package edu.noia.myoffice.customer.domain.sanitizer;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneNumberGoogleSanitizerTest {

    final PhoneNumberGoogleSanitizer sanitizer = new PhoneNumberGoogleSanitizer();

    @Test
    public void should_sanitize_swiss_as_expected() {
        checkOk("+41 22 285 56 66 aaa", "+41 22 285 56 66");
        checkOk("+41222855666", "+41 22 285 56 66");
        checkOk("+41 22 285 56 66", "+41 22 285 56 66");
    }

    @Test
    public void should_handle_invalid_swiss_cases_as_expected() {
        checkKo("+41 22 285 56 66 1234 abc");
        checkKo("022 28 55 66");
        checkKo("022 abc 28 55 66");
        checkKo("a");
    }

    @Test
    public void should_sanitize_international_as_expected() {
        checkOk("+33 9 99 99 99 99", "+33 9 99 99 99 99");
        checkOk("+33 9 99 99 99 99 aaa", "+33 9 99 99 99 99");
        checkOk("+44 20 7123 4567", "+44 20 7123 4567");
    }

    @Test
    public void should_handle_invalid_international_cases_as_expected() {
        checkKo("+33 9 99 99 99");
        checkKo("+33 99 99 99 99");
        checkKo("+44 20 71 23 45");
    }

    private void checkOk(String number, String sanitized) {
        Optional<String> phone = sanitizer.sanitize(number);
        assertThat(phone.isPresent(), Matchers.is(true));
        phone.ifPresent(p -> assertThat(p, Matchers.equalTo(sanitized)));
    }

    private void checkKo(String number) {
        Optional<String> phone = sanitizer.sanitize(number);
        assertThat(phone.isPresent(), Matchers.is(false));
    }
}
