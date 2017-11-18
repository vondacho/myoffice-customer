package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.regex.Matcher;

import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.EMAIL_PATTERN;

@Slf4j
public class EmailAddressSanitizer {
    public Optional<EmailAddress> sanitize(EmailAddress email) {
        return Optional.ofNullable(email)
            .map(EmailAddress::getAddress)
            .flatMap(address -> sanitize(address))
            .map(tidyAddress -> EmailAddress.of(tidyAddress, email.getKind()));
    }

    public Optional<String> sanitize(String emailAddress) {
        final Matcher matcher = EMAIL_PATTERN.matcher(emailAddress);
        if (matcher.find()) {
            LOG.debug("{} has been sanitized into {}", emailAddress, matcher.group());
            return Optional.of(matcher.group());
        } else {
            LOG.info("{} does not contain a valid email address", emailAddress);
        }
        return Optional.empty();
    }
}
