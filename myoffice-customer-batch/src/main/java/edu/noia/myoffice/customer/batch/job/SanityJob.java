package edu.noia.myoffice.customer.batch.job;

import edu.noia.myoffice.customer.batch.util.ChunkPageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SanityJob {

    @Autowired
    private SanityChunk sanityChunk;

    public void execute() {
        Pageable pageable = new ChunkPageable();
        do {
            pageable = sanityChunk.execute(pageable).orElse(null);
        }
        while (pageable != null);
    }
}
