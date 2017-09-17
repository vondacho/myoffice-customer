package edu.noia.myoffice.customer.domain.entity;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(of = "state", doNotUseGetters = true)
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Affiliation {
    @NonNull
    AffiliationState state;
}
