package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.data.test.util.TestCustomer;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryAdapterTest {

    @Mock
    private JpaCustomerStateRepository jpaRepository;
    @InjectMocks
    private CustomerRepositoryAdapter repositoryAdapter;

    @Test
    public void save_should_call_jpa_persistence_and_return_the_customer_with_the_expected_state() {
        // Given
        CustomerState anyState = TestCustomer.randomValid();
        Customer anyCustomer = Customer.of(UUID.randomUUID(), anyState);
        given(jpaRepository.save(any(JpaCustomerState.class)))
                .willAnswer(invocation -> invocation.getArgument(0));
        // When
        Customer customer1 = repositoryAdapter.save(anyCustomer);
        Customer customer2 = repositoryAdapter.save(anyCustomer.getId(), anyState);
        // Then
        assertThat(customer1).isEqualTo(customer2);
        assertThat(customer1.getState()).isEqualTo(customer2.getState()).isEqualToComparingFieldByField(anyState);
        verify(jpaRepository, times(2)).save(any(JpaCustomerState.class));
    }

    @Test
    public void save_should_call_jpa_persistence_and_return_the_customer_with_the_expected_native_state() {
        // Given
        CustomerState anyState = TestCustomer.randomValid();
        Customer anyCustomer = Customer.of(UUID.randomUUID(), anyState);
        JpaCustomerState jpaState = JpaCustomerState.of(anyState);
        given(jpaRepository.save(any(JpaCustomerState.class))).willReturn(jpaState);
        // When
        Customer customer = repositoryAdapter.save(anyCustomer);
        // Then
        assertThat(customer).isEqualTo(anyCustomer);
        Object state = ReflectionTestUtils.getField(customer,"state");
        assertThat(state).isInstanceOf(JpaCustomerState.class);
        assertThat(state).isEqualToIgnoringGivenFields(jpaState,"primaryId");
        verify(jpaRepository).save(any(JpaCustomerState.class));
    }

}
