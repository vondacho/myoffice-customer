package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.vo.CustomerVO;

import java.util.UUID;

public interface CustomerState {

    UUID getId();

    CustomerState setData(CustomerVO data);

    CustomerVO getData();
}
