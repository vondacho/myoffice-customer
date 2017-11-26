package edu.noia.myoffice.customer.domain.test.util;

import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestAffiliate {

    public static Affiliate random() {
        return Affiliate.of(CustomerId.random(), false);
    }
}
