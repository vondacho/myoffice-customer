package edu.noia.myoffice.customer.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.PHONE;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class PhoneNumber {

    @Pattern(regexp = PHONE, message = "'${validatedValue}' does not follow " + PHONE)
    @NotNull
    private String number;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Kind kind;

    public enum Kind {PRIVATE, OFFICE, MOBILE, FAX}
}
