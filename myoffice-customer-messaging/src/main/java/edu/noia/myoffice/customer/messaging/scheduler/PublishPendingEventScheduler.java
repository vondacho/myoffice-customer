package edu.noia.myoffice.customer.messaging.scheduler;

import edu.noia.myoffice.customer.messaging.store.ExternalEventStore;
import edu.noia.myoffice.customer.messaging.store.InternalEventStore;
import edu.noia.myoffice.customer.messaging.store.PersistentEvent;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublishPendingEventScheduler {

    @NonNull
    InternalEventStore eventStore;
    @NonNull
    ExternalEventStore publisher;

    @Scheduled(initialDelay = 3000, fixedDelayString = "${events.publisher.freq:3000}")
    public void sendEvents() {
        eventStore.listPending(100).forEach(this::publish);
    }

    private void publish(PersistentEvent event) {
        publisher.accept(event);
        event.sent(LocalDateTime.now());
    }
}
