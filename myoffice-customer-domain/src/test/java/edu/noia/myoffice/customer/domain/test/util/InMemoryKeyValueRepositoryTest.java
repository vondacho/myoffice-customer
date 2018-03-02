package edu.noia.myoffice.customer.domain.test.util;

import edu.noia.myoffice.common.domain.repository.InMemoryKeyValueRepository;
import edu.noia.myoffice.common.util.search.FindCriteria;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import org.junit.Before;
import org.junit.Test;

import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryKeyValueRepositoryTest {

    private InMemoryKeyValueRepository<Customer, CustomerId, CustomerState> repository;

    @Before
    public void setup() {
        BiFunction<CustomerId, CustomerState, Customer> entityCreator = (id, state) -> Customer.of(id, state);
        repository = new InMemoryKeyValueRepository(entityCreator);
    }

    @Test
    public void should_insert_find_update_delete_as_expected() {
        // given
        Customer anyCustomer = TestCustomer.random();
        // when
        Customer customer = repository.save(anyCustomer);
        // then
        assertThat(customer).isEqualTo(anyCustomer);
        assertThat(repository.findOne(anyCustomer.getId()).isPresent()).isTrue();
        assertThat(repository.findByCriteria(FindCriteria.empty())).containsExactly(anyCustomer);

        // given
        Customer otherCustomer = TestCustomer.random();
        // when
        customer = repository.save(otherCustomer);
        // then
        assertThat(customer).isEqualTo(otherCustomer);
        assertThat(repository.findOne(otherCustomer.getId()).isPresent()).isTrue();
        assertThat(repository.findByCriteria(FindCriteria.empty())).containsExactlyInAnyOrder(anyCustomer, otherCustomer);

        // given
        Customer modifiedCustomer = customer.modify(
                CustomerSample.of("anyName", "123", "anyCity", "anyCountry"));
        // when
        customer = repository.save(modifiedCustomer);
        // then
        assertThat(customer).isEqualTo(modifiedCustomer);
        assertThat(repository.findOne(modifiedCustomer.getId()).isPresent()).isTrue();
        assertThat(repository.findByCriteria(FindCriteria.empty())).containsExactlyInAnyOrder(anyCustomer, modifiedCustomer);

        // when
        repository.delete(anyCustomer.getId());
        // then
        assertThat(repository.findByCriteria(FindCriteria.empty())).containsExactly(modifiedCustomer);

        // when
        repository.delete(otherCustomer.getId());
        // then
        assertThat(repository.findByCriteria(FindCriteria.empty())).isEmpty();
    }
}
