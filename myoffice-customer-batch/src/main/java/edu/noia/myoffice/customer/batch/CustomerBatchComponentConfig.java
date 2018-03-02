package edu.noia.myoffice.customer.batch;

import edu.noia.myoffice.customer.batch.config.ThreadPoolConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@Import(ThreadPoolConfiguration.class)
@ComponentScan
public class CustomerBatchComponentConfig {
}
