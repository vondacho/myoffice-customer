package edu.noia.myoffice.customer.rest;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import edu.noia.myoffice.common.rest.MyOfficeCommonRestApplication;
import edu.noia.myoffice.common.rest.jackson.AuditableEntityMixin;
import edu.noia.myoffice.customer.batch.MyOfficeCustomerBatchApplication;
import edu.noia.myoffice.customer.data.MyOfficeCustomerDataApplication;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;
import edu.noia.myoffice.customer.data.jpa.JpaFolderState;
import edu.noia.myoffice.customer.domain.MyOfficeCustomerDomainApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({
        MyOfficeCustomerDomainApplication.class,
        MyOfficeCustomerDataApplication.class,
        MyOfficeCustomerBatchApplication.class,
        MyOfficeCommonRestApplication.class
})
@SpringBootApplication
public class MyOfficeCustomerRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOfficeCustomerRestApplication.class, args);
    }

    @Bean
    public Module module() {
        SimpleModule module = new SimpleModule();
        module.setMixInAnnotation(JpaCustomerState.class, AuditableEntityMixin.class);
        module.setMixInAnnotation(JpaFolderState.class, AuditableEntityMixin.class);
        return module;
    }
}
