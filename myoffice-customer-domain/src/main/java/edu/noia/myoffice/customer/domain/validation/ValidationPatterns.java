package edu.noia.myoffice.customer.domain.validation;

import java.util.regex.Pattern;

public interface ValidationPatterns {

    String EMAIL = "[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9]+)+";

    String PHONE_INTERNATIONAL = "\\+(?:[0-9] ?){6,14}[0-9]";

    String PHONE_CH = "(?:(?:|0{1,2}|\\+{0,2})41(?:|\\(0\\))|0)(| )([1-9]\\d)(| )(\\d{3})(| )(\\d{2})(| )(\\d{2})";

    Pattern EMAIL_PATTERN = Pattern.compile(EMAIL);
    Pattern PHONE_INTERNATIONAL_PATTERN = Pattern.compile(PHONE_INTERNATIONAL);
    Pattern PHONE_CH_PATTERN = Pattern.compile(PHONE_CH);
}