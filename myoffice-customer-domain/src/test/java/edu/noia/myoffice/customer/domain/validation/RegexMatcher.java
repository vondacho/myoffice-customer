package edu.noia.myoffice.customer.domain.validation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

@RequiredArgsConstructor
public class RegexMatcher extends TypeSafeMatcher<String> {

    private final @NonNull String regex;

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches regular expression=`" + regex + "`");
    }

    @Override
    public boolean matchesSafely(final String string) {
        return string.matches(regex);
    }

    public static RegexMatcher matchesRegex(final String regex) {
        return new RegexMatcher(regex);
    }
}
