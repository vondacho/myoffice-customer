package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.testing.TransactionalEndpointITBase;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import org.junit.Test;

import static edu.noia.myoffice.customer.rest.endpoint.CustomerResource.CUSTOMER_ENDPOINT_PATH;
import static edu.noia.myoffice.customer.rest.util.test.CustomerRestTestObjectFactory.CUSTOMER_TEST_OBJECT;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerResourceIT extends TransactionalEndpointITBase {

    @Test
    public void create_should_return_an_affiliation_of_one_customer_in_one_folder() throws Exception {
        // Given
        CustomerState customerState = CUSTOMER_TEST_OBJECT.getState();
        CustomerVO data = new CustomerVO(
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
        doRequest(CUSTOMER_ENDPOINT_PATH, POST, data, ra -> ra
        // Then
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.folder.state.name").isNotEmpty())
        .andExpect(jsonPath("$.customer.state.salutation").value(customerState.getSalutation()))
        .andExpect(jsonPath("$.customer.state.firstName").value(customerState.getFirstName()))
        .andExpect(jsonPath("$.customer.state.lastName").value(customerState.getLastName()))
        .andExpect(jsonPath("$.customer.state.zip").value(customerState.getZip()))
        .andExpect(jsonPath("$.customer.state.city").value(customerState.getCity()))
        .andExpect(jsonPath("$.customer.state.region").value(customerState.getRegion()))
        .andExpect(jsonPath("$._links.customer.href").isNotEmpty())
        .andExpect(jsonPath("$._links.folder.href").isNotEmpty()));
    }
}
