package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder(builderMethodName = "hiddenBuilder", toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CustomerSample implements CustomerState {

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

    public static CustomerSample of(CustomerState state) {
        return CustomerSample.builder(state.getLastName(), state.getZip(), state.getCity(), state.getCountry())
                .salutation(state.getSalutation())
                .firstName(state.getFirstName())
                .birthDate(state.getBirthDate())
                .streetNo(state.getStreetNo())
                .region(state.getRegion())
                .emailAddress1(state.getEmailAddress1())
                .emailAddress2(state.getEmailAddress2())
                .emailAddress3(state.getEmailAddress3())
                .phoneNumber1(state.getPhoneNumber1())
                .phoneNumber2(state.getPhoneNumber2())
                .phoneNumber3(state.getPhoneNumber3())
                .phoneNumber4(state.getPhoneNumber4())
                .profile(state.getProfile())
                .social(state.getSocial())
                .websiteUrl(state.getWebsiteUrl())
                .notes(state.getNotes())
                .build();
    }

    public static CustomerSampleBuilder builder(
            @NonNull String lastName, @NonNull String zip, @NonNull String city, @NonNull String country) {
        return hiddenBuilder()
                .lastName(lastName)
                .zip(zip)
                .city(city)
                .country(country);
    }
}
