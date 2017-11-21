package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.service.CustomerDataService;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.*;
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

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customer/v1/folders")
public class FolderResource {

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
    public ResponseEntity<Resource<Folder>> create(@RequestBody MutableFolderSample input) {
        return status(HttpStatus.CREATED)
                .body(folderProcessor.process(new Resource<>(Folder.of(input).save(repository))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Folder>> modify(
            @PathVariable("id") FolderId folderId,
            @RequestBody MutableFolderSample input) {
        return ok(folderProcessor.process(new Resource<>(service.modify(folderId, input))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource<Folder>> patch(
            @PathVariable("id") FolderId folderId,
            @RequestBody MutableFolderSample input) {
        return ok(folderProcessor.process(new Resource<>(service.patch(folderId, input))));
    }

    @PutMapping("/{id}/customers")
    public ResponseEntity<Resource<Affiliation>> affiliate(
            @PathVariable("id") FolderId folderId,
            @RequestBody Affiliate input) {
        Resource<Affiliation> affiliation = new Resource<>(service.affiliate(folderId, input.getCustomerId()));
        return ok(affiliationProcessor.process(affiliation));
    }

    @DeleteMapping("/{id}/customers/{customerId}")
    public ResponseEntity<Resource<Affiliation>> unaffiliate(
            @PathVariable("id") FolderId folderId,
            @PathVariable CustomerId customerId) {
        service.unaffiliate(folderId, customerId);
        return status(HttpStatus.NO_CONTENT).build();
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

    @GetMapping("/{id}/customers")
    public ResponseEntity<List<Customer>> getCustomers(@PathVariable("id") FolderId folder) {
        return ok(dataService.findAllCustomers(folder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") FolderId folderId) {
        repository.delete(folderId);
        return status(HttpStatus.NO_CONTENT).build();
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(FolderId.class,
                new IdentifiantPropertyEditorSupport<>(
                        s-> FolderId.of(UUID.fromString(s))));

        binder.registerCustomEditor(CustomerId.class,
                new IdentifiantPropertyEditorSupport<>(
                        s-> CustomerId.of(UUID.fromString(s))));

        binder.registerCustomEditor(Folder.class,
                new EntityPropertyEditorSupport<>(
                        s-> FolderId.of(UUID.fromString(s)),
                        repository::findOne,
                        id -> id.getId().toString(),
                        Folder::getId,
                        Folder.class));
    }
}
