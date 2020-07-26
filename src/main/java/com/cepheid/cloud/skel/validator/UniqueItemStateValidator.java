package com.cepheid.cloud.skel.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UniqueItemStateValidator implements ConstraintValidator<UniqueItemState, CharSequence>
{
    private List<String> acceptedValues;

    @Override
    public void initialize(UniqueItemState annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }


    public boolean isValid(CharSequence value, ConstraintValidatorContext context)
    {
        if (value == null)
        {
            return true;
        }

        return acceptedValues.contains(value.toString());
    }
}
