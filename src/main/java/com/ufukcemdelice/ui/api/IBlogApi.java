package com.ufukcemdelice.ui.api;

import com.ufukcemdelice.business.dto.BlogDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface IBlogApi {

    //CREATE
    ResponseEntity<?>  createBlog(@Valid @RequestBody BlogDto blogDto);

    //LIST
    ResponseEntity<List<BlogDto>>  listBlog();

    //FIND
    ResponseEntity<BlogDto> findBlog(Long id);


    //UPDATE
    ResponseEntity<BlogDto>  updateBlog(Long id, BlogDto registerDto);

    //DELETE
    ResponseEntity <Map<String, Boolean>> deleteBlog( Long id);
}