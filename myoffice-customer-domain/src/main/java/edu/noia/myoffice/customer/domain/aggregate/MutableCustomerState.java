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
}
