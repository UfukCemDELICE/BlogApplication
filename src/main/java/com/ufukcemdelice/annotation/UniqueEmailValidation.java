package com.ufukcemdelice.annotation;

import com.ufukcemdelice.data.entity.BlogEntity;
import com.ufukcemdelice.data.repository.IBlogRepository;
import lombok.RequiredArgsConstructor;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//lombok
@RequiredArgsConstructor
public class UniqueEmailValidation implements ConstraintValidator<UserRegisterUniqueEmail,String> {

    //repository
    private final IBlogRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        BlogEntity registerEntity=repository.findByEmail(email);
        if(registerEntity!=null)
            return false;
        return true;
    }
}