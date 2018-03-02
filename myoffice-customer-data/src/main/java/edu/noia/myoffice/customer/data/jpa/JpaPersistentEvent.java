package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.customer.messaging.store.PersistentEvent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaPersistentEvent extends PersistentEvent {

    @Id
    Long id;
}
