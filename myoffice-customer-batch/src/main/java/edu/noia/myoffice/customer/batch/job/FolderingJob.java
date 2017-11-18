package edu.noia.myoffice.customer.batch.job;

import edu.noia.myoffice.customer.batch.util.ChunkPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FolderingJob {
    @Autowired
    private FolderingChunk folderingChunk;

    public void execute() {
        Pageable pageable = new ChunkPageable();
        do {
            pageable = folderingChunk.execute(pageable).orElse(null);
        }
        while (pageable != null);
    }

}
