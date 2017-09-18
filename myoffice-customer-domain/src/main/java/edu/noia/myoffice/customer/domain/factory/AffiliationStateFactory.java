package edu.noia.myoffice.customer.domain.factory;

import edu.noia.myoffice.customer.domain.aggregate.AffiliationState;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;

public interface AffiliationStateFactory {

    AffiliationState of(AffiliationVO data);

    default AffiliationState of(AffiliationState state) {
        return state;
    }
}
