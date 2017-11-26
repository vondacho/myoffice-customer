package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerId;

public interface CustomerRepository extends EntityRepository<Customer, CustomerId, CustomerState> {
}

