package edu.noia.myoffice.customer.data;

import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyOfficeCustomerDataApplication.class})
public class MyOfficeCustomerDataApplicationIT {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FolderRepository folderRepository;

    @Test
    public void contextLoads() {
        Assertions.assertThat(customerRepository).isNotNull();
        Assertions.assertThat(folderRepository).isNotNull();
    }
}
