package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.*;
import edu.noia.myoffice.customer.domain.validation.BeanValidator;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import edu.noia.myoffice.customer.domain.vo.MutableCustomerSample;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(of = "id", callSuper = false)
@RequiredArgsConstructor(staticName = "ofValid")
@FieldDefaults(level = AccessLevel.PACKAGE)
public class Customer {

    public static EmailAddressSanitizer emailAddressSanitizer = new EmailAddressSanitizer();
    public static PhoneNumberSanitizer phoneNumberSanitizer = new PhoneNumberGoogleSanitizer();
    public static NameSanitizer nameSanitizer = new NameSanitizer();
    public static IdSanitizer idSanitizer = new IdSanitizer();

    @Getter
    @NonNull
    UUID id;
    @NonNull
    CustomerState state;

    public static Customer of(@NonNull CustomerState state) {
        return of(identify(), state);
    }

    public static Customer of(@NonNull UUID id, @NonNull CustomerState state) {
        return ofValid(id, validate(state));
    }

    private static MutableCustomerState toMutable(CustomerState state) {
        return state instanceof MutableCustomerState ? (MutableCustomerState)state : MutableCustomerSample.of(state);
    }

    private static UUID identify() {
        return UUID.randomUUID();
    }

    private static <T> T validate(T state) {
        return BeanValidator.validate(state);
    }

    public CustomerState getState() {
        return CustomerSample.of(state);
    }

    public Customer modify(CustomerState modifier) {
        state = toMutable(state).modify(validate(modifier));
        return this;
    }

    public Customer save(CustomerRepository repository) {
        return repository.save(getId(), state);
    }

    public Folder folderize() {
        return Folder.of(FolderSample.builder(state.getFullname()).build()).affiliate(getId());
    }

    public Customer sanitize() {
        MutableCustomerState ms = toMutable(state);
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
        state = ms;
        return this;
    }
}
