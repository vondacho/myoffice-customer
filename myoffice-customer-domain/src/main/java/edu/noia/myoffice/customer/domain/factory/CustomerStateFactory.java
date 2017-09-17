package edu.noia.myoffice.customer.domain.factory;

import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import lombok.NonNull;

public interface CustomerStateFactory {

    CustomerState of(CustomerVO data);

    default CustomerState of(CustomerState state) {
        return state;
    }
}
