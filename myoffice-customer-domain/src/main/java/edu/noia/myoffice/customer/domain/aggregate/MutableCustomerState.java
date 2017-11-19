package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import edu.noia.myoffice.customer.domain.vo.Profile;
import edu.noia.myoffice.customer.domain.vo.Social;

import java.time.LocalDate;

public interface MutableCustomerState extends CustomerState {

    MutableCustomerState setSalutation(String value);
    MutableCustomerState setLastName(String value);
    MutableCustomerState setFirstName(String value);
    MutableCustomerState setBirthDate(LocalDate value);
    MutableCustomerState setStreetNo(String value);
    MutableCustomerState setZip(String value);
    MutableCustomerState setCity(String value);
    MutableCustomerState setRegion(String value);
    MutableCustomerState setCountry(String value);
    MutableCustomerState setPhoneNumber1(PhoneNumber value);
    MutableCustomerState setPhoneNumber2(PhoneNumber value);
    MutableCustomerState setPhoneNumber3(PhoneNumber value);
    MutableCustomerState setPhoneNumber4(PhoneNumber value);
    MutableCustomerState setEmailAddress1(EmailAddress value);
    MutableCustomerState setEmailAddress2(EmailAddress value);
    MutableCustomerState setEmailAddress3(EmailAddress value);
    MutableCustomerState setProfile(Profile value);
    MutableCustomerState setSocial(Social value);
    MutableCustomerState setWebsiteUrl(String value);
    MutableCustomerState setNotes(String value);

    default MutableCustomerState modify(CustomerState modifier) {
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

    default MutableCustomerState patch(CustomerState modifier) {
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
