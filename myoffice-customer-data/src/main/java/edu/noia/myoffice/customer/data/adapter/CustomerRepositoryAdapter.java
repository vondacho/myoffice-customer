package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRepositoryAdapter implements CustomerRepository {

    @NonNull
    JpaCustomerStateRepository repository;

    @Override
    public Optional<Customer> findOne(UUID id) {
        return repository
                .findById(id)
                .map(state -> Customer.ofValid(state.getId(), state));
    }

    @Override
    public List<Customer> findAll(Specification specification) {
        return repository
                .findAll(specification)
                .stream()
                .map(state -> Customer.ofValid(state.getId(), state))
                .collect(toList());
    }

    @Override
    public Page<Customer> findAll(Specification specification, Pageable pageable) {
        return repository
                .findAll(specification, pageable)
                .map(state -> Customer.ofValid(state.getId(), state));
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(state -> Customer.ofValid(state.getId(), state));
    }

    @Override
    public Customer save(Customer customer) {
        return save(customer.getId(), customer.getState());
    }

    @Override
    public Customer save(UUID id, CustomerState state) {
        return Customer.ofValid(id, repository.save(toJpaEntity(state).setId(id)));
    }

    @Override
    public void delete(UUID id) {
        repository
                .findById(id)
                .ifPresent(customer -> repository.delete(customer));
    }

    private JpaCustomerState toJpaEntity(CustomerState state) {
        return state instanceof JpaCustomerState ? (JpaCustomerState)state : JpaCustomerState.of(state);
    }
}
