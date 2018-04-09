package edu.noia.myoffice.customer;

import edu.noia.myoffice.customer.data.CustomerDataComponentConfig;
import edu.noia.myoffice.customer.messaging.CustomerMessagingComponentConfig;
import edu.noia.myoffice.customer.rest.CustomerDomainComponentConfig;
import edu.noia.myoffice.customer.rest.CustomerRestComponentConfig;
import edu.noia.myoffice.customer.ui.CustomerUIComponentConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(value = {
        CustomerDataComponentConfig.class,
        CustomerMessagingComponentConfig.class,
        CustomerRestComponentConfig.class,
        CustomerDomainComponentConfig.class,
        CustomerUIComponentConfig.class
})
@Configuration
public class CustomerApplicationConfig {
}
