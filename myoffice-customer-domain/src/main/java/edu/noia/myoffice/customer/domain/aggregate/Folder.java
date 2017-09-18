package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(of = {"state"}, doNotUseGetters = true, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Folder {

    @NonNull
    FolderState state;

    public UUID getId() {
        return state.getId();
    }

    public FolderState getState() {
        return state;
    }

    public Folder setData(FolderVO folder) {
        state.setData(folder);
        return this;
    }
}
