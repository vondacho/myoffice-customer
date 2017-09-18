package edu.noia.myoffice.customer.domain.util.test;

import edu.noia.myoffice.customer.data.adapter.CustomerStateFactoryAdapter;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerTestObjectFactory {

    private static final String DEFAULT_LAST_NAME = "Doe";
    private static final String DEFAULT_ZIP = "1260";
    private static final String DEFAULT_CITY = "Nyon";
    private static final String DEFAULT_COUNTRY = "Switzerland";
    private static final String DEFAULT_SALUTATION = "Mister";
    private static final String DEFAULT_FIRST_NAME = "John";
    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.of(1900, 1,1);
    private static final String DEFAULT_STREET = "Picadilly 89b";
    private static final String DEFAULT_REGION = "Vaud";
    private static final String DEFAULT_PHONE_NUMBER = "079 900 90 90";
    private static final String DEFAULT_EMAIL_ADDRESS = "john.doe@seven.com";
    private static final String DEFAULT_WEBSITE = "johndoe.seven.com";
    private static final Social DEFAULT_SOCIAL = createDefaultSocial();
    private static final Profile DEFAULT_PROFILE = createDefaultProfile();
    private static final String DEFAULT_NOTES = "He is very dangerous.";

    public static Customer createDefaultCustomer() {
        CustomerState state = new CustomerStateFactoryAdapter().of(CustomerVO.builder()
            .lastName(DEFAULT_LAST_NAME)
            .zip(DEFAULT_ZIP)
            .city(DEFAULT_CITY)
            .country(DEFAULT_COUNTRY)
            .salutation(DEFAULT_SALUTATION)
            .firstName(DEFAULT_FIRST_NAME)
            .birthDate(DEFAULT_BIRTH_DATE)
            .street(DEFAULT_STREET)
            .region(DEFAULT_REGION)
            .phoneNumber1(PhoneNumber.of(DEFAULT_PHONE_NUMBER, PhoneNumber.Kind.PRIVATE))
            .emailAddress1(EmailAddress.of(DEFAULT_EMAIL_ADDRESS, EmailAddress.Kind.PRIVATE))
            .websiteUrl(DEFAULT_WEBSITE)
            .social(DEFAULT_SOCIAL)
            .profile(DEFAULT_PROFILE)
            .notes(DEFAULT_NOTES).build());

        return Customer.of(state);
    }

    private static Profile createDefaultProfile() {
        return null;
    }

    private static Social createDefaultSocial() {
        return null;
    }

}

