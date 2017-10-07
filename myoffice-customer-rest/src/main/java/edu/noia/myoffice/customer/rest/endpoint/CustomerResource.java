package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.SearchCriteria;
import edu.noia.myoffice.common.rest.util.SpecificationBuilder;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.CustomerDataService;
import edu.noia.myoffice.customer.domain.service.CustomerService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customer/v1/customers")
public class CustomerResource {

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
        return ResponseEntity
                .status(CREATED)
                .body(affiliationProcessor.process(new Resource<>(service.create(customer))));
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
                .map(Pair::getFirst)
                .map(Resource<FolderVO>::new)
                .collect(toList()));
    }

    @GetMapping
    public ResponseEntity<Page<Resource<Customer>>> findAll(
            @RequestParam(value = "filter", required = false) String filter,
            Pageable pageable) {

        if (StringUtils.hasText(filter)) {
            SpecificationBuilder<Customer> specificationBuilder = new SpecificationBuilder();
            SearchCriteria.of(filter).forEach(specificationBuilder::with);
            return ok(repository
                    .findAll(specificationBuilder.build(), pageable)
                    .map(Resource<Customer>::new)
                    .map(customerProcessor::process));
        }
        else {
            return ok(repository
                    .findAll(pageable)
                    .map(Resource<Customer>::new)
                    .map(customerProcessor::process));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Customer customer) {
        repository.delete(customer.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/folderize")
    public ResponseEntity folderize() {
        service.folderize();
        return ok().build();
    }

    @PostMapping("/sanitize")
    public ResponseEntity sanitize() {
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
                        Customer::getId,
                        Customer.class));
    }
}
