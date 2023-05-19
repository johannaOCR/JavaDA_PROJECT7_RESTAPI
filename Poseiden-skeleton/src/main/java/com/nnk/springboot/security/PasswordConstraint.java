package com.nnk.springboot.security;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface PasswordConstraint {
    String message() default "au moins une lettre majuscule, au moins 8 caractères, au moins un chiffre et un symbole";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

