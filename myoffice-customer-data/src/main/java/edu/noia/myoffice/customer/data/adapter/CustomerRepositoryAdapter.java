package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRepositoryAdapter implements CustomerRepository {

    @NonNull
    JpaCustomerStateRepository repository;

    @Override
    public Optional<Customer> findOne(CustomerId id) {
        return repository
                .findById(id.getId())
                .map(this::toCustomer);
    }

    @Override
    public List<Customer> findAll(Specification specification) {
        return repository
                .findAll(specification)
                .stream()
                .map(this::toCustomer)
                .collect(toList());
    }

    @Override
    public Page<Customer> findAll(Specification specification, Pageable pageable) {
        return repository
                .findAll(specification, pageable)
                .map(this::toCustomer);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(this::toCustomer);
    }

    @Override
    public Customer save(Customer customer) {
        return save(customer.getId(), customer.getState());
    }

    @Override
    public Customer save(CustomerId id, CustomerState state) {
        return Customer.ofValid(id, repository.save(toJpaEntity(state).setId(id.getId())));
    }

    @Override
    public void delete(CustomerId id) {
        repository
                .findById(id.getId())
                .ifPresent(repository::delete);
    }

    private Customer toCustomer(JpaCustomerState state) {
        return Customer.ofValid(CustomerId.of(state.getId()), state);
    }

    private JpaCustomerState toJpaEntity(CustomerState state) {
        return state instanceof JpaCustomerState ? (JpaCustomerState)state : JpaCustomerState.of(state);
    }
}
