package edu.noia.myoffice.customer.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.noia.myoffice.common.domain.event.Event;

import java.util.List;

public interface FolderMixin {
    @JsonIgnore
    List<Event> getDomainEvents();
}
