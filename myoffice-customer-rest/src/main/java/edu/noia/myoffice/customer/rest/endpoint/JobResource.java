package edu.noia.myoffice.customer.rest.endpoint;

import edu.noia.myoffice.customer.batch.job.FolderingJob;
import edu.noia.myoffice.customer.batch.job.SanityJob;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/customer/v1/jobs")
public class JobResource {
    @NonNull
    private SanityJob sanityJob;
    @NonNull
    private FolderingJob folderingJob;

    @PostMapping("/foldering")
    public ResponseEntity folderize() {
        folderingJob.execute();
        LOG.debug("A foldering job has been created");
        return status(NO_CONTENT).build();
    }

    @PostMapping("/sanity")
    public ResponseEntity sanitize(Pageable pageable) {
        sanityJob.execute();
        LOG.debug("A sanity job has been created");
        return status(NO_CONTENT).build();
    }
}
