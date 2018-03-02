package edu.noia.myoffice.customer.domain.test.config;

import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import edu.noia.myoffice.customer.domain.service.CustomerService;
import edu.noia.myoffice.customer.domain.test.util.CustomerRepositoryAdapterStub;
import edu.noia.myoffice.customer.domain.test.util.FolderRepositoryAdapterStub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ITConfiguration {

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepositoryAdapterStub();
    }

    @Bean
    public FolderRepository folderRepository() {
        return new FolderRepositoryAdapterStub();
    }

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository,
                                           FolderRepository folderRepository) {
        return new CustomerService(customerRepository, folderRepository);
    }
}
