package edu.noia.myoffice.customer.messaging.store;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.UUID;

import static edu.noia.myoffice.customer.messaging.store.PersistentEvent.Status.PENDING;
import static edu.noia.myoffice.customer.messaging.store.PersistentEvent.Status.SENT;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersistentEvent {

    @NonNull
    UUID uuid;
    @NonNull
    LocalDateTime occuredAt;
    LocalDateTime sentAt;
    @NonNull
    String payload;
    @NonNull
    Status status;

    public static PersistentEvent of(String payload) {
        return new PersistentEvent(UUID.randomUUID(), LocalDateTime.now(), payload, PENDING);
    }

    public void sent(LocalDateTime at) {
        this.sentAt = at;
        this.status = SENT;
    }

    public enum Status {
        PENDING, SENT
    }
}
