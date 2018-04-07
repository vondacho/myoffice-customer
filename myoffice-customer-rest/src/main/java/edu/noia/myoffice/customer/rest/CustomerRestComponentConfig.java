package edu.noia.myoffice.customer.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import edu.noia.myoffice.common.rest.exception.EndpointExceptionHandler;
import edu.noia.myoffice.common.serializer.CommonSerializers;
import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import edu.noia.myoffice.customer.domain.vo.Affiliation;
import edu.noia.myoffice.customer.rest.mixin.AffiliationMixin;
import edu.noia.myoffice.customer.rest.mixin.CustomerMixin;
import edu.noia.myoffice.customer.rest.mixin.FolderMixin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@ComponentScan(basePackageClasses = {
        CustomerRestComponentConfig.class,
        EndpointExceptionHandler.class
})
@Configuration
public class CustomerRestComponentConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .serializationInclusion(JsonInclude.Include.NON_EMPTY)
                .mixIn(Affiliation.class, AffiliationMixin.class)
                .mixIn(Folder.class, FolderMixin.class)
                .mixIn(Customer.class, CustomerMixin.class)
                .modules(CommonSerializers.getModule());
    }
}
