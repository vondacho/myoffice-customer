package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.util.EntityPropertyEditorSupport;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.common.util.search.FindCriteria;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
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
    private CustomerRepository repository;
    @NonNull
    private ResourceProcessor<Resource<Customer>> customerProcessor;
    @NonNull
    private ResourceProcessor<Resource<Affiliation>> affiliationProcessor;

    @PostMapping
    public ResponseEntity<Resource<Affiliation>> create(@RequestBody CustomerSample input) {
        return
                status(CREATED)
                .body(affiliationProcessor.process(new Resource<>(service.create(input))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resource<Customer>> modify(
            @PathVariable("id") CustomerId customerId,
            @RequestBody CustomerSample input) {
        return ok(customerProcessor.process(new Resource<>(service.modify(customerId, input))));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Resource<Customer>> patch(
            @PathVariable("id") CustomerId customerId,
            @RequestBody CustomerSample input) {
        return ok(customerProcessor.process(new Resource<>(service.patch(customerId, input))));
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
    public ResponseEntity<List<Resource<Customer>>> findAll(
            @RequestParam(value = "filter", required = false) String filter,
            Pageable pageable) {

        if (StringUtils.hasText(filter)) {
            return ok(repository
                    .findByCriteria(FindCriteria.from(filter))
                    .stream()
                    .map(Resource<Customer>::new)
                    .map(customerProcessor::process)
                    .collect(toList()));
        }
        else {
            return ok(repository
                    .findByCriteria(FindCriteria.empty())
                    .stream()
                    .map(Resource<Customer>::new)
                    .map(customerProcessor::process)
                    .collect(toList()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Customer customer) {
        repository.delete(customer.getId());
        return ResponseEntity.ok().build();
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(CustomerId.class,
                new IdentifiantPropertyEditorSupport<>(
                        s-> CustomerId.of(UUID.fromString(s))));

        binder.registerCustomEditor(Customer.class,
                new EntityPropertyEditorSupport<>(
                        s-> CustomerId.of(UUID.fromString(s)),
                        repository::findOne,
                        id -> id.getId().toString(),
                        Customer::getId,
                        Customer.class));
    }
}
