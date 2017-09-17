package edu.noia.myoffice.customer.rest.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"customers"})
public interface FolderMixin {
}
