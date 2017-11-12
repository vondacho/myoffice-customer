package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.MutableCustomerState;
import lombok.*;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MutableCustomerSample implements MutableCustomerState {
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

    public static MutableCustomerState of(CustomerState state) {
        return new MutableCustomerSample().modify(state);
    }
}
