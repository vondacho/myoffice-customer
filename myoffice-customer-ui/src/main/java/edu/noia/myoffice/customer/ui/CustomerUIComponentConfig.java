package edu.noia.myoffice.customer.ui;

import edu.noia.myoffice.customer.ui.config.ClientSidePathLocationStrategySupportConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(ClientSidePathLocationStrategySupportConfiguration.class)
@Configuration
public class CustomerUIComponentConfig {
}
