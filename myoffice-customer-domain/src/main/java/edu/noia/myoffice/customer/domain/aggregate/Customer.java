package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.util.BeanValidator;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.EmailAddressSanitizer;
import edu.noia.myoffice.customer.domain.service.NameSanitizer;
import edu.noia.myoffice.customer.domain.service.PhoneNumberGoogleSanitizer;
import edu.noia.myoffice.customer.domain.service.PhoneNumberSanitizer;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerMutableSample;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity<
        Customer,
        CustomerId,
        CustomerState,
        CustomerMutableState,
        CustomerRepository> {

    public static EmailAddressSanitizer emailAddressSanitizer = new EmailAddressSanitizer();
    public static PhoneNumberSanitizer phoneNumberSanitizer = new PhoneNumberGoogleSanitizer();
    public static NameSanitizer nameSanitizer = new NameSanitizer();

    public static Customer of(@NonNull CustomerState state) {
        return of(CustomerId.random(), state);
    }

    public static Customer of(@NonNull CustomerId id, @NonNull CustomerState state) {
        return ofValid(id, validateState(state));
    }

    public static Customer ofValid(@NonNull CustomerId id, @NonNull CustomerState state) {
        return new Customer().setState(state).setId(id);
    }

    public Folder folderize() {
        return Folder.of(FolderSample.builder(state.getFullname()).build()).affiliate(getId());
    }

    private static <T> T validateState(T state) {
        return BeanValidator.validate(state);
    }

    public Customer sanitize() {
        CustomerMutableState ms = toMutable();
        ms
                .setLastName(nameSanitizer.sanitize(ms.getLastName()).orElse(null))
                .setFirstName(nameSanitizer.sanitize(ms.getFirstName()).orElse(null))
                .setPhoneNumber1(phoneNumberSanitizer.sanitize(ms.getPhoneNumber1()).orElse(null))
                .setPhoneNumber2(phoneNumberSanitizer.sanitize(ms.getPhoneNumber2()).orElse(null))
                .setPhoneNumber3(phoneNumberSanitizer.sanitize(ms.getPhoneNumber3()).orElse(null))
                .setPhoneNumber4(phoneNumberSanitizer.sanitize(ms.getPhoneNumber4()).orElse(null))
                .setEmailAddress1(emailAddressSanitizer.sanitize(ms.getEmailAddress1()).orElse(null))
                .setEmailAddress2(emailAddressSanitizer.sanitize(ms.getEmailAddress2()).orElse(null))
                .setEmailAddress3(emailAddressSanitizer.sanitize(ms.getEmailAddress3()).orElse(null));

        return setState(ms);
    }

    @Override
    protected CustomerMutableState toMutableState(CustomerState state) {
        return CustomerMutableSample.of(state);
    }

    @Override
    protected CustomerState toImmutableState(CustomerState state) {
        return CustomerSample.of(state);
    }

    @Override
    protected CustomerId identify() {
        return CustomerId.random();
    }

    @Override
    protected CustomerState validate(CustomerState state) {
        return validateState(state);
    }
}
