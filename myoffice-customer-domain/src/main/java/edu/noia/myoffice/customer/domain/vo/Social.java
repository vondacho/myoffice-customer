package edu.noia.myoffice.customer.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value(staticConstructor = "of")
@EqualsAndHashCode(callSuper = false)
public class Social {

    private String skypeUrl;
    private String facebookUrl;
    private String googleplusUrl;
    private String linkedinUrl;
    private String twitterUrl;
    private String instagramUrl;
}
