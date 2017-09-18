package edu.noia.myoffice.customer.domain.util.test;

import edu.noia.myoffice.customer.data.adapter.AffiliationStateFactoryAdapter;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AffiliationTestObjectFactory {

    public static Affiliation createDefaultAffiliation(Customer customer, Folder folder) {
        return Affiliation.of(new AffiliationStateFactoryAdapter().of(AffiliationVO.builder()
            .isPrimaryDebtor(true)
            .customer(customer.getId())
            .folder(folder.getId())
            .build()));
    }

}

