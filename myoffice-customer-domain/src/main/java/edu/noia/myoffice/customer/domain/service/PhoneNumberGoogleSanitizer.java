package edu.noia.myoffice.customer.domain.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class PhoneNumberGoogleSanitizer implements PhoneNumberSanitizer {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
    private static final String DEFAULT_REGION = "CH";

    @Override
    public Optional<PhoneNumber> sanitize(PhoneNumber phoneNumber) {
        return Optional.ofNullable(phoneNumber)
                .map(PhoneNumber::getNumber)
                .flatMap(this::extract)
                .flatMap(this::parse)
                .flatMap(this::canonize)
                .map(canonized -> kindize(phoneNumber, canonized));
    }

    @Override
    public Optional<String> sanitize(String phoneNumber) {
        return Optional.ofNullable(phoneNumber)
                .flatMap(this::extract)
                .flatMap(this::parse)
                .flatMap(this::canonize)
                .map(PhoneNumber::getNumber);
    }

    private Optional<String> extract(String phoneNumber) {
        Iterator<PhoneNumberMatch> it = phoneNumberUtil.findNumbers(
                phoneNumber, DEFAULT_REGION, PhoneNumberUtil.Leniency.POSSIBLE, Long.MAX_VALUE).iterator();
        return it.hasNext() ? Optional.of(it.next().rawString()) : Optional.empty();
    }

    private Optional<Phonenumber.PhoneNumber> clean(Phonenumber.PhoneNumber phoneNumber) {
        return phoneNumberUtil.truncateTooLongNumber(phoneNumber) ? Optional.of(phoneNumber) : Optional.empty();
    }

    private Optional<Phonenumber.PhoneNumber> parse(String phoneNumber) {
        try {
            return Optional.of(phoneNumberUtil.parse(phoneNumber, DEFAULT_REGION));
        } catch (NumberParseException e) {
            LOG.debug("{} does not contain a valid phone number that can be parsed", phoneNumber);
            return Optional.empty();
        }
    }

    private Optional<PhoneNumber> canonize(Phonenumber.PhoneNumber phoneNumber) {
        if (phoneNumberUtil.isValidNumber(phoneNumber)) {
            final PhoneNumberUtil.PhoneNumberType parsedNumberType = phoneNumberUtil.getNumberType(phoneNumber);
            final String canonizedNumber = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            LOG.debug("{} has been canonized as {} of type {}", phoneNumber, canonizedNumber, parsedNumberType);
            return Optional.of(PhoneNumber.of(canonizedNumber, toKind(parsedNumberType)));
        }
        else {
            LOG.debug("{} does not contain a valid phone number", phoneNumber);
            return Optional.empty();
        }
    }

    private PhoneNumber kindize(PhoneNumber raw, PhoneNumber canonized) {
        return raw.getKind() == null ? canonized :
            raw.getKind() == PhoneNumber.Kind.OFFICE && canonized.getKind() == PhoneNumber.Kind.PRIVATE ?
                    PhoneNumber.of(canonized.getNumber(), raw.getKind()) : canonized;
    }

    private PhoneNumber.Kind toKind(PhoneNumberUtil.PhoneNumberType type) {
        switch (type) {
            case PAGER:
                return PhoneNumber.Kind.FAX;
            case VOIP:
                return PhoneNumber.Kind.OFFICE;
            case MOBILE:
                return PhoneNumber.Kind.MOBILE;
            default:
                return PhoneNumber.Kind.PRIVATE;
        }
    }

    private String patternize(String phoneNumber, Pattern pattern) {
        final Matcher matcher = pattern.matcher(phoneNumber);
        if (matcher.find()) {
            LOG.debug("{} has been sanitized following the pattern {}", phoneNumber, matcher.group());
            return matcher.group();
        }
        else {
            LOG.info("{} do not satisfy the pattern {}", phoneNumber, pattern.pattern());
            return phoneNumber;
        }
    }
}
