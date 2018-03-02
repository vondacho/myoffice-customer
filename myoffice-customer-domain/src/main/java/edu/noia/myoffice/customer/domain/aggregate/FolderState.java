package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

public interface FolderState extends EntityState {

    @NotNull
    String getName();
    String getNotes();
    @Valid
    Set<Affiliate> getAffiliates();

    FolderState setName(String value);
    FolderState setNotes(String value);

    default FolderState modify(FolderState modifier) {
        return setName(modifier.getName()).setNotes(modifier.getNotes());
    }

    default FolderState patch(FolderState modifier) {
        return setName(modifier.getName() != null ? modifier.getName() : getName())
                .setNotes(modifier.getNotes() != null ? modifier.getName() : getNotes());
    }

    default FolderState add(Affiliate affiliate) {
        getAffiliates().add(affiliate);
        return this;
    }

    default FolderState add(Collection<Affiliate> affiliates) {
        getAffiliates().addAll(affiliates);
        return this;
    }

    default FolderState remove(Affiliate affiliate) {
        if (!getAffiliates().remove(affiliate)) {
            throw notFound(Affiliate.class, affiliate.getCustomerId()).get();
        }
        return this;
    }
}
