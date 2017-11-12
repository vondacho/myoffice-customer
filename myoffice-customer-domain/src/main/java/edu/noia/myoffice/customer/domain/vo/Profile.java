package edu.noia.myoffice.customer.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class Profile {

    @NonNull
    Boolean hasMoved;
    @NonNull
    Boolean isSubcontractor;
    @NonNull
    Boolean sendInvitation;
}