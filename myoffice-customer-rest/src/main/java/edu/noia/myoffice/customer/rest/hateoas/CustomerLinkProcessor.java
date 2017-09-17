package edu.noia.myoffice.customer.rest.hateoas;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.rest.endpoint.CustomerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CustomerLinkProcessor implements ResourceProcessor<Resource<Customer>> {

    @Override
    public Resource<Customer> process(Resource<Customer> customerResource) {
        customerResource.add(linkTo(CustomerResource.class).slash(customerResource.getContent().getId()).withSelfRel());
        return customerResource;
    }
}
