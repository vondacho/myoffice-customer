package edu.noia.myoffice.customer.messaging.adapter;

import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.common.domain.event.EventPublisher;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class EventPublisherAdapter implements EventPublisher {

    @NonNull
    ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void accept(Event event) {
        applicationEventPublisher.publishEvent(event);
    }
}
