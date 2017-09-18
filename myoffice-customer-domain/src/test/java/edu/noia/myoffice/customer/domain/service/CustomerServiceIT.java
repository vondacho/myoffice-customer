package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.data.MyOfficeCustomerDataApplication;
import edu.noia.myoffice.customer.domain.MyOfficeCustomerDomainApplication;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.AffiliationRepository;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static edu.noia.myoffice.customer.domain.util.test.AffiliationTestObjectFactory.createDefaultAffiliation;
import static edu.noia.myoffice.customer.domain.util.test.CustomerTestObjectFactory.createDefaultCustomer;
import static edu.noia.myoffice.customer.domain.util.test.FolderTestObjectFactory.createDefaultFolder;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyOfficeCustomerDataApplication.class, MyOfficeCustomerDomainApplication.class})
@Transactional
public class CustomerServiceIT {

    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FolderRepository folderRepository;
    @Autowired
    private AffiliationRepository affiliationRepository;

    @Test
    public void create_should_return_an_affiliation_of_the_customer_and_a_new_folder() {
        // Given
        CustomerState customerState = createDefaultCustomer().getState();
        CustomerVO customerData = customerState.getData();
        // When
        Affiliation affiliation = service.create(customerData);
        // Then
        assertThat(affiliation).isNotNull();
    }

    @Test
    public void create_in_an_existing_folder_should_return_an_affiliation_of_the_customer_and_this_folder() {
        // Given
        final Customer testCustomer = createDefaultCustomer();
        final Folder existingFolder = createDefaultFolder();
        folderRepository.save(existingFolder);
        flushClear();

        CustomerState customerState = testCustomer.getState();
        CustomerVO customerData = customerState.getData();
        // When
        Affiliation affiliation = service.createAndAffiliate(customerData, existingFolder.getId());
        // Then
        assertThat(affiliation).isNotNull();
        assertThat(affiliation.getState().getData().getFolder()).isEqualTo(existingFolder.getId());
    }

    @Test
    public void affiliate_an_existing_customer_into_an_existing_folder_should_return_the_right_affiliation() {
        // Given
        final Customer existingCustomer = customerRepository.save(createDefaultCustomer());
        final Folder existingFolder = folderRepository.save(createDefaultFolder());
        final Affiliation existingAffiliation =
                affiliationRepository.save(createDefaultAffiliation(existingCustomer, existingFolder));
        flushClear();
        // When
        Affiliation affiliation = service.affiliate(existingCustomer.getId(), existingFolder.getId());
        // Then
        assertThat(affiliation).isNotNull();
        assertThat(affiliation).isEqualTo(existingAffiliation);
    }

    @PersistenceContext
    private EntityManager em;

    protected void flushClear() {
        em.flush();
        em.clear();
    }
}
