package com.pinin.alex.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IdValidator implements ConstraintValidator<IdConstraint, String> {

    @Override
    public void initialize(IdConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^[0-9a-z]{24}$");
        return pattern.matcher(value).matches();
    }
}
