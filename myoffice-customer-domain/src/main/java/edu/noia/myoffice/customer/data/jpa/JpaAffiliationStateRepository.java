package edu.noia.myoffice.customer.data.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaAffiliationStateRepository extends CrudRepository<JpaAffiliationState, Long> {

    Optional<JpaAffiliationState> findByCustomerAndFolder(UUID customer, UUID folder);

    List<JpaAffiliationState> findByCustomer(UUID customer);

    List<JpaAffiliationState> findByFolder(UUID folder);

    void deleteByCustomerAndFolder(UUID customer, UUID folder);
}
