package edu.noia.myoffice.customer.domain.event;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class FolderDeletedEventPayload implements EventPayload {

    @NonNull
    FolderId folderId;
}
