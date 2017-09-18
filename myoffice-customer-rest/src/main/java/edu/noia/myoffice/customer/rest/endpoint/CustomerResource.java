package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.CustomerDataService;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.AffiliateVO;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import edu.noia.myoffice.customer.domain.vo.CustomerVO;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.util.Pair;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static edu.noia.myoffice.customer.rest.endpoint.CustomerResource.CUSTOMER_ENDPOINT_PATH;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = CUSTOMER_ENDPOINT_PATH)
public class CustomerResource {

    public static final String CUSTOMER_ENDPOINT_PATH = "/customers";

    @NonNull
    private CustomerService service;
    @NonNull
    private CustomerDataService dataService;
    @NonNull
    private CustomerRepository repository;
    @NonNull
    private ResourceProcessor<Resource<Customer>> customerProcessor;
    @NonNull
    private ResourceProcessor<Resource<Affiliation>> affiliationProcessor;

    @PostMapping
    public ResponseEntity<Resource<Affiliation>> create(@RequestBody @Valid CustomerVO customer) {
        return new ResponseEntity(
                affiliationProcessor.process(new Resource<>(service.create(customer))),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Customer>> modify(@PathVariable("id") Customer customer) {
        return ok(customerProcessor.process(new Resource<>(repository.save(customer))));
    }

    @PostMapping("/{id}/folders")
    public ResponseEntity<Resource<Affiliation>> affiliate(
            @PathVariable("id") UUID customerId,
            @RequestBody @Valid AffiliationVO input) {
        Resource<Affiliation> affiliation = new Resource<>(service.affiliate(customerId, input.getFolder()));
        return ok(affiliationProcessor.process(affiliation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Customer>> findOne(@PathVariable("id") Customer customer) {
        return ok(customerProcessor.process(new Resource<>(customer)));
    }

    @GetMapping("/{id}/folders")
    public ResponseEntity getFolders(
            @PathVariable("id") UUID customer) {
        return ok(dataService.findAllFolders(customer)
                .stream()
                .map(pair -> pair.getFirst())
                .map(Resource<FolderVO>::new)
                .collect(toList()));
    }

    @GetMapping
    public ResponseEntity<Page<Resource<Customer>>> findAll(Pageable pageable) {
        return ok(repository
                .findAll(pageable)
                .map(Resource<Customer>::new)
                .map(customerProcessor::process));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Customer customer) {
        repository.delete(customer.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/folderize")
    public ResponseEntity<?> folderize() {
        service.folderize();
        return ok().build();
    }

    @PostMapping("/sanitize")
    public ResponseEntity<?> sanitize() {
        service.sanitize();
        return ok().build();
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(UUID.class,
                new IdentifiantPropertyEditorSupport<>(UUID::fromString));

        binder.registerCustomEditor(Customer.class,
                new EntityPropertyEditorSupport<>(UUID::fromString,
                        repository::findOne,
                        UUID::toString,
                        (Customer customer) -> customer.getId(),
                        Customer.class));
    }
}
