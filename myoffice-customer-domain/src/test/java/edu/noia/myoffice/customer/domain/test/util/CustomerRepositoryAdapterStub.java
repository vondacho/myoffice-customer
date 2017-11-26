package edu.noia.myoffice.customer.domain.test.util;

import edu.noia.myoffice.common.domain.repository.InMemoryKeyValueRepository;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.vo.CustomerId;

public class CustomerRepositoryAdapterStub
        extends InMemoryKeyValueRepository<Customer, CustomerId, CustomerState>
        implements CustomerRepository {

    public CustomerRepositoryAdapterStub() {
        super((id, state) -> Customer.ofValid(id, state));
    }
}
