package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.customer.domain.vo.CustomerId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCustomerStateRepository extends
        PagingAndSortingRepository<JpaCustomerState, Long>,
        JpaSpecificationExecutor<JpaCustomerState>,
        RevisionRepository<JpaCustomerState, Long, Integer> {

    Optional<JpaCustomerState> findById(CustomerId uuid);

    List<JpaCustomerState> findAll(Specification specification);

    Page<JpaCustomerState> findAll(Specification specification, Pageable pageable);
}
