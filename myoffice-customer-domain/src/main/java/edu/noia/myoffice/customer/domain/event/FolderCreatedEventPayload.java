package edu.noia.myoffice.customer.domain.event;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import edu.noia.myoffice.customer.domain.vo.FolderSample;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class FolderCreatedEventPayload implements EventPayload {

    @NonNull
    FolderId folderId;
    @NonNull
    FolderSample folderSample;
}
