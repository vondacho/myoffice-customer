package edu.noia.myoffice.customer.rest.util.test;

import edu.noia.myoffice.customer.data.adapter.CustomerStateFactoryAdapter;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import edu.noia.myoffice.customer.domain.vo.Profile;
import edu.noia.myoffice.customer.domain.vo.Social;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerRestTestObjectFactory {

    public static final Customer CUSTOMER_TEST_OBJECT = createDefaultCustomer();

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

    private static Customer createDefaultCustomer() {
        CustomerState state = new CustomerStateFactoryAdapter().of(
                DEFAULT_LAST_NAME,
                DEFAULT_ZIP,
                DEFAULT_CITY,
                DEFAULT_COUNTRY);
        state.setId(UUID.randomUUID());
        state.setSalutation(DEFAULT_SALUTATION);
        state.setFirstName(DEFAULT_FIRST_NAME);
        state.setBirthDate(DEFAULT_BIRTH_DATE);
        state.setStreet(DEFAULT_STREET);
        state.setRegion(DEFAULT_REGION);
        state.setPhoneNumber1(PhoneNumber.of(DEFAULT_PHONE_NUMBER, PhoneNumber.Kind.PRIVATE));
        state.setEmailAddress1(EmailAddress.of(DEFAULT_EMAIL_ADDRESS, EmailAddress.Kind.PRIVATE));
        state.setWebsiteUrl(DEFAULT_WEBSITE);
        state.setSocial(DEFAULT_SOCIAL);
        state.setProfile(DEFAULT_PROFILE);
        state.setNotes(DEFAULT_NOTES);
        return Customer.of(state);
    }

    private static Profile createDefaultProfile() {
        return null;
    }

    private static Social createDefaultSocial() {
        return null;
    }

}

