package edu.noia.myoffice.customer.rest.hateoas;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.rest.endpoint.FolderEndpoint;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class FolderLinkProcessor implements ResourceProcessor<Resource<Folder>> {

    @Override
    public Resource<Folder> process(Resource<Folder> folderResource) {
        folderResource.add(linkTo(FolderEndpoint.class).slash(folderResource.getContent().getId().getId()).withSelfRel());
        return folderResource;
    }
}
