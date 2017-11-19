package edu.noia.myoffice.customer.data;

import edu.noia.myoffice.common.data.MyOfficeCommonDataApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MyOfficeCommonDataApplication.class)
@SpringBootApplication
public class MyOfficeCustomerDataApplication {
}