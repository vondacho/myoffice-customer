package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

public interface FolderState extends EntityState {

    @NotNull
    String getName();
    String getNotes();
    @Valid
    Set<Affiliate> getAffiliates();
}
