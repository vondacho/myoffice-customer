package edu.noia.myoffice.customer.messaging.listener;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class CustomerDomainEventListener {

    @TransactionalEventListener({BaseEvent.class})
    public void handleEvent(BaseEvent event) {
        LOG.debug(event.toString());
    }
}
