package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface CustomerRepository {

    @Transactional(readOnly = true)
    Optional<Customer> findOne(UUID id);

    @Transactional(readOnly = true)
    List<Customer> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Customer> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    default List<Customer> findAll() {
        return findAll(Specifications.where(null));
    }

    Customer save(Customer customer);

    void delete(UUID id);
}

