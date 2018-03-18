package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.common.data.jpa.util.SpecificationBuilder;
import edu.noia.myoffice.common.util.search.FindCriteria;
import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderStateRepository;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FolderRepositoryAdapter implements FolderRepository {

    @NonNull
    JpaFolderStateRepository repository;

    private static JpaFolderState toJpaEntity(FolderState state) {
        return state instanceof JpaFolderState ? (JpaFolderState) state : JpaFolderState.of(state);
    }

    @Override
    public List<Folder> findByCriteria(List<FindCriteria> criteria) {
        SpecificationBuilder builder = new SpecificationBuilder();
        criteria.forEach(builder::with);
        return repository
                .findAll(builder.build())
                .stream()
                .map(this::toFolder)
                .collect(toList());
    }

    @Override
    public Optional<Folder> findOne(FolderId id) {
        return repository
                .findById(id)
                .map(this::toFolder);
    }

    @Override
    public List<Folder> findAllByAffiliate(CustomerId customerId) {
        Specification<JpaFolderState> specification = (root, query, criteriaBuilder) ->
                criteriaBuilder.isMember(Affiliate.of(customerId), root.get("affiliates"));

        return repository.findAll(specification)
                .stream()
                .map(this::toFolder)
                .collect(toList());
    }

    @Override
    public Folder save(FolderId id, FolderState state) {
        return new PersistedFolder(id, repository.save(toJpaEntity(state).setId(id)));
    }

    @Override
    public void delete(FolderId id) {
        repository
                .findById(id)
                .ifPresent(folder -> repository.delete(folder));
    }

    private Folder toFolder(JpaFolderState state) {
        return new PersistedFolder(state.getId(), state);
    }

    private class PersistedFolder extends Folder {
        private PersistedFolder(FolderId id, FolderState state) {
            super(id, state);
        }
    }

}
