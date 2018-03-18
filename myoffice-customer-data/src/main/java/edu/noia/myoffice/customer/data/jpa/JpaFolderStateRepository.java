package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.customer.domain.vo.FolderId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.List;
import java.util.Optional;

public interface JpaFolderStateRepository extends
        PagingAndSortingRepository<JpaFolderState, Long>,
        JpaSpecificationExecutor<JpaFolderState>,
        RevisionRepository<JpaFolderState, Long, Integer> {

    Optional<JpaFolderState> findById(FolderId id);

    List<JpaFolderState> findAll(Specification specification);

    Page<JpaFolderState> findAll(Specification specification, Pageable pageable);
}
