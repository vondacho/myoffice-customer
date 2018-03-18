package edu.noia.myoffice.customer.rest.hateoas;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.rest.endpoint.CustomerEndpoint;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class CustomerLinkProcessor implements ResourceProcessor<Resource<Customer>> {

    @Override
    public Resource<Customer> process(Resource<Customer> customerResource) {
        customerResource.add(linkTo(CustomerEndpoint.class).slash(customerResource.getContent().getId().getId()).withSelfRel());
        return customerResource;
    }
}
