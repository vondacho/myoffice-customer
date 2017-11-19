package edu.noia.myoffice.customer.batch.job;

import edu.noia.myoffice.customer.batch.util.ChunkPageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SanityJob {

    @Autowired
    private SanityChunk sanityChunk;

    @Async("batchingTaskExecutor")
    public void execute() {
        LOG.debug("Execution of sanity job has been started");
        Pageable pageable = new ChunkPageable();
        do {
            LOG.debug("Execution of next sanity job chunk is on going: {}", pageable.toString());
            pageable = sanityChunk.execute(pageable).orElse(null);
        }
        while (pageable != null);
        LOG.debug("Execution of sanity job is terminated");
    }
}
