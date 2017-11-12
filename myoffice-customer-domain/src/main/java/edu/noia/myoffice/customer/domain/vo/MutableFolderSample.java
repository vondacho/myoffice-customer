package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.aggregate.MutableFolderState;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(exclude = "affiliates", callSuper = false)
@Accessors(chain = true)
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MutableFolderSample implements MutableFolderState {
    String name;
    String notes;
    Set<Affiliate> affiliates = new HashSet<>();

    public static MutableFolderState of(FolderState state) {
        return new MutableFolderSample().modify(state).add(state.getAffiliates());
    }
}
