package edu.noia.myoffice.customer.domain.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.regex.Matcher;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.PHONE_PATTERN;

@Slf4j
public class PhoneNumberGoogleSanitizer implements PhoneNumberSanitizer {
    @Override
    public Optional<PhoneNumber> sanitize(PhoneNumber phoneNumber) {
        return Optional.ofNullable(phoneNumber)
                .map(PhoneNumber::getNumber)
                .flatMap(number -> sanitize(number))
                .map(tidyNumber -> PhoneNumber.of(tidyNumber, phoneNumber.getKind()));
    }

    @Override
    public Optional<String> sanitize(String phoneNumber) {
        // Extract a phone number
        final Matcher matcher = PHONE_PATTERN.matcher(phoneNumber);
        if (matcher.find()) {
            try {
                // Canonize the original phone number into an international number
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                final String CH_ISO_CODE = "CH";
                final Phonenumber.PhoneNumber parsedNumber = phoneUtil.parse(matcher.group(), CH_ISO_CODE);
                final String canonizedNumber = phoneUtil.format(parsedNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
                LOG.debug("{} has been sanitized into {}", phoneNumber, canonizedNumber);
                return Optional.of(canonizedNumber);
            } catch (NumberParseException e) {
                LOG.info("{} does not contain a valid phone number that can be parsed", phoneNumber);
            }
        } else {
            LOG.info("{} does not contain a valid phone number", phoneNumber);
        }
        return Optional.empty();
    }

}
