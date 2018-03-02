package edu.noia.myoffice.customer.batch.job;

import edu.noia.myoffice.common.util.data.ChunkPageable;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderingJob {

    @Autowired
    FolderingChunkExecutor chunkExecutor;

    @Async("batchingTaskExecutor")
    public void execute() {
        LOG.debug("Execution of foldering job has been started");
        Pageable pageable = new ChunkPageable();
        do {
            LOG.debug("Execution of next foldering job chunk is on going: {}", pageable.toString());
            pageable = chunkExecutor.execute(pageable).orElse(null);
        }
        while (pageable != null);
        LOG.debug("Execution of foldering job is terminated");
    }

}
