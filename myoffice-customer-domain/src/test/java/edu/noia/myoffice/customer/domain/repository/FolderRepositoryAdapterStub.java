package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FolderRepositoryAdapterStub implements FolderRepository {
    @Override
    public Optional<Folder> findOne(UUID id) {
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
    public Folder save(UUID id, FolderState state) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
