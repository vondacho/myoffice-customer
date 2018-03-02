package edu.noia.myoffice.customer.messaging.store;

import edu.noia.myoffice.common.domain.event.Event;

import java.util.List;
import java.util.function.Consumer;

public interface InternalEventStore extends Consumer<Event> {

    List<PersistentEvent> listPending(int count);
}
