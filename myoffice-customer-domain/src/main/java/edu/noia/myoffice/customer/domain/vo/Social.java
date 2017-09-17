package edu.noia.myoffice.customer.domain.vo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Social {

    private String skypeUrl;

    private String facebookUrl;

    private String googleplusUrl;

    private String linkedinUrl;

    private String twitterUrl;

    private String instagramUrl;
}
