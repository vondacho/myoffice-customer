package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.util.validation.BeanValidator;
import edu.noia.myoffice.customer.domain.event.CustomerCreatedEventPayload;
import edu.noia.myoffice.customer.domain.service.EmailAddressSanitizer;
import edu.noia.myoffice.customer.domain.service.NameSanitizer;
import edu.noia.myoffice.customer.domain.service.PhoneNumberGoogleSanitizer;
import edu.noia.myoffice.customer.domain.service.PhoneNumberSanitizer;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity<Customer, CustomerId, CustomerState> {

    public static final EmailAddressSanitizer emailAddressSanitizer = new EmailAddressSanitizer();
    public static final PhoneNumberSanitizer phoneNumberSanitizer = new PhoneNumberGoogleSanitizer();
    public static final NameSanitizer nameSanitizer = new NameSanitizer();

    protected Customer(CustomerId id, CustomerState state) {
        super(id, state);
    }

    public static Customer of(@NonNull CustomerState state) {
        return of(CustomerId.random(), state);
    }

    public static Customer of(@NonNull CustomerId id, @NonNull CustomerState state) {
        return ofValid(id, validateState(state));
    }

    public static Customer ofValid(@NonNull CustomerId id, @NonNull CustomerState state) {
        Customer customer = new Customer(id, CustomerSample.of(validateState(state)));
        return customer.andEvent(BaseEvent.of(CustomerCreatedEventPayload.of(id, (CustomerSample) customer.state)));
    }

    public Folder folderize() {
        return Folder.of(FolderSample.of(state.getFullname())).affiliate(getId());
    }

    private static <T> T validateState(T state) {
        return BeanValidator.validate(state);
    }

    public Customer sanitize() {
        state
                .setLastName(nameSanitizer.sanitize(state.getLastName()).orElse(null))
                .setFirstName(nameSanitizer.sanitize(state.getFirstName()).orElse(null))
                .setPhoneNumber1(phoneNumberSanitizer.sanitize(state.getPhoneNumber1()).orElse(null))
                .setPhoneNumber2(phoneNumberSanitizer.sanitize(state.getPhoneNumber2()).orElse(null))
                .setPhoneNumber3(phoneNumberSanitizer.sanitize(state.getPhoneNumber3()).orElse(null))
                .setPhoneNumber4(phoneNumberSanitizer.sanitize(state.getPhoneNumber4()).orElse(null))
                .setEmailAddress1(emailAddressSanitizer.sanitize(state.getEmailAddress1()).orElse(null))
                .setEmailAddress2(emailAddressSanitizer.sanitize(state.getEmailAddress2()).orElse(null))
                .setEmailAddress3(emailAddressSanitizer.sanitize(state.getEmailAddress3()).orElse(null));
        return this;
    }

    @Override
    public void validate(CustomerState state) {
        validateState(state);
    }

    @Override
    protected CustomerState cloneState() {
        return CustomerSample.of(state);
    }
}