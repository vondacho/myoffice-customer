package edu.noia.myoffice.customer.batch.job;

import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class SanityChunkExecutor {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<Pageable> execute(Pageable pageable) {
        return Optional.empty();
        /* TODO
        Page<Customer> page = customerRepository.findAll(pageable);
        page.forEach(Customer::sanitize);
        return page.isLast() ? Optional.empty() : Optional.of(pageable.next());
        */
    }
}
