package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderStateRepository;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderRepositoryAdapter implements FolderRepository {

    @NonNull
    JpaFolderStateRepository repository;

    private static JpaFolderState toJpaEntity(FolderState state) {
        return state instanceof JpaFolderState ? (JpaFolderState)state : JpaFolderState.of(state);
    }

    @Override
    public Optional<Folder> findOne(FolderId id) {
        return repository
                .findById(id.getId())
                .map(this::toFolder);
    }

    @Override
    public List<Folder> findAll(Specification specification) {
        return repository
                .findAll(specification)
                .stream()
                .map(this::toFolder)
                .collect(toList());
    }

    @Override
    public Page<Folder> findAll(Specification specification, Pageable pageable) {
        return repository
                .findAll(specification, pageable)
                .map(this::toFolder);
    }

    @Override
    public Folder save(Folder folder) {
        return save(folder.getId(), folder.getState());
    }

    @Override
    public Folder save(FolderId id, FolderState state) {
        return Folder.ofValid(id, repository.save(toJpaEntity(state).setId(id.getId())));
    }

    @Override
    public void delete(FolderId id) {
        repository
                .findById(id.getId())
                .ifPresent(folder -> repository.delete(folder));
    }

    @Override
    public Page<Folder> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(this::toFolder);
    }

    private Folder toFolder(JpaFolderState state) {
        return Folder.ofValid(FolderId.of(state.getId()), state);
    }

}
