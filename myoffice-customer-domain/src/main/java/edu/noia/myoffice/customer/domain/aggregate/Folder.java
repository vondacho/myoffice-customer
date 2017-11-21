package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.validation.BeanValidator;
import edu.noia.myoffice.customer.domain.vo.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

@EqualsAndHashCode(of = "id", callSuper = false)
@RequiredArgsConstructor(staticName = "ofValid")
@FieldDefaults(level = AccessLevel.PACKAGE)
public class Folder {

    @Getter
    @NonNull
    FolderId id;
    @NonNull
    FolderState state;

    public static Folder of(@NonNull FolderState state) {
        return of(identify(), state);
    }

    public static Folder of(@NonNull FolderId id, @NonNull FolderState state) {
        return ofValid(id, validate(state));
    }

    public FolderState getState() {
        return FolderSample.of(state);
    }

    public Set<Affiliate> getAffiliates() {
        return unmodifiableSet(state.getAffiliates());
    }

    private static MutableFolderState toMutable(FolderState state) {
        return state instanceof MutableFolderState ? (MutableFolderState)state : MutableFolderSample.of(state);
    }

    public Folder affiliate(Affiliate affiliate) {
        state = toMutable(state).add(validate(affiliate));
        return this;
    }

    private static FolderId identify() {
        return FolderId.random();
    }

    public Folder modify(FolderState modifier) {
        state = toMutable(state).modify(validate(modifier));
        return this;
    }

    public Folder modify(Affiliate modifier) {
        state = toMutable(state).remove(modifier).add(validate(modifier));
        return this;
    }

    public Folder patch(FolderState modifier) {
        state = validate(toMutable(state).patch(modifier));
        return this;
    }

    public Folder save(FolderRepository repository) {
        return repository.save(getId(), state);
    }

    private static <T> T validate(T state) {
        return BeanValidator.validate(state);
    }

    public Folder affiliate(CustomerId customerId) {
        return affiliate(Affiliate.of(customerId));
    }

    public Folder unaffiliate(CustomerId customerId) {
        state = toMutable(state).remove(Affiliate.of(customerId));
        return this;
    }
}
