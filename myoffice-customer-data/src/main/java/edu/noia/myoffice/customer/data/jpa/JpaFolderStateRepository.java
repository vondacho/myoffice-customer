package edu.noia.myoffice.customer.data.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaFolderStateRepository extends
        PagingAndSortingRepository<JpaFolderState, Long>,
        JpaSpecificationExecutor<JpaFolderState> {

    Optional<JpaFolderState> findById(UUID id);

    List<JpaFolderState> findAll(Specification specification);

    Page<JpaFolderState> findAll(Specification specification, Pageable pageable);
}
