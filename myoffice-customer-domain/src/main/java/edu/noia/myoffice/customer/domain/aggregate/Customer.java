package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.*;
import edu.noia.myoffice.customer.domain.validation.BeanValidator;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import edu.noia.myoffice.customer.domain.vo.MutableCustomerSample;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(of = "id", callSuper = false)
@RequiredArgsConstructor(staticName = "ofValid")
@FieldDefaults(level = AccessLevel.PACKAGE)
public class Customer {

    private static EmailAddressSanitizer emailAddressSanitizer = new EmailAddressSanitizer();
    private static PhoneNumberSanitizer phoneNumberSanitizer = new PhoneNumberGoogleSanitizer();
    private static NameSanitizer nameSanitizer = new NameSanitizer();
    private static IdSanitizer idSanitizer = new IdSanitizer();

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
        return state instanceof MutableCustomerState ? (MutableCustomerState) state : MutableCustomerSample.of(state);
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
        toMutable().modify(validate(modifier));
        return this;
    }

    public Customer save(CustomerRepository repository) {
        return repository.save(getId(), state);
    }

    public Folder folderize() {
        return Folder.of(FolderSample.builder(state.getFullname()).build());
    }

    public Customer sanitize() {
        id = idSanitizer.sanitize(id);
        toMutable()
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

    private MutableCustomerState toMutable() {
        return (MutableCustomerState) (state = toMutable(state));
    }
}
