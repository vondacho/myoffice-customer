package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.customer.data.jpa.JpaPersistentEventRepository;
import edu.noia.myoffice.customer.messaging.store.InternalEventStore;
import edu.noia.myoffice.customer.messaging.store.PersistentEvent;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InternalEventStoreAdapter implements InternalEventStore {

    @NonNull
    JpaPersistentEventRepository repository;

    @Override
    public List<PersistentEvent> listPending(int count) {
        //TODO
        return Collections.emptyList();
    }

    @Override
    public void accept(Event event) {
        //TODO
    }
}
