package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaAffiliationState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.domain.aggregate.AffiliationState;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.factory.AffiliationStateFactory;
import edu.noia.myoffice.customer.domain.factory.CustomerStateFactory;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AffiliationStateFactoryAdapter implements AffiliationStateFactory {

    @Override
    public AffiliationState of(AffiliationVO data) {
        return JpaAffiliationState.of(data.getCustomer(), data.getFolder()).setData(data);
    }
}
