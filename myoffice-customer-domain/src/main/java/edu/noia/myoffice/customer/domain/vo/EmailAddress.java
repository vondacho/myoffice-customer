package edu.noia.myoffice.customer.domain.vo;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.EMAIL;

@Embeddable
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmailAddress {

    @Pattern(regexp = EMAIL, message = "'${validatedValue}' does not follow " + EMAIL)
    @NonNull
    @NotNull
    private String address;
    @Enumerated(EnumType.STRING)
    @NonNull
    @NotNull
    private Kind kind;

    public enum Kind {PRIVATE, OFFICE}
}
