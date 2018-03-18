package edu.noia.myoffice.customer.rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.customer.domain.vo.Affiliate;

import java.util.List;
import java.util.Set;

public interface FolderMixin {
    @JsonIgnore
    Set<Affiliate> getAffiliates();
    @JsonIgnore
    List<Event> getRegisteredEvents();
}
