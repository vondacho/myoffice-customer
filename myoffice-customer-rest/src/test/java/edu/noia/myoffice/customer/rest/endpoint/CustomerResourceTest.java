package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import edu.noia.myoffice.customer.rest.hateoas.CustomerLinkProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;

import static edu.noia.myoffice.customer.rest.util.test.CustomerRestTestObjectFactory.CUSTOMER_TEST_OBJECT;
import static edu.noia.myoffice.customer.rest.util.test.FolderRestTestObjectFactory.FOLDER_TEST_OBJECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class CustomerResourceTest {
    @Mock
    private CustomerService service;
    @Mock
    private CustomerRepository repository;
    @Mock
    private CustomerLinkProcessor linkProcessor;
    @InjectMocks
    private CustomerResource endpoint;

    @Test
    public void create_should_call_service_with_expected_parameters() throws Exception {
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

        given(service.create(data)).willReturn(Affiliation.of(FOLDER_TEST_OBJECT, CUSTOMER_TEST_OBJECT));
        given(linkProcessor.process(any(Resource.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        // When
        Resource<Affiliation> affiliation = endpoint.create(data).getBody();

        // Then
        verify(service).create(data);
        assertThat(affiliation).isNotNull();
        assertThat(affiliation.getContent().getCustomer()).isNotNull();

        CustomerState cstate = affiliation.getContent().getCustomer().getState();
        assertThat(cstate).isEqualToIgnoringGivenFields(data,
                "id", "folder", "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy", "primaryId");
        assertThat(cstate.getId()).isNotNull();

        FolderState fstate = affiliation.getContent().getFolder().getState();
        assertThat(fstate.getId()).isNotNull();
        assertThat(fstate.getName()).isNotNull();
    }
}