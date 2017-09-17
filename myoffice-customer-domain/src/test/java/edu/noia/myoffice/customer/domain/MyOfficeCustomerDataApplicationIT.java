package edu.noia.myoffice.customer.domain;

import edu.noia.myoffice.customer.data.MyOfficeCustomerDataApplication;
import edu.noia.myoffice.customer.domain.factory.CustomerStateFactory;
import edu.noia.myoffice.customer.domain.factory.FolderStateFactory;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyOfficeCustomerDataApplication.class})
public class MyOfficeCustomerDataApplicationIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    private FolderStateFactory folderStateFactory;

    @Autowired
    private CustomerStateFactory customerStateFactory;

    @Test
    public void contextLoads() {
        assertThat(customerRepository).isNotNull();
        assertThat(folderRepository).isNotNull();
        assertThat(folderStateFactory).isNotNull();
        assertThat(customerStateFactory).isNotNull();
    }
}
