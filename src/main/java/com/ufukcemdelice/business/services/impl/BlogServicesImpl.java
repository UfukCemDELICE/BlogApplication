package com.ufukcemdelice.business.services.impl;

import com.ufukcemdelice.bean.ModelMapperBean;
import com.ufukcemdelice.bean.PasswordEncoderBean;
import com.ufukcemdelice.business.dto.BlogDto;
import com.ufukcemdelice.business.services.IBlogServices;
import com.ufukcemdelice.data.entity.BlogEntity;
import com.ufukcemdelice.data.repository.IBlogRepository;
import com.ufukcemdelice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//lombok
@RequiredArgsConstructor
@Log4j2

@Service
@Transactional
//asıl iş katmanı olan yer
public class BlogServicesImpl implements IBlogServices {

    //injection
    private final IBlogRepository repository;
    private final ModelMapperBean modelMapperBean;
    private final PasswordEncoderBean passwordEncoderBean;

    // Model Mapper
    @Override
    public BlogDto entityToDto(BlogEntity blogEntity) {
        return modelMapperBean.modelMapperMethod().map(blogEntity, BlogDto.class);
    }

    @Override
    public BlogEntity dtoToEntity(BlogDto blogDto) {
        return modelMapperBean.modelMapperMethod().map(blogDto, BlogEntity.class);
    }

    //CREATE
    @Override
    public BlogDto createBlog(BlogDto blogDto) {
        blogDto.setPassword(passwordEncoderBean.passwordEncoderMethod().encode(blogDto.getPassword()));
        BlogEntity registerEntity = dtoToEntity(blogDto);
        repository.save(registerEntity);
        return blogDto;
    }

    //LIST
    @Override
    public List<BlogDto> listBlog() {
        List<BlogEntity> registerEntityList = repository.findAll();
        List<BlogDto> dtoList = new ArrayList<>();
        for (BlogEntity temp : registerEntityList) {
            BlogDto entityToDto = entityToDto(temp);
            dtoList.add(entityToDto);
        }
        return dtoList;
    }

    //FIND
    @Override
    public BlogDto findBlog(Long id) {
        BlogEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        BlogDto entityToDto = entityToDto(registerEntity);
        return entityToDto;
    }

    //DELETE
    @Override
    public Map<String, Boolean> deleteBlog(Long id) {
        BlogEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        repository.delete(registerEntity);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    //UPDATE
    @Override
    public BlogDto updateBlog(Long id, BlogDto blogDto) {
        BlogEntity registerEntity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id + " id bulunamadı"));
        if (registerEntity != null) {
            registerEntity.setBlogHeader(blogDto.getBlogHeader());
            registerEntity.setBlogContent(blogDto.getBlogContent());
            registerEntity.setEmail(blogDto.getEmail());
            registerEntity.setPassword(blogDto.getPassword());
            repository.save(registerEntity);
        }
        return blogDto;
    }
}