package edu.noia.myoffice.customer.rest.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import edu.noia.myoffice.common.rest.jackson.AuditableEntityMixin;
import edu.noia.myoffice.customer.data.jpa.JpaCustomerState;

@JsonIgnoreProperties({"id", "folder"})
public class CustomerStateMixin extends AuditableEntityMixin {

    @JsonUnwrapped
    JpaCustomerState state;
}
