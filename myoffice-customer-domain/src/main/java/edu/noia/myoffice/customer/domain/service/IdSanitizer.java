package edu.noia.myoffice.customer.domain.service;

import java.util.Optional;
import java.util.UUID;

public class IdSanitizer {
    public UUID sanitize(UUID id) {
        return Optional.ofNullable(id).orElse(UUID.randomUUID());
    }
}
