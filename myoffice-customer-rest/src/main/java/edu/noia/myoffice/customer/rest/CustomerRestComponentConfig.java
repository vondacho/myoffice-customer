package edu.noia.myoffice.customer.rest;

import edu.noia.myoffice.common.serializer.CommonSerializers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@ComponentScan
@Configuration
public class CustomerRestComponentConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .modules(CommonSerializers.getModule());
    }
}
