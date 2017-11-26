package edu.noia.myoffice.customer.domain.test.util;

import edu.noia.myoffice.common.domain.repository.InMemoryKeyValueRepository;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specifications;

import static org.assertj.core.api.Assertions.assertThat;

public class InMemoryKeyValueRepositoryTest {

    InMemoryKeyValueRepository<Customer, CustomerId, CustomerState> repository;

    @Before
    public void setup() {
        repository = new InMemoryKeyValueRepository<>((id, state) -> Customer.ofValid(id, state));
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
        assertThat(repository.findAll(Specifications.where(null))).containsExactly(anyCustomer);

        // given
        Customer otherCustomer = TestCustomer.random();
        // when
        customer = repository.save(otherCustomer);
        // then
        assertThat(customer).isEqualTo(otherCustomer);
        assertThat(repository.findOne(otherCustomer.getId()).isPresent()).isTrue();
        assertThat(repository.findAll(Specifications.where(null))).containsExactlyInAnyOrder(anyCustomer, otherCustomer);

        // given
        Customer modifiedCustomer = customer.modify(
                CustomerSample.builder("anyName", "123", "anyCity", "anyCountry").build());
        // when
        customer = repository.save(modifiedCustomer);
        // then
        assertThat(customer).isEqualTo(modifiedCustomer);
        assertThat(repository.findOne(modifiedCustomer.getId()).isPresent()).isTrue();
        assertThat(repository.findAll(Specifications.where(null))).containsExactlyInAnyOrder(anyCustomer, modifiedCustomer);

        // when
        repository.delete(anyCustomer.getId());
        // then
        assertThat(repository.findAll(Specifications.where(null))).containsExactly(modifiedCustomer);

        // when
        repository.delete(otherCustomer.getId());
        // then
        assertThat(repository.findAll(Specifications.where(null))).isEmpty();
    }
}
