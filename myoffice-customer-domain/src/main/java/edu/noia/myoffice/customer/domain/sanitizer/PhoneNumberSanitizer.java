package edu.noia.myoffice.customer.domain.sanitizer;

import edu.noia.myoffice.customer.domain.vo.PhoneNumber;

import java.util.Optional;

public interface PhoneNumberSanitizer {
    
    Optional<PhoneNumber> sanitize(PhoneNumber phoneNumber);

    Optional<String> sanitize(String phoneNumber);
}
