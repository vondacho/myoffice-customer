package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.CustomerMutableState;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
public class CustomerMutableSample implements CustomerMutableState {
    String salutation;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String streetNo;
    String zip;
    String city;
    String region;
    String country;
    PhoneNumber phoneNumber1;
    PhoneNumber phoneNumber2;
    PhoneNumber phoneNumber3;
    PhoneNumber phoneNumber4;
    EmailAddress emailAddress1;
    EmailAddress emailAddress2;
    EmailAddress emailAddress3;
    String websiteUrl;
    Social social;
    Profile profile;
    String notes;

    public static CustomerMutableState of(CustomerState state) {
        return new CustomerMutableSample().modify(state);
    }
}
