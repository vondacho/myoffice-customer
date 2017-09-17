package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.validation.ValidationPatterns;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.PHONE;

@Embeddable
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhoneNumber {

    @Pattern(regexp = PHONE, message = "'${validatedValue}' does not follow " + PHONE)
    @NonNull
    @NotNull
    private String number;
    @Enumerated(EnumType.STRING)
    @NonNull
    @NotNull
    private Kind kind;

    public enum Kind {PRIVATE, OFFICE, MOBILE, FAX}
}
