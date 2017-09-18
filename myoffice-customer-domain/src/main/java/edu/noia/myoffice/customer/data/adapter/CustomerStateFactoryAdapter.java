package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.factory.CustomerStateFactory;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomerStateFactoryAdapter implements CustomerStateFactory {

    @Override
    public CustomerState of(CustomerVO data) {
        return JpaCustomerState.of(UUID.randomUUID(), data.getLastName(), data.getZip(), data.getCity(), data.getCountry()).setData(data);
    }
}
