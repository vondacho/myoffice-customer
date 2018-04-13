package edu.noia.myoffice.customer.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.noia.myoffice.common.data.adapter.RdbmsEventStoreAdapter;
import edu.noia.myoffice.common.data.jpa.JpaEventPublication;
import edu.noia.myoffice.common.data.jpa.JpaEventPublicationRepository;
import edu.noia.myoffice.common.event.store.InternalEventStore;
import edu.noia.myoffice.customer.data.adapter.CustomerRepositoryAdapter;
import edu.noia.myoffice.customer.data.adapter.FolderRepositoryAdapter;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerStateRepository;
import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderStateRepository;
import edu.noia.myoffice.customer.domain.repository.CustomerRepository;
import edu.noia.myoffice.customer.domain.repository.FolderRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
        basePackageClasses = {
                JpaCustomerStateRepository.class,
                JpaFolderStateRepository.class,
                JpaEventPublicationRepository.class
        },
        repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class
)
@EntityScan(
        basePackageClasses = {
                JpaCustomerState.class,
                JpaFolderState.class,
                JpaEventPublication.class
        }
)
@Configuration
public class CustomerDataComponentConfiguration {

    @Bean
    public FolderRepository folderRepository(JpaFolderStateRepository jpaFolderRepository) {
        return new FolderRepositoryAdapter(jpaFolderRepository);
    }

    @Bean
    public CustomerRepository customerRepository(JpaCustomerStateRepository jpaCustomerRepository) {
        return new CustomerRepositoryAdapter(jpaCustomerRepository);
    }

    @Bean
    public InternalEventStore internalEventStore(JpaEventPublicationRepository jpaEventPublicationRepository, ObjectMapper objectMapper) {
        return new RdbmsEventStoreAdapter(jpaEventPublicationRepository, objectMapper);
    }
}
