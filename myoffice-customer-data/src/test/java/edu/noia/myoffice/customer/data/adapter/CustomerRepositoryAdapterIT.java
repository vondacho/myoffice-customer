package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.CustomerDataComponentConfig;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.data.test.util.TestCustomer;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {CustomerDataComponentConfig.class})
@DataJpaTest
public class CustomerRepositoryAdapterIT {

    @Autowired
    private CustomerRepositoryAdapter repositoryAdapter;
    @Autowired
    private JpaCustomerStateRepository jpaRepository;
    @PersistenceContext
    private EntityManager em;

    @Test
    public void save_should_return_the_customer_with_a_persistent_state() {
        // Given
        Customer anyCustomer = TestCustomer.random();
        // When
        Customer customer = repositoryAdapter.save(anyCustomer.getId(), anyCustomer.getState());
        flushClear();
        // Then
        assertThat(customer).isEqualTo(anyCustomer);
        Object state = ReflectionTestUtils.getField(customer,"state");
        assertThat(state).isInstanceOf(JpaCustomerState.class);
        assertThat(ReflectionTestUtils.getField(state,"primaryId")).isNotNull();
    }

    @Test
    public void save_should_persist_and_return_the_customer() {
        // Given
        Customer anyCustomer = TestCustomer.random();
        // When
        Customer customer = repositoryAdapter.save(anyCustomer.getId(), anyCustomer.getState());
        flushClear();
        // Then
        JpaCustomerState jpaCustomer = jpaRepository.findById(customer.getId().getId()).orElse(null);
        assertThat(jpaCustomer).isNotNull();
        assertThat(jpaCustomer).isEqualToComparingOnlyGivenFields(customer.getState(),
                "lastName", "zip", "city", "country");
    }

    @Test
    public void readModifySave() {
        repositoryAdapter.findAll().forEach(customer -> customer.save(repositoryAdapter));
    }

    private void flushClear() {
        em.flush();
        em.clear();
    }
}
