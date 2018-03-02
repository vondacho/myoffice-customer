package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.common.util.search.FindCriteria;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
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
    public List<Customer> findByCriteria(List<FindCriteria> criteria) {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public Customer save(CustomerId id, CustomerState state) {
        return new PersistedCustomer(id, repository.save(toJpaEntity(state).setId(id.getId())));
    }

    @Override
    public void delete(CustomerId id) {
        repository
                .findById(id.getId())
                .ifPresent(repository::delete);
    }

    private Customer toCustomer(JpaCustomerState state) {
        return new PersistedCustomer(CustomerId.of(state.getId()), state);
    }

    private JpaCustomerState toJpaEntity(CustomerState state) {
        return state instanceof JpaCustomerState ? (JpaCustomerState)state : JpaCustomerState.of(state);
    }

    private class PersistedCustomer extends Customer {
        private PersistedCustomer(CustomerId id, CustomerState state) {
            super(id, state);
        }
    }
}
