package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FolderRepositoryAdapterStub implements FolderRepository {
    @Override
    public Optional<Folder> findOne(FolderId id) {
        return null;
    }

    @Override
    public List<Folder> findAll(Specification specification) {
        return null;
    }

    @Override
    public Page<Folder> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Folder save(Folder folder) {
        return null;
    }

    @Override
    public Folder save(FolderId id, FolderState state) {
        return null;
    }

    @Override
    public void delete(FolderId id) {

    }
}
