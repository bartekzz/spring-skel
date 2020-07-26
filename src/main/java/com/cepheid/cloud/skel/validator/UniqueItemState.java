package com.cepheid.cloud.skel.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueItemStateValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueItemState
{
    Class<? extends Enum<?>> enumClass();
    String message() default "State is only accepting UNDEFINED, VALID, INVALID";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}