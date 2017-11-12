package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import edu.noia.myoffice.customer.domain.vo.Profile;
import edu.noia.myoffice.customer.domain.vo.Social;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public interface CustomerState {

    String getSalutation();
    @NotNull
    String getLastName();
    String getFirstName();
    LocalDate getBirthDate();
    String getStreetNo();
    @NotNull
    String getZip();
    @NotNull
    String getCity();
    String getRegion();
    @NotNull
    String getCountry();
    @Valid
    PhoneNumber getPhoneNumber1();
    @Valid
    PhoneNumber getPhoneNumber2();
    @Valid
    PhoneNumber getPhoneNumber3();
    @Valid
    PhoneNumber getPhoneNumber4();
    @Valid
    EmailAddress getEmailAddress1();
    @Valid
    EmailAddress getEmailAddress2();
    @Valid
    EmailAddress getEmailAddress3();
    String getWebsiteUrl();
    @Valid
    Social getSocial();
    @Valid
    Profile getProfile();
    String getNotes();

    default String getFullname() {
        return Optional.ofNullable(getFirstName())
                .map(fn -> fn + " " + getLastName()).orElseGet(() -> getLastName());
    }
}
