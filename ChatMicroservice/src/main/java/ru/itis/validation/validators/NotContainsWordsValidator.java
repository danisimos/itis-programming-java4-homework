package ru.itis.validation.validators;

import ru.itis.validation.annotations.NotContainsWords;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class NotContainsWordsValidator implements ConstraintValidator<NotContainsWords, String> {
    private String[] words;

    @Override
    public void initialize(NotContainsWords constraintAnnotation) {
        this.words = constraintAnnotation.words();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Arrays.stream(words).noneMatch(value::contains);
    }
}
