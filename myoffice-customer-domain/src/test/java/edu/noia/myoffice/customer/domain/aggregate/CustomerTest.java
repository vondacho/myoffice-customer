package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class CustomerTest {

    @Test
    public void equals_contract_should_be_implemented_correctly() {
        EqualsVerifier.forClass(CustomerId.class).verify();
        EqualsVerifier.forClass(CustomerSample.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
        EqualsVerifier.forClass(BaseEntity.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .withRedefinedSubclass(Customer.class)
                .withOnlyTheseFields("id")
                .verify();
    }
}
