package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

@EqualsAndHashCode(exclude = "affiliates", callSuper = false, doNotUseGetters = true)
@Accessors(chain = true)
@Setter
@Getter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class FolderSample implements FolderState {

    @NonNull
    String name;
    String notes;
    Set<Affiliate> affiliates = new HashSet<>();

    public static FolderSample of(FolderState state) {
        return FolderSample.of(state.getName())
                .setName(state.getName())
                .setNotes(state.getNotes())
                .setAffiliates(state.getAffiliates());
    }

    @Override
    public FolderState modify(EntityState modifier) {
        return modifier instanceof FolderState ? modify((FolderState) modifier) : this;
    }

    @Override
    public FolderState patch(EntityState modifier) {
        return modifier instanceof FolderState ? patch((FolderState) modifier) : this;
    }

    @Override
    public FolderSample addAffiliate(Affiliate affiliate) {
        getAffiliates().add(affiliate);
        return this;
    }

    @Override
    public FolderSample addAffiliates(Collection<Affiliate> affiliates) {
        getAffiliates().addAll(affiliates);
        return this;
    }

    @Override
    public FolderSample removeAffiliate(Affiliate affiliate) {
        if (!getAffiliates().remove(affiliate)) {
            throw notFound(Affiliate.class, affiliate.getCustomerId()).get();
        }
        return this;
    }

    @Override
    public FolderSample clearAffiliates() {
        getAffiliates().clear();
        return this;
    }
}
