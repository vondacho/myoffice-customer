package edu.noia.myoffice.customer.rest.hateoas;

import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.rest.endpoint.CustomerResource;
import edu.noia.myoffice.customer.rest.endpoint.FolderResource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class AffiliationLinkProcessor implements ResourceProcessor<Resource<Affiliation>> {

    @Override
    public Resource<Affiliation> process(Resource<Affiliation> affiliationResource) {
        affiliationResource.add(linkTo(CustomerResource.class).slash(affiliationResource.getContent().getCustomer().getId()).withRel("customer"));
        affiliationResource.add(linkTo(FolderResource.class).slash(affiliationResource.getContent().getFolder().getId()).withRel("folder"));
        return affiliationResource;
    }
}
