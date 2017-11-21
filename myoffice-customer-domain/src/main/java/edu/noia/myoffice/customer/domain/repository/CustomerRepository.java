package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface CustomerRepository {

    @Transactional(readOnly = true)
    Optional<Customer> findOne(CustomerId id);

    @Transactional(readOnly = true)
    List<Customer> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Customer> findAll(Specification specification, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Customer> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    default List<Customer> findAll() {
        return findAll(Specifications.where(null));
    }

    Customer save(Customer customer);

    Customer save(CustomerId id, CustomerState state);

    void delete(CustomerId id);
}

