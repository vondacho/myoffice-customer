package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

public interface FolderState extends EntityState {

    @NotNull
    String getName();

    FolderState setName(String value);

    String getNotes();

    FolderState setNotes(String value);

    @Valid
    Set<Affiliate> getAffiliates();

    default FolderState modify(FolderState modifier) {
        return setName(modifier.getName())
                .setNotes(modifier.getNotes())
                .clearAffiliates()
                .addAffiliates(modifier.getAffiliates());
    }

    default FolderState patch(FolderState modifier) {
        return setName(modifier.getName() != null ? modifier.getName() : getName())
                .setNotes(modifier.getNotes() != null ? modifier.getNotes() : getNotes())
                .addAffiliates(modifier.getAffiliates());
    }

    FolderState addAffiliate(Affiliate affiliate);

    FolderState addAffiliates(Collection<Affiliate> affiliates);

    FolderState removeAffiliate(Affiliate affiliate);

    FolderState clearAffiliates();
}
