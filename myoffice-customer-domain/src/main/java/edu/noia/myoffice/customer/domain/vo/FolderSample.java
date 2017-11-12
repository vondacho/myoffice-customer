package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@EqualsAndHashCode(exclude = "affiliates", callSuper = false)
@Getter
@Builder(builderMethodName = "hiddenBuilder", toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class FolderSample implements FolderState {

    String name;
    String notes;
    @Singular Set<Affiliate> affiliates;

    public static FolderSample of(FolderState state) {
        return FolderSample.builder(state.getName())
                .name(state.getName())
                .notes(state.getNotes())
                .affiliates(state.getAffiliates())
                .build();
    }

    public static FolderSampleBuilder builder(@NonNull String name) {
        return hiddenBuilder().name(name);
    }
}
