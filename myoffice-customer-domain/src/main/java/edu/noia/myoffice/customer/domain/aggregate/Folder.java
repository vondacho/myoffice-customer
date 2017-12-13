package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.util.BeanValidator;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.vo.*;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Slf4j
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(callSuper = true)
public class Folder extends BaseEntity<
        Folder,
        FolderId,
        FolderState,
        FolderMutableState,
        FolderRepository> {

    public static Folder of(@NonNull FolderState state) {
        return of(FolderId.random(), state);
    }

    public static Folder of(@NonNull FolderId id, @NonNull FolderState state) {
        return ofValid(id, validateState(state));
    }

    public static Folder ofValid(@NonNull FolderId id, @NonNull FolderState state) {
        return new Folder().setState(state).setId(id);
    }

    private static <T> T validateState(T state) {
        return BeanValidator.validate(state);
    }

    public Set<Affiliate> getAffiliates() {
        return unmodifiableSet(state.getAffiliates());
    }

    public Folder affiliate(CustomerId customerId) {
        return affiliate(Affiliate.of(customerId));
    }

    public Folder modify(Affiliate modifier) {
        modifier = validate(modifier);
        return setState(toMutable().remove(modifier).add(modifier));
    }

    public Folder affiliate(Affiliate affiliate) {
        affiliate = validate(affiliate);
        return setState(toMutable().add(affiliate));
    }

    public Folder unaffiliate(CustomerId customerId) {
        return setState(toMutable().remove(Affiliate.of(customerId)));
    }

    @Override
    protected FolderMutableState toMutableState(FolderState state) {
        return FolderMutableSample.of(state);
    }

    @Override
    protected FolderState toImmutableState(FolderState state) {
        return FolderSample.of(state);
    }

    @Override
    protected FolderId identify() {
        return FolderId.random();
    }

    @Override
    protected FolderState validate(FolderState state) {
        return validateState(state);
    }

    private Affiliate validate(Affiliate affiliate) {
        return validateState(affiliate);
    }
}
