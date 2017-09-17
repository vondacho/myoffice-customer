package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.data.MyOfficeCustomerDataApplication;
import edu.noia.myoffice.customer.domain.MyOfficeCustomerDomainApplication;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @Test
    public void create_should_return_an_affiliation_of_the_customer_and_a_new_folder() {
        // Given
        CustomerState customerState = createDefaultCustomer().getState();
        CustomerVO customerData = new CustomerVO(
                customerState.getSalutation(),
                customerState.getFirstName(),
                customerState.getLastName(),
                customerState.getBirthDate(),
                customerState.getStreet(),
                customerState.getZip(),
                customerState.getCity(),
                customerState.getRegion(),
                customerState.getCountry(),
                customerState.getPhoneNumber1(),
                customerState.getPhoneNumber2(),
                customerState.getPhoneNumber3(),
                customerState.getPhoneNumber4(),
                customerState.getEmailAddress1(),
                customerState.getEmailAddress2(),
                customerState.getEmailAddress3(),
                customerState.getWebsiteUrl(),
                customerState.getSocial(),
                customerState.getProfile(),
                customerState.getNotes());
        // When
        AffiliationVO affiliation = service.create(customerData);
        // Then
        assertThat(affiliation).isNotNull();
        // customer
        assertThat(affiliation.getCustomer().getId()).isNotNull();
        assertThat(affiliation.getCustomer().getState()).isEqualToIgnoringGivenFields(customerData,
                "id", "folder", "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy", "primaryId");
        // folder
        FolderState fstate = affiliation.getFolder().getState();
        assertThat(fstate.getId()).isNotNull();
        assertThat(fstate.getName()).isNotNull();
        assertThat(affiliation.getFolder().getCustomers()).contains(affiliation.getCustomer());
        assertThat(affiliation.getFolder().getCustomers().size()).isEqualTo(1);
    }

    @Test
    public void create_in_an_existing_folder_should_return_an_affiliation_of_the_customer_and_this_folder() {
        // Given
        final Customer existingCustomer = createDefaultCustomer();
        final Customer testCustomer = createDefaultCustomer();
        final Folder existingFolder = createDefaultFolder();
        folderRepository.save(existingFolder.add(existingCustomer));
        flushClear();

        CustomerState customerState = testCustomer.getState();
        CustomerVO customerData = new CustomerVO(
                customerState.getSalutation(),
                customerState.getFirstName(),
                customerState.getLastName(),
                customerState.getBirthDate(),
                customerState.getStreet(),
                customerState.getZip(),
                customerState.getCity(),
                customerState.getRegion(),
                customerState.getCountry(),
                customerState.getPhoneNumber1(),
                customerState.getPhoneNumber2(),
                customerState.getPhoneNumber3(),
                customerState.getPhoneNumber4(),
                customerState.getEmailAddress1(),
                customerState.getEmailAddress2(),
                customerState.getEmailAddress3(),
                customerState.getWebsiteUrl(),
                customerState.getSocial(),
                customerState.getProfile(),
                customerState.getNotes());
        // When
        AffiliationVO affiliation = service.createInFolder(customerData, existingFolder.getId());
        // Then
        assertThat(affiliation).isNotNull();
        // customer
        assertThat(affiliation.getCustomer().getId()).isNotNull();
        assertThat(affiliation.getCustomer().getId()).isNotEqualTo(existingCustomer.getId());
        assertThat(affiliation.getCustomer().getState()).isEqualToIgnoringGivenFields(customerData,
                "id", "folder", "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy", "primaryId");
        // folder
        assertThat(affiliation.getFolder()).isEqualTo(existingFolder);
        assertThat(affiliation.getFolder().getCustomers().size()).isEqualTo(2);
        assertThat(affiliation.getFolder().getCustomers()).contains(existingCustomer);
        assertThat(affiliation.getFolder().getCustomers()).contains(affiliation.getCustomer());
    }

    @Test
    public void affiliate_an_existing_customer_into_an_existing_folder_should_return_the_right_affiliation() {
        // Given
        final Customer existingCustomer = customerRepository.save(createDefaultCustomer());
        final Folder existingFolder = folderRepository.save(createDefaultFolder().add(createDefaultCustomer()));
        flushClear();
        // When
        AffiliationVO affiliation = service.affiliate(existingCustomer.getId(), existingFolder.getId());
        // Then
        assertThat(affiliation).isNotNull();
        // customer
        assertThat(affiliation.getCustomer()).isEqualTo(existingCustomer);
        // folder
        assertThat(affiliation.getFolder()).isEqualTo(existingFolder);
        assertThat(affiliation.getFolder().getCustomers().size()).isEqualTo(2);
        assertThat(affiliation.getFolder().getCustomers()).contains(existingCustomer);
    }

    @PersistenceContext
    private EntityManager em;

    protected void flushClear() {
        em.flush();
        em.clear();
    }
}
