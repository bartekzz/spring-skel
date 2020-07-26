package com.cepheid.cloud.skel.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueItemNameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueItemName {
    String message() default "Name is already used";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}