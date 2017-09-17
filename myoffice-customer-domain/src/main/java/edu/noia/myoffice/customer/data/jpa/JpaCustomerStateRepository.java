package edu.noia.myoffice.customer.data.jpa;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaCustomerStateRepository extends
        PagingAndSortingRepository<JpaCustomerState, Long>,
        JpaSpecificationExecutor<JpaCustomerState> {

    Optional<JpaCustomerState> findById(UUID uuid);

    List<JpaCustomerState> findAll(Specification specification);
}
