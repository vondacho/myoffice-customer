package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
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
import java.util.List;
import java.util.UUID;

import static edu.noia.myoffice.customer.rest.endpoint.FolderResource.FOLDER_ENDPOINT_PATH;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = FOLDER_ENDPOINT_PATH)
public class FolderResource {

    public static final String FOLDER_ENDPOINT_PATH = "/folders";

    @NonNull
    private CustomerService service;
    @NonNull
    private FolderRepository repository;
    @NonNull
    private ResourceProcessor<Resource<Folder>> folderProcessor;
    @NonNull
    private ResourceProcessor<Resource<Customer>> customerProcessor;
    @NonNull
    private ResourceProcessor<Resource<Affiliation>> affiliationProcessor;

    @PostMapping
    public ResponseEntity<Resource<Folder>> create(@RequestBody @Valid FolderVO input) {
        return new ResponseEntity(
                folderProcessor.process(new Resource<>(service.createFolder(input))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Folder>> modify(
            @PathVariable("id") UUID folderId,
            @RequestBody @Valid FolderVO input) {

        return ok(folderProcessor.process(
                new Resource<>(
                        repository.save(
                                repository.findOne(folderId)
                                    .map(folder -> folder.assign(input))
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                String.format("No %s identified by %s has been found", Folder.class, folderId)))))));
    }

    @PostMapping("/{id}/customers")
    public ResponseEntity<Resource<Affiliation>> affiliate(
            @PathVariable("id") UUID folderId,
            @RequestBody @Valid CustomerVO input) {
        Resource<Affiliation> affiliation = new Resource<>(service.createInFolder(input, folderId));
        return ok(affiliationProcessor.process(affiliation));
    }

    @PutMapping("/{id}/customers/{customerId}")
    public ResponseEntity<Resource<Affiliation>> affiliate(
            @PathVariable("id") UUID folderId,
            @PathVariable("customerId") UUID customerId) {
        return ok(affiliationProcessor.process(new Resource<>(service.affiliate(folderId, customerId))));
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
    public ResponseEntity<?> delete(@PathVariable("id") Folder folder) {
        repository.delete(folder.getId());
        return ok().build();
    }

    @GetMapping("/{id}/customers")
    public ResponseEntity<List<Resource<Customer>>> getCustomers(@PathVariable("id") Folder folder) {
        return ok(folder.getCustomers()
                .stream()
                .map(Resource<Customer>::new)
                .map(customerProcessor::process)
                .collect(toList()));
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
