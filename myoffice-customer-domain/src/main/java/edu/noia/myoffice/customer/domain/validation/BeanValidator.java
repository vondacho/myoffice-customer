package edu.noia.myoffice.customer.domain.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanValidator {

    private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static <T> T validate(T input) {
        return validate(input, Default.class);
    }

    public static <T> T validate(T input, Class<?>... groups) {
        Optional.of(validator.validate(input, groups))
                .filter(violations -> !violations.isEmpty())
                .ifPresent(violations -> {
                    throw new ConstraintViolationException(violations);
                });
        return input;
    }
}
