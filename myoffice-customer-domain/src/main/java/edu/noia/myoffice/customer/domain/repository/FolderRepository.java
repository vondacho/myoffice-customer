package edu.noia.myoffice.customer.domain.repository;

import edu.noia.myoffice.common.domain.repository.EntityRepository;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;

import java.util.List;

public interface FolderRepository extends EntityRepository<Folder, FolderId, FolderState> {

    List<Folder> findAllByAffiliate(CustomerId customerId);
}
