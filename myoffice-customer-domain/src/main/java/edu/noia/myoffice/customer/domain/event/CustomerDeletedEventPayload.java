package edu.noia.myoffice.customer.domain.event;

import edu.noia.myoffice.customer.domain.vo.CustomerId;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class CustomerDeletedEventPayload {

    @NonNull
    CustomerId customerId;
}
