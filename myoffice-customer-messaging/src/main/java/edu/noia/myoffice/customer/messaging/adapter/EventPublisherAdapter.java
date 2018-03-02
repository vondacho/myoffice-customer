package edu.noia.myoffice.customer.messaging.adapter;

import edu.noia.myoffice.common.domain.event.BaseEvent;
import edu.noia.myoffice.common.domain.event.EventPayload;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.time.Instant;

@RequiredArgsConstructor
public class EventPublisherAdapter implements EventPublisher {

    @NonNull
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void accept(EventPayload payload) {
        applicationEventPublisher.publishEvent(BaseEvent.of(payload, Instant.now()));
    }
}
