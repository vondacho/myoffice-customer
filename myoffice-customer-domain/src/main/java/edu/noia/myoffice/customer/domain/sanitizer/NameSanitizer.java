package edu.noia.myoffice.customer.domain.sanitizer;

import java.util.Optional;

public class NameSanitizer {
    public Optional<String> sanitize(String name) {
        return Optional.ofNullable(name)
            .map(n -> n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase());
    }
}
