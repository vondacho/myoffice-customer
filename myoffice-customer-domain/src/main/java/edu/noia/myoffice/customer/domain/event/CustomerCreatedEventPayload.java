package edu.noia.myoffice.customer.domain.event;

import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.customer.domain.vo.CustomerId;
import edu.noia.myoffice.customer.domain.vo.CustomerSample;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class CustomerCreatedEventPayload implements EventPayload {

    @NonNull
    CustomerId customerId;
    @NonNull
    CustomerSample customerState;
}
