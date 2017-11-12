package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.MyOfficeCustomerDataApplication;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.data.test.util.TestCustomer;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyOfficeCustomerDataApplication.class})
@Transactional
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
        Customer customer = repositoryAdapter.save(anyCustomer);
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
        Customer customer = repositoryAdapter.save(anyCustomer);
        flushClear();
        // Then
        JpaCustomerState jpaCustomer = jpaRepository.findById(customer.getId()).orElse(null);
        assertThat(jpaCustomer).isNotNull();
        assertThat(jpaCustomer).isEqualToComparingOnlyGivenFields(customer.getState(),
                "lastName", "zip", "city", "country");
    }

    protected void flushClear() {
        em.flush();
        em.clear();
    }
}
