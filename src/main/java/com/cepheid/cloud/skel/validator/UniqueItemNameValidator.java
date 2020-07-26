package com.cepheid.cloud.skel.validator;

import com.cepheid.cloud.skel.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UniqueItemNameValidator implements ConstraintValidator<UniqueItemName, String>
{
    @Autowired ItemRepository mItemRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !mItemRepository.existsByName(name);
    }
}
