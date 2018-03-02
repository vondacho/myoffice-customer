package edu.noia.myoffice.customer.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.EMAIL;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class EmailAddress {

    @Pattern(regexp = EMAIL, message = "'${validatedValue}' does not follow " + EMAIL)
    @NotNull
    String address;

    @NotNull
    Kind kind;

    public enum Kind {PRIVATE, OFFICE}
}
