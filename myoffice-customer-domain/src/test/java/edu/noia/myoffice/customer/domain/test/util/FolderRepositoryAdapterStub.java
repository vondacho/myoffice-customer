package edu.noia.myoffice.customer.domain.test.util;

import edu.noia.myoffice.common.domain.repository.InMemoryKeyValueRepository;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.FolderId;

public class FolderRepositoryAdapterStub
        extends InMemoryKeyValueRepository<Folder, FolderId, FolderState>
        implements FolderRepository {

    public FolderRepositoryAdapterStub() {
        super((id, state) -> Folder.ofValid(id, state));
    }
}
