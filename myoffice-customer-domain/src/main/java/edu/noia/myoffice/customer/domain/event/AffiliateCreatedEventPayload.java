package edu.noia.myoffice.customer.domain.event;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AffiliateCreatedEventPayload implements EventPayload {

    @NonNull
    FolderId folderId;
    @NonNull
    CustomerId customerId;
}
