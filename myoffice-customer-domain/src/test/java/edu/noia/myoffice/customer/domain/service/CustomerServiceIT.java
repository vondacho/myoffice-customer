package edu.noia.myoffice.customer.domain.service;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.test.config.ITConfiguration;
import edu.noia.myoffice.customer.domain.test.util.TestCustomer;
import edu.noia.myoffice.customer.domain.test.util.TestFolder;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ITConfiguration.class})
public class CustomerServiceIT {

    @Autowired
    private CustomerService service;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FolderRepository folderRepository;

    @After
    public void rollback() {
        customerRepository.findAll().forEach(customer -> customerRepository.delete(customer.getId()));
        folderRepository.findAll().forEach(folder -> folderRepository.delete(folder.getId()));
    }

    @Test
    public void create_should_return_an_affiliation_of_the_customer_and_a_new_folder() {
        // Given
        CustomerState anyCustomer = TestCustomer.randomValid();
        // When
        Affiliation affiliation = service.create(anyCustomer);
        // Then
        assertThat(affiliation).isNotNull();
        assertThat(affiliation.getCustomer().getId()).isNotNull();
        assertThat(affiliation.getFolder().getId()).isNotNull();
        assertThat(customerRepository.findOne(affiliation.getCustomer().getId()).isPresent()).isTrue();
        Folder folder = folderRepository.findOne(affiliation.getFolder().getId()).orElse(null);
        assertThat(folder).isNotNull();
        assertThat(folder.getAffiliates()).contains(Affiliate.of(affiliation.getCustomer().getId()));
    }

    @Test
    public void create_in_an_existing_folder_should_return_an_affiliation_of_the_customer_and_this_folder() {
        // Given
        Folder anyFolder = folderRepository.save(TestFolder.random());
        CustomerState anyCustomer = TestCustomer.randomValid();
        // When
        Affiliation affiliation = service.create(anyCustomer, anyFolder.getId());
        // Then
        assertThat(affiliation).isNotNull();
        assertThat(affiliation.getCustomer().getId()).isNotNull();
        assertThat(affiliation.getFolder()).isEqualTo(anyFolder);
        assertThat(customerRepository.findOne(affiliation.getCustomer().getId()).isPresent()).isTrue();
        assertThat(folderRepository.findOne(affiliation.getFolder().getId()).get().getAffiliates())
                .contains(Affiliate.of(affiliation.getCustomer().getId()));
    }

    @Test
    public void affiliate_an_existing_customer_into_an_existing_folder_should_return_the_right_affiliation() {
        // Given
        Folder anyFolder = folderRepository.save(TestFolder.random());
        Customer anyCustomer = customerRepository.save(TestCustomer.random());
        // When
        Affiliation affiliation = service.affiliate(anyFolder.getId(), anyCustomer.getId());
        // Then
        assertThat(affiliation).isNotNull();
        assertThat(affiliation.getCustomer()).isEqualTo(anyCustomer);
        assertThat(affiliation.getFolder()).isEqualTo(anyFolder);
        assertThat(folderRepository.findOne(affiliation.getFolder().getId()).get().getAffiliates())
                .contains(Affiliate.of(affiliation.getCustomer().getId()));
    }
}
