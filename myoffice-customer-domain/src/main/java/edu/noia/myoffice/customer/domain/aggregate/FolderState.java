package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.entity.AffiliationState;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.NonNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface FolderState {

    UUID getId();

    FolderState setData(FolderVO data);

    FolderVO getData();

    <E extends AffiliationState> void add(E affiliation);

    <E extends AffiliationState> Set<E> getCustomers();
}
