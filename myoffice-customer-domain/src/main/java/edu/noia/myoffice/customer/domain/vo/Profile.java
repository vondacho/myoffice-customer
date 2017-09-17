package edu.noia.myoffice.customer.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Profile {

    private Boolean hasMoved;

    private Boolean isSubcontractor;

    private Boolean sendInvitation;
}
