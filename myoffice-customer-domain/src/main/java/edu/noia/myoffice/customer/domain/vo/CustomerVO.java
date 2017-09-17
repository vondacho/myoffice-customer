package edu.noia.myoffice.customer.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerVO {
    String salutation;
    String firstName;
    @NotNull
    String lastName;
    LocalDate birthDate;
    String street;
    @NotNull
    String zip;
    @NotNull
    String city;
    String region;
    @NotNull
    String country;
    @Valid
    PhoneNumber phoneNumber1;
    @Valid
    PhoneNumber phoneNumber2;
    @Valid
    PhoneNumber phoneNumber3;
    @Valid
    PhoneNumber phoneNumber4;
    @Valid
    EmailAddress emailAddress1;
    @Valid
    EmailAddress emailAddress2;
    @Valid
    EmailAddress emailAddress3;
    String websiteUrl;
    @Valid
    Social social;
    @Valid
    Profile profile;
    String notes;

    public String getFullname() {
        return Optional.ofNullable(getFirstName())
                .map(fn -> fn + " " + getLastName()).orElseGet(() -> getLastName());
    }
}
