package edu.noia.myoffice.customer.domain.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.Validation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BeanValidator {
    public static <T> T validate(T bean) {
        Validation.buildDefaultValidatorFactory().getValidator().validate(bean);
        return bean;
    }
}
