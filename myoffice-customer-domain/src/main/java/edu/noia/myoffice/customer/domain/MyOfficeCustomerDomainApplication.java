package edu.noia.myoffice.customer.domain;

import edu.noia.myoffice.common.domain.MyOfficeCommonDomainApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MyOfficeCommonDomainApplication.class)
@SpringBootApplication
public class MyOfficeCustomerDomainApplication {
}
