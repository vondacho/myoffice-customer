package edu.noia.myoffice.customer.ui;

import edu.noia.myoffice.customer.rest.MyOfficeCustomerRestApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({
		MyOfficeCustomerRestApplication.class,
})
@SpringBootApplication
public class MyOfficeCustomerUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyOfficeCustomerUiApplication.class, args);
	}
}
