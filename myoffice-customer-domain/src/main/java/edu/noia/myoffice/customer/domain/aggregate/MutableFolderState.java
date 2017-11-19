package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.exception.ResourceNotFoundException;
import edu.noia.myoffice.customer.domain.vo.Affiliate;

import java.util.Collection;

public interface MutableFolderState extends FolderState {

    MutableFolderState setName(String value);
    MutableFolderState setNotes(String value);

    default MutableFolderState modify(FolderState modifier) {
        return setName(modifier.getName()).setNotes(modifier.getNotes());
    }

    default MutableFolderState patch(FolderState modifier) {
        return setName(modifier.getName() != null ? modifier.getName() : getName())
            .setNotes(modifier.getNotes() != null ? modifier.getName() : getNotes());
    }

    default MutableFolderState add(Affiliate affiliate) {
        getAffiliates().add(affiliate);
        return this;
    }

    default MutableFolderState add(Collection<Affiliate> affiliates) {
        getAffiliates().addAll(affiliates);
        return this;
    }

    default MutableFolderState remove(Affiliate affiliate) {
        if (!getAffiliates().remove(affiliate)) {
            throw new ResourceNotFoundException(
                String.format("No %s identified by %s has been found", Affiliate.class, affiliate.getCustomerId()));
        }
        return this;
    }
}
