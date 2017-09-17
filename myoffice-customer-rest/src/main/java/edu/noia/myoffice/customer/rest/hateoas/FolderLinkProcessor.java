package edu.noia.myoffice.customer.rest.hateoas;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.rest.endpoint.CustomerResource;
import edu.noia.myoffice.customer.rest.endpoint.FolderResource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class FolderLinkProcessor implements ResourceProcessor<Resource<Folder>> {

    @Override
    public Resource<Folder> process(Resource<Folder> folderResource) {
        folderResource.add(linkTo(FolderResource.class).slash(folderResource.getContent().getId()).withSelfRel());
        folderResource.add(
                folderResource.getContent()
                        .getCustomers()
                        .stream()
                        .map(Resource::new)
                        .map(customerResource -> linkTo(CustomerResource.class).slash(customerResource.getContent().getId()).withRel("customer"))
                        .collect(toList()));
        return folderResource;
    }
}
