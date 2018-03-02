package edu.noia.myoffice.customer.messaging.store;

import java.util.function.Consumer;

public class ExternalEventStore implements Consumer<PersistentEvent> {
    @Override
    public void accept(PersistentEvent event) {
    }
}
