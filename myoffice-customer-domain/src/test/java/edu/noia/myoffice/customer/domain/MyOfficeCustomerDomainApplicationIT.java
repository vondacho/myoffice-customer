package edu.noia.myoffice.customer.domain;

import edu.noia.myoffice.customer.data.MyOfficeCustomerDataApplication;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        MyOfficeCustomerDomainApplication.class,
        MyOfficeCustomerDataApplication.class})
public class MyOfficeCustomerDomainApplicationIT {

    @Autowired
    private CustomerService customerService;

    @Test
    public void contextLoads() {
        assertThat(customerService).isNotNull();
    }
}
