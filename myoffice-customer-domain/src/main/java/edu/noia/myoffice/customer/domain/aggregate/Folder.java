package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.common.domain.entity.BaseEntity;
import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.util.validation.BeanValidator;
import edu.noia.myoffice.customer.domain.event.AffiliateCreatedEventPayload;
import edu.noia.myoffice.customer.domain.event.AffiliateRemovedEventPayload;
import edu.noia.myoffice.customer.domain.event.FolderCreatedEventPayload;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@Slf4j
@EqualsAndHashCode(callSuper = true)
public class Folder extends BaseEntity<Folder, FolderId, FolderState> {

    protected Folder(FolderId id, FolderState state) {
        super(id, state);
    }

    public static Folder of(@NonNull FolderState state) {
        return of(FolderId.random(), state);
    }

    public static Folder of(@NonNull FolderId id, @NonNull FolderState state) {
        return ofValid(id, FolderSample.of(validateState(state)));
    }

    public static Folder ofValid(@NonNull FolderId id, @NonNull FolderState state) {
        Folder folder = new Folder(id, FolderSample.of(state));
        return folder.andEvent(BaseEvent.of(FolderCreatedEventPayload.of(id, (FolderSample) folder.state)));
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
        state.remove(modifier).add(modifier);
        return this;
    }

    @Override
    public void validate(FolderState state) {
        validateState(state);
    }

    public Folder affiliate(Affiliate affiliate) {
        affiliate = validate(affiliate);
        state.add(affiliate);
        return andEvent(BaseEvent.of(AffiliateCreatedEventPayload.of(getId(), affiliate.getCustomerId())));
    }

    public Folder unaffiliate(CustomerId customerId) {
        state.remove(Affiliate.of(customerId));
        return andEvent(BaseEvent.of(AffiliateRemovedEventPayload.of(getId(), customerId)));
    }

    private Affiliate validate(Affiliate affiliate) {
        return validateState(affiliate);
    }

    @Override
    protected FolderState cloneState() {
        return FolderSample.of(state);
    }
}
