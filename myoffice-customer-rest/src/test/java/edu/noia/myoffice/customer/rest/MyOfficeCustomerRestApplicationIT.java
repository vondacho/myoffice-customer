package edu.noia.myoffice.customer.rest;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.noia.myoffice.customer.rest.endpoint.CustomerResource;
import edu.noia.myoffice.customer.rest.endpoint.FolderResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyOfficeCustomerRestApplication.class})
public class MyOfficeCustomerRestApplicationIT {

	@Autowired
	private CustomerResource customerResource;

	@Autowired
	private FolderResource folderResource;

	@Autowired
	private Module module;

	@Test
	public void contextLoads() {
		assertThat(customerResource).isNotNull();
		assertThat(folderResource).isNotNull();
		assertThat(module).isNotNull().isInstanceOf(SimpleModule.class);
	}
}
