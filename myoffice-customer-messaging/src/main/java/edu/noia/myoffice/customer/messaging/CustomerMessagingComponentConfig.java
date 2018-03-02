package edu.noia.myoffice.customer.messaging;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.events.config.EnablePersistentDomainEvents;

@EnablePersistentDomainEvents
@Configuration
@ComponentScan
public class CustomerMessagingComponentConfig {
}
