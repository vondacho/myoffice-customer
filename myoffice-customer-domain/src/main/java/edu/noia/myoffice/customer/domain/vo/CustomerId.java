package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.common.domain.vo.Identity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of="id", callSuper = false, doNotUseGetters = true)
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public final class CustomerId implements Identity {
    @NonNull
    UUID id;

    public static CustomerId random() {
        return CustomerId.of(UUID.randomUUID());
    }
}
