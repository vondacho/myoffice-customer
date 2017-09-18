package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.service.EmailAddressSanitizer;
import edu.noia.myoffice.customer.domain.service.NameSanitizer;
import edu.noia.myoffice.customer.domain.service.PhoneNumberGoogleSanitizer;
import edu.noia.myoffice.customer.domain.service.PhoneNumberSanitizer;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(of = {"state"}, doNotUseGetters = true, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    private static EmailAddressSanitizer emailAddressSanitizer = new EmailAddressSanitizer();
    private static PhoneNumberSanitizer phoneNumberSanitizer = new PhoneNumberGoogleSanitizer();
    private static NameSanitizer nameSanitizer = new NameSanitizer();

    @NonNull
    CustomerState state;

    public UUID getId() {
        return state.getId();
    }

    public CustomerState getState() {
        return state;
    }

    public Customer setData(CustomerVO data) {
        state.setData(data);
        return this;
    }

    public Customer sanitize() {
        CustomerVO data = state.getData();
        data.setLastName(nameSanitizer.sanitize(data.getLastName()).orElse(null));
        data.setFirstName(nameSanitizer.sanitize(data.getFirstName()).orElse(null));
        data.setPhoneNumber1(phoneNumberSanitizer.sanitize(data.getPhoneNumber1()).orElse(null));
        data.setPhoneNumber2(phoneNumberSanitizer.sanitize(data.getPhoneNumber2()).orElse(null));
        data.setPhoneNumber3(phoneNumberSanitizer.sanitize(data.getPhoneNumber3()).orElse(null));
        data.setPhoneNumber4(phoneNumberSanitizer.sanitize(data.getPhoneNumber4()).orElse(null));
        data.setEmailAddress1(emailAddressSanitizer.sanitize(data.getEmailAddress1()).orElse(null));
        data.setEmailAddress2(emailAddressSanitizer.sanitize(data.getEmailAddress2()).orElse(null));
        data.setEmailAddress3(emailAddressSanitizer.sanitize(data.getEmailAddress3()).orElse(null));
        return setData(data);
    }
}
