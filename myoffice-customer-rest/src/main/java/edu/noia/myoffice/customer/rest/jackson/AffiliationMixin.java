package edu.noia.myoffice.customer.rest.jackson;

import java.util.UUID;

public interface AffiliationMixin {

    AffiliateCustomer getCustomer();
    AffiliateFolder getFolder();

    interface AffiliateCustomer {
        UUID getId();
    }
    interface AffiliateFolder {
        UUID getId();
    }
}
