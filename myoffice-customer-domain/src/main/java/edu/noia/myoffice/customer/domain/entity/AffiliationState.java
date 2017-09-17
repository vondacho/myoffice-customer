package edu.noia.myoffice.customer.domain.entity;

import edu.noia.myoffice.customer.domain.vo.AffiliationVO;

public interface AffiliationState {
    AffiliationState setData(AffiliationVO data);

    AffiliationVO getData();
}
