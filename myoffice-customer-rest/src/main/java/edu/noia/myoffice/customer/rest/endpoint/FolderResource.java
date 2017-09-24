package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.service.CustomerDataService;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;
import java.util.stream.Collectors;

import static edu.noia.myoffice.customer.rest.endpoint.FolderResource.FOLDER_ENDPOINT_PATH;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = FOLDER_ENDPOINT_PATH)
public class FolderResource {

    public static final String FOLDER_ENDPOINT_PATH = "/api/customer/v1/folders";

    @NonNull
    private CustomerService service;
    @NonNull
    private CustomerDataService dataService;
    @NonNull
    private FolderRepository repository;
    @NonNull
    private ResourceProcessor<Resource<Folder>> folderProcessor;
    @NonNull
    private ResourceProcessor<Resource<Affiliation>> affiliationProcessor;

    @PostMapping
    public ResponseEntity<Resource<Folder>> create(@RequestBody @Valid FolderVO input) {
        return new ResponseEntity(
                folderProcessor.process(new Resource<>(service.create(input))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Folder>> modify(
            @PathVariable("id") UUID folderId,
            @RequestBody @Valid FolderVO input) {

        return ok(folderProcessor.process(new Resource<>(service.modify(folderId, input))));
    }

    @PostMapping("/{id}/customers")
    public ResponseEntity<Resource<Affiliation>> affiliate(
            @PathVariable("id") UUID folderId,
            @RequestBody @Valid AffiliationVO input) {
        Resource<Affiliation> affiliation = new Resource<>(service.affiliate(input.getCustomer(), folderId));
        return ok(affiliationProcessor.process(affiliation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Folder>> findOne(@PathVariable("id") Folder folder) {
        return ok(folderProcessor.process(new Resource<>(folder)));
    }

    @GetMapping
    public ResponseEntity<Page<Resource<Folder>>> findAll(Pageable pageable) {
        return ok(repository
                .findAll(pageable)
                .map(Resource<Folder>::new)
                .map(folderProcessor::process));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Folder folder) {
        repository.delete(folder.getId());
        return ok().build();
    }

    @GetMapping("/{id}/customers")
    public ResponseEntity getCustomers(@PathVariable("id") UUID folder) {
        return ok(dataService.findAllCustomers(folder)
                .stream()
                .map(pair -> pair.getFirst())
                .map(Resource<CustomerVO>::new)
                .collect(Collectors.toList()));
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(UUID.class,
                new IdentifiantPropertyEditorSupport<>(UUID::fromString));

        binder.registerCustomEditor(Folder.class,
                new EntityPropertyEditorSupport<>(UUID::fromString,
                        repository::findOne,
                        UUID::toString,
                        (Folder folder) -> folder.getId(),
                        Folder.class));
    }
}
