package edu.noia.myoffice.customer.rest;

import edu.noia.myoffice.common.domain.event.EventPublisher;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.service.CustomerDataService;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.service.DefaultCustomerDataService;
import edu.noia.myoffice.customer.domain.service.DefaultCustomerService;
import edu.noia.myoffice.customer.rest.service.TransactionalCustomerDataService;
import edu.noia.myoffice.customer.rest.service.TransactionalCustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDomainComponentConfig {

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository,
                                           FolderRepository folderRepository,
                                           EventPublisher eventPublisher) {
        return new TransactionalCustomerService(
                new DefaultCustomerService(customerRepository, folderRepository, eventPublisher));
    }

    @Bean
    public CustomerDataService customerDataService(CustomerRepository customerRepository,
                                                   FolderRepository folderRepository) {
        return new TransactionalCustomerDataService(
                new DefaultCustomerDataService(customerRepository, folderRepository));
    }
}
