package edu.noia.myoffice.customer.messaging.listener;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DomainEventListener implements Consumer<Event> {

    @Autowired
    InternalEventStore internalEventStore;

    @EventListener(value = {Event.class})
    public void accept(Event event) {
        internalEventStore.accept(event);
    }
}
