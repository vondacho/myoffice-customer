package edu.noia.myoffice.customer.config;

import edu.noia.myoffice.customer.data.CustomerDataComponentConfiguration;
import edu.noia.myoffice.customer.domain.CustomerDomainComponentConfiguration;
import edu.noia.myoffice.customer.messaging.CustomerMessagingComponentConfiguration;
import edu.noia.myoffice.customer.rest.CustomerRestComponentConfiguration;
import edu.noia.myoffice.customer.ui.CustomerUiComponentConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {
        CustomerDataComponentConfiguration.class,
        CustomerMessagingComponentConfiguration.class,
        CustomerRestComponentConfiguration.class,
        CustomerDomainComponentConfiguration.class,
        CustomerUiComponentConfiguration.class
})
@Configuration
public class CustomerApplicationConfiguration {
}
