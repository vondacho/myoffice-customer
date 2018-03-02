package edu.noia.myoffice.customer.batch.job;

import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class FolderingChunkExecutor {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private FolderRepository folderRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Pageable> execute(Pageable pageable) {
        return Optional.empty();
        /* TODO
        Page<Customer> page = customerRepository.findAll(pageable);
        page.forEach(customer -> customer.folderize().save(folderRepository));
        return page.isLast() ? Optional.empty() : Optional.of(pageable.next());*/
    }
}
