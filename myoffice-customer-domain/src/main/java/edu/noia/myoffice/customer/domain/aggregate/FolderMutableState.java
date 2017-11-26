package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityMutableState;
import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.customer.domain.vo.Affiliate;

import java.util.Collection;

public interface FolderMutableState extends
        FolderState,
        EntityMutableState<FolderMutableState, FolderState> {

    FolderMutableState setName(String value);
    FolderMutableState setNotes(String value);

    default FolderMutableState modify(FolderState modifier) {
        return setName(modifier.getName()).setNotes(modifier.getNotes());
    }

    default FolderMutableState patch(FolderState modifier) {
        return setName(modifier.getName() != null ? modifier.getName() : getName())
            .setNotes(modifier.getNotes() != null ? modifier.getName() : getNotes());
    }

    default FolderMutableState add(Affiliate affiliate) {
        getAffiliates().add(affiliate);
        return this;
    }

    default FolderMutableState add(Collection<Affiliate> affiliates) {
        getAffiliates().addAll(affiliates);
        return this;
    }

    default FolderMutableState remove(Affiliate affiliate) {
        if (!getAffiliates().remove(affiliate)) {
            throw new ResourceNotFoundException(
                String.format("No %s identified by %s has been found", Affiliate.class, affiliate.getCustomerId()));
        }
        return this;
    }
}
