package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.common.data.jpa.util.SpecificationBuilder;
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

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRepositoryAdapter implements CustomerRepository {

    @NonNull
    JpaCustomerStateRepository repository;

    @Override
    public Optional<Customer> findOne(CustomerId id) {
        return repository
                .findById(id)
                .map(this::toCustomer);
    }

    @Override
    public List<Customer> findByCriteria(List<FindCriteria> criteria) {
        SpecificationBuilder builder = new SpecificationBuilder();
        criteria.forEach(builder::with);
        return repository
                .findAll(builder.build())
                .stream()
                .map(this::toCustomer)
                .collect(toList());
    }

    @Override
    public Customer save(CustomerId id, CustomerState state) {
        return new PersistedCustomer(id, repository.save(toJpaEntity(state).setId(id)));
    }

    @Override
    public void delete(CustomerId id) {
        repository
                .findById(id)
                .ifPresent(repository::delete);
    }

    private Customer toCustomer(JpaCustomerState state) {
        return new PersistedCustomer(state.getId(), state);
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
