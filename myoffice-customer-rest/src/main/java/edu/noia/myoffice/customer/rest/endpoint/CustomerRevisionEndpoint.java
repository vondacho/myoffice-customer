package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.common.rest.dto.RevisionDto;
import edu.noia.myoffice.common.rest.util.IdentifiantPropertyEditorSupport;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customer/v1/customers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRevisionEndpoint {

    @Autowired
    JpaCustomerStateRepository repository;

    @GetMapping(value = "/{id}/revisions")
    public ResponseEntity<List<RevisionDto>> getRevisions(@PathVariable(value = "id") Long primaryId) {
        return ok(RevisionDto.from(repository.findRevisions(primaryId)));
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.registerCustomEditor(CustomerId.class,
                new IdentifiantPropertyEditorSupport<>(s -> CustomerId.of(UUID.fromString(s))));
    }
}
