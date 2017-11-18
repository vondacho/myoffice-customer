package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerRepositoryAdapterStub implements CustomerRepository {
    @Override
    public Optional<Customer> findOne(UUID id) {
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
    public Customer save(UUID id, CustomerState state) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
