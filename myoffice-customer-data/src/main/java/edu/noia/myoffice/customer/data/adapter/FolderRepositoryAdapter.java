package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.common.util.search.FindCriteria;
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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderRepositoryAdapter implements FolderRepository {

    @NonNull
    JpaFolderStateRepository repository;

    private static JpaFolderState toJpaEntity(FolderState state) {
        return state instanceof JpaFolderState ? (JpaFolderState)state : JpaFolderState.of(state);
    }

    @Override
    public List<Folder> findByCriteria(List<FindCriteria> criteria) {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public Optional<Folder> findOne(FolderId id) {
        return repository
                .findById(id.getId())
                .map(this::toFolder);
    }

    @Override
    public Folder save(FolderId id, FolderState state) {
        return new PersistedFolder(id, repository.save(toJpaEntity(state).setId(id.getId())));
    }

    @Override
    public void delete(FolderId id) {
        repository
                .findById(id.getId())
                .ifPresent(folder -> repository.delete(folder));
    }

    private Folder toFolder(JpaFolderState state) {
        return new PersistedFolder(FolderId.of(state.getId()), state);
    }

    private class PersistedFolder extends Folder {
        private PersistedFolder(FolderId id, FolderState state) {
            super(id, state);
        }
    }

}
