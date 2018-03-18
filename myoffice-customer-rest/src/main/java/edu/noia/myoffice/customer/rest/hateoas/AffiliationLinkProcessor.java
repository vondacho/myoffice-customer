package edu.noia.myoffice.customer.rest.hateoas;

import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.rest.endpoint.CustomerEndpoint;
import edu.noia.myoffice.customer.rest.endpoint.FolderEndpoint;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class AffiliationLinkProcessor implements ResourceProcessor<Resource<Affiliation>> {

    @Override
    public Resource<Affiliation> process(Resource<Affiliation> affiliationResource) {
        Affiliation data = affiliationResource.getContent();
        affiliationResource.add(linkTo(CustomerEndpoint.class).slash(data.getCustomer().getId().getId()).withRel("customer"));
        affiliationResource.add(linkTo(FolderEndpoint.class).slash(data.getFolder().getId().getId()).withRel("folder"));
        return affiliationResource;
    }
}
