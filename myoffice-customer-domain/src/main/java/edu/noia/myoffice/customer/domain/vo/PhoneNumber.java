package edu.noia.myoffice.customer.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.PHONE_INTERNATIONAL;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class PhoneNumber {

    @Pattern(regexp = PHONE_INTERNATIONAL, message = "'${validatedValue}' does not follow " + PHONE_INTERNATIONAL)
    @NotNull
    private String number;

    @NotNull
    private Kind kind;

    public enum Kind {PRIVATE, OFFICE, MOBILE, FAX}
}
