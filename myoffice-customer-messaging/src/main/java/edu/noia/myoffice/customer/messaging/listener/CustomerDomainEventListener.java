package edu.noia.myoffice.customer.messaging.listener;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.function.Consumer;

@Slf4j
@Component
public class CustomerDomainEventListener implements Consumer<BaseEvent> {

    @Autowired
    InternalEventStore internalEventStore;

    @TransactionalEventListener({BaseEvent.class})
    public void accept(BaseEvent event) {
        LOG.debug(event.toString());
        internalEventStore.accept(event);
    }
}
