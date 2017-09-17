package edu.noia.myoffice.customer.domain.aggregate;

import edu.noia.myoffice.customer.domain.entity.Affiliation;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@EqualsAndHashCode(of = "state", doNotUseGetters = true)
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Folder {

    @NonNull
    FolderState state;

    public UUID getId() {
        return state.getId();
    }

    public FolderVO getState() {
        return state.getData();
    }

    public Folder setState(FolderVO folder) {
        state.setData(folder);
        return this;
    }

    public List<Affiliation> getCustomers() {
        return state.getCustomers()
                .stream()
                .map(Affiliation::of)
                .collect(toList());
    }

    public Folder affiliate(Affiliation affiliation) {
        state.add(affiliation.getState());
        return this;
    }
}
