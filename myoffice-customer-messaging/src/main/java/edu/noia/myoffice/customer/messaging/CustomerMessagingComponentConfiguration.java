package edu.noia.myoffice.customer.messaging;

import edu.noia.myoffice.common.event.scheduler.PublishPendingEventScheduler;
import edu.noia.myoffice.common.event.store.ExternalEventStore;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@EnableScheduling
@ComponentScan
@Configuration
public class CustomerMessagingComponentConfiguration {

    @Bean
    public ExternalEventStore externalEventStore() {
        return event -> {
            LOG.debug("Event {} has been published to external event store", event);
        };
    }

    @Bean
    public PublishPendingEventScheduler externalEventStorePublisher(
            InternalEventStore internalEventStore,
            ExternalEventStore externalEventStore) {
        return new PublishPendingEventScheduler(internalEventStore, externalEventStore);
    }
}
