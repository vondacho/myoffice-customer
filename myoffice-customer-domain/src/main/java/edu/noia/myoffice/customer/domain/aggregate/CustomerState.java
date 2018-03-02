package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import edu.noia.myoffice.customer.domain.vo.Profile;
import edu.noia.myoffice.customer.domain.vo.Social;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public interface CustomerState extends EntityState {

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
                .map(fn -> fn + " " + getLastName()).orElseGet(this::getLastName);
    }

    CustomerState setSalutation(String value);
    CustomerState setLastName(String value);
    CustomerState setFirstName(String value);
    CustomerState setBirthDate(LocalDate value);
    CustomerState setStreetNo(String value);
    CustomerState setZip(String value);
    CustomerState setCity(String value);
    CustomerState setRegion(String value);
    CustomerState setCountry(String value);
    CustomerState setPhoneNumber1(PhoneNumber value);
    CustomerState setPhoneNumber2(PhoneNumber value);
    CustomerState setPhoneNumber3(PhoneNumber value);
    CustomerState setPhoneNumber4(PhoneNumber value);
    CustomerState setEmailAddress1(EmailAddress value);
    CustomerState setEmailAddress2(EmailAddress value);
    CustomerState setEmailAddress3(EmailAddress value);
    CustomerState setProfile(Profile value);
    CustomerState setSocial(Social value);
    CustomerState setWebsiteUrl(String value);
    CustomerState setNotes(String value);

    default CustomerState modify(CustomerState modifier) {
        return setSalutation(modifier.getSalutation())
                .setFirstName(modifier.getFirstName())
                .setLastName(modifier.getLastName())
                .setBirthDate(modifier.getBirthDate())
                .setStreetNo(modifier.getStreetNo())
                .setZip(modifier.getZip())
                .setCity(modifier.getCity())
                .setRegion(modifier.getRegion())
                .setCountry(modifier.getCountry())
                .setEmailAddress1(modifier.getEmailAddress1())
                .setEmailAddress2(modifier.getEmailAddress2())
                .setEmailAddress3(modifier.getEmailAddress3())
                .setPhoneNumber1(modifier.getPhoneNumber1())
                .setPhoneNumber2(modifier.getPhoneNumber2())
                .setPhoneNumber3(modifier.getPhoneNumber3())
                .setPhoneNumber4(modifier.getPhoneNumber4())
                .setProfile(modifier.getProfile())
                .setSocial(modifier.getSocial())
                .setWebsiteUrl(modifier.getWebsiteUrl())
                .setNotes(modifier.getNotes());
    }

    default CustomerState patch(CustomerState modifier) {
        return setSalutation(modifier.getSalutation() != null ? modifier.getSalutation() : getSalutation())
                .setFirstName(modifier.getFirstName() != null ? modifier.getFirstName() : getFirstName())
                .setLastName(modifier.getLastName() != null ? modifier.getLastName() : getLastName())
                .setBirthDate(modifier.getBirthDate() != null ? modifier.getBirthDate() : getBirthDate())
                .setStreetNo(modifier.getStreetNo() != null ? modifier.getStreetNo() : getStreetNo())
                .setZip(modifier.getZip() != null ? modifier.getZip() : getZip())
                .setCity(modifier.getCity() != null ? modifier.getCity() : getCity())
                .setRegion(modifier.getRegion() != null ? modifier.getRegion() : getRegion())
                .setCountry(modifier.getCountry() != null ? modifier.getCountry() : getCountry())
                .setEmailAddress1(modifier.getEmailAddress1() != null ? modifier.getEmailAddress1() : getEmailAddress1())
                .setEmailAddress2(modifier.getEmailAddress2() != null ? modifier.getEmailAddress2() : getEmailAddress2())
                .setEmailAddress3(modifier.getEmailAddress3() != null ? modifier.getEmailAddress3() : getEmailAddress3())
                .setPhoneNumber1(modifier.getPhoneNumber1() != null ? modifier.getPhoneNumber1() : getPhoneNumber1())
                .setPhoneNumber2(modifier.getPhoneNumber2() != null ? modifier.getPhoneNumber2() : getPhoneNumber2())
                .setPhoneNumber3(modifier.getPhoneNumber3() != null ? modifier.getPhoneNumber3() : getPhoneNumber3())
                .setPhoneNumber4(modifier.getPhoneNumber4() != null ? modifier.getPhoneNumber4() : getPhoneNumber4())
                .setProfile(modifier.getProfile() != null ? modifier.getProfile() : getProfile())
                .setSocial(modifier.getSocial() != null ? modifier.getSocial() : getSocial())
                .setWebsiteUrl(modifier.getWebsiteUrl() != null ? modifier.getWebsiteUrl() : getWebsiteUrl())
                .setNotes(modifier.getNotes() != null ? modifier.getNotes() : getNotes());
    }

}
