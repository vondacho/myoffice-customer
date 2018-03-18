package edu.noia.myoffice.customer.domain.test.util;

import edu.noia.myoffice.common.domain.repository.InMemoryKeyValueRepository;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class FolderRepositoryAdapterStub
        extends InMemoryKeyValueRepository<Folder, FolderId, FolderState>
        implements FolderRepository {

    public FolderRepositoryAdapterStub() {
        super((id, state) -> Folder.ofValid(id, state));
    }

    @Override
    public List<Folder> findAllByAffiliate(CustomerId customerId) {
        return store.entrySet()
                .stream()
                .filter(e -> e.getValue().getAffiliates().contains(Affiliate.of(customerId)))
                .map(e -> entityCreator.apply(e.getKey(), e.getValue()))
                .collect(toList());
    }
}
