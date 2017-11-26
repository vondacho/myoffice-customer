package edu.noia.myoffice.customer.domain.validation;

import org.junit.Test;

import static edu.noia.myoffice.common.util.RegexMatcher.matchesRegex;
import static edu.noia.myoffice.customer.domain.validation.ValidationPatterns.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class ValidationPatternsTest {
    @Test
    public void emailTest() {
        assertThat("b@c.d", matchesRegex(EMAIL));
        assertThat("b@c.d.e.f", matchesRegex(EMAIL));
        assertThat("a.b@c.d", matchesRegex(EMAIL));
        assertThat("a.b@c.d.e.f", matchesRegex(EMAIL));
        assertThat("a", not(matchesRegex(EMAIL)));
        assertThat("a.b@c", not(matchesRegex(EMAIL)));
        assertThat("a@c", not(matchesRegex(EMAIL)));
    }

    @Test
    public void phoneTest() {
        assertThat("032 724 08 05", matchesRegex(PHONE_CH));
        assertThat("0041 32 841 38 38", matchesRegex(PHONE_CH));
        assertThat("0041 22 228 55 66", matchesRegex(PHONE_CH));
        assertThat("099 999 99 99", matchesRegex(PHONE_CH));
        assertThat("0041 02 228 55 66", not(matchesRegex(PHONE_CH)));
        assertThat("022 28.55.66", not(matchesRegex(PHONE_CH)));
        assertThat("+41 32 724 52 94", matchesRegex(PHONE_INTERNATIONAL));
        assertThat("+41022285566", matchesRegex(PHONE_INTERNATIONAL));
        assertThat("+99 99 999 99 99", matchesRegex(PHONE_INTERNATIONAL));
        assertThat("032 724 52 94xxx", not(matchesRegex(PHONE_INTERNATIONAL)));
        assertThat("xxx", not(matchesRegex(PHONE_INTERNATIONAL)));
        assertThat("099 999 99 99", not(matchesRegex(PHONE_INTERNATIONAL)));
        assertThat("99 999 99 99", not(matchesRegex(PHONE_INTERNATIONAL)));
        assertThat("099 999 99 9", not(matchesRegex(PHONE_INTERNATIONAL)));
    }
}
