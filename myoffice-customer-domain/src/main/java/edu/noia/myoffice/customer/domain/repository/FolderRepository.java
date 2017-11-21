package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface FolderRepository {

    @Transactional(readOnly = true)
    Optional<Folder> findOne(FolderId id);

    @Transactional(readOnly = true)
    List<Folder> findAll(Specification specification);

    @Transactional(readOnly = true)
    Page<Folder> findAll(Pageable pageable);

    @Transactional(readOnly = true)
    default List<Folder> findAll() {
        return findAll(Specifications.where(null));
    }

    Folder save(Folder folder);

    Folder save(FolderId id, FolderState state);

    void delete(FolderId id);
}
