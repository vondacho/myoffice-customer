package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderStateRepository;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
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
import java.util.UUID;

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
    public Optional<Folder> findOne(UUID id) {
        return repository
                .findById(id)
                .map(state -> Folder.ofValid(state.getId(), state));
    }

    @Override
    public List<Folder> findAll(Specification specification) {
        return repository
                .findAll(specification)
                .stream()
                .map(state -> Folder.ofValid(state.getId(), state))
                .collect(toList());
    }

    @Override
    public Page<Folder> findAll(Pageable pageable) {
        return repository
                .findAll(pageable)
                .map(state -> Folder.ofValid(state.getId(), state));
    }

    @Override
    public Folder save(Folder folder) {
        return save(folder.getId(), folder.getState());
    }

    @Override
    public void delete(UUID id) {
        repository
                .findById(id)
                .ifPresent(folder -> repository.delete(folder));
    }

    @Override
    public Folder save(UUID id, FolderState state) {
        return Folder.ofValid(id, repository.save(toJpaEntity(state).setId(id)));
    }
}
