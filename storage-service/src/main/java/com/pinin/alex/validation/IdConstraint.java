package com.pinin.alex.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IdValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdConstraint {
    String getMessage() default "Invalid id format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
