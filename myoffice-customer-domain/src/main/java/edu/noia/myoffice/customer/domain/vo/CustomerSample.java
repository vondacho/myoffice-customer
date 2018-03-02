package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CustomerSample implements CustomerState {

    String salutation;
    String firstName;
    @NonNull
    String lastName;
    LocalDate birthDate;
    String streetNo;
    @NonNull
    String zip;
    @NonNull
    String city;
    String region;
    @NonNull
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

    public static CustomerSample of(CustomerState state) {
        return CustomerSample.of(state.getLastName(), state.getZip(), state.getCity(), state.getCountry())
                .setSalutation(state.getSalutation())
                .setFirstName(state.getFirstName())
                .setBirthDate(state.getBirthDate())
                .setStreetNo(state.getStreetNo())
                .setRegion(state.getRegion())
                .setEmailAddress1(state.getEmailAddress1())
                .setEmailAddress2(state.getEmailAddress2())
                .setEmailAddress3(state.getEmailAddress3())
                .setPhoneNumber1(state.getPhoneNumber1())
                .setPhoneNumber2(state.getPhoneNumber2())
                .setPhoneNumber3(state.getPhoneNumber3())
                .setPhoneNumber4(state.getPhoneNumber4())
                .setProfile(state.getProfile())
                .setSocial(state.getSocial())
                .setWebsiteUrl(state.getWebsiteUrl())
                .setNotes(state.getNotes());
    }

    @Override
    public CustomerState modify(EntityState modifier) {
        return modifier instanceof CustomerState ? modify((CustomerState)modifier) : this;
    }

    @Override
    public CustomerState patch(EntityState modifier) {
        return modifier instanceof CustomerState ? patch((CustomerState)modifier) : this;
    }
}
