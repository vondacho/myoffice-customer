package edu.noia.myoffice.customer.data.test.util;

import edu.noia.myoffice.customer.domain.vo.Affiliate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestAffiliate {

    public static Affiliate random() {
        return Affiliate.of(UUID.randomUUID(), false);
    }
}
