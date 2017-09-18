package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface AffiliationRepository {

    @Transactional(readOnly = true)
    Optional<Affiliation> findOne(UUID customer, UUID folder);

    @Transactional(readOnly = true)
    List<Affiliation> findAllByCustomer(UUID customer);

    @Transactional(readOnly = true)
    List<Affiliation> findAllByFolder(UUID folder);

    Affiliation save(Affiliation affiliation);

    void delete(UUID customer, UUID folder);
}
