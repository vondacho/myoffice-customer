package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.SearchCriteria;
import edu.noia.myoffice.common.rest.util.SpecificationBuilder;
import edu.noia.myoffice.customer.batch.job.FolderingJob;
import edu.noia.myoffice.customer.batch.job.SanityJob;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customer/v1/customers")
public class CustomerResource {

    @NonNull
    private CustomerService service;
    @NonNull
    private SanityJob sanityJob;
    @NonNull
    private FolderingJob folderingJob;
    @NonNull
    private CustomerRepository repository;
    @NonNull
    private ResourceProcessor<Resource<Customer>> customerProcessor;
    @NonNull
    private ResourceProcessor<Resource<Affiliation>> affiliationProcessor;

    @PostMapping
    public ResponseEntity<Resource<Affiliation>> create(@RequestBody CustomerState input) {
        return
                status(CREATED)
                .body(affiliationProcessor.process(new Resource<>(service.create(input))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Customer>> modify(
            @PathVariable("id") UUID customerId,
            @RequestBody CustomerState input) {
        return ok(customerProcessor.process(new Resource<>(service.modify(customerId, input))));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Customer>> findOne(@PathVariable("id") Customer customer) {
        return ok(customerProcessor.process(new Resource<>(customer)));
    }

    @GetMapping("/{id}/folders")
    public ResponseEntity<List<Folder>> getFolders(@PathVariable("id") UUID customerId) {
        return ok(Collections.emptyList());
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
        folderingJob.execute();
        return status(NO_CONTENT).build();
    }

    @PostMapping("/sanitize")
    public ResponseEntity sanitize(Pageable pageable) {
        sanityJob.execute();
        return status(NO_CONTENT).build();
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
