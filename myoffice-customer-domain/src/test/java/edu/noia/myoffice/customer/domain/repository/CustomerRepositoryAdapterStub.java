package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerRepositoryAdapterStub implements CustomerRepository {
    @Override
    public Optional<Customer> findOne(CustomerId id) {
        return null;
    }

    @Override
    public List<Customer> findAll(Specification specification) {
        return null;
    }

    @Override
    public Page<Customer> findAll(Specification specification, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Customer save(Customer customer) {
        return null;
    }

    @Override
    public Customer save(CustomerId id, CustomerState state) {
        return null;
    }

    @Override
    public void delete(CustomerId id) {

    }
}
