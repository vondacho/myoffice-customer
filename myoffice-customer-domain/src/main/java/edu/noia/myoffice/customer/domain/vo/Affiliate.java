package edu.noia.myoffice.customer.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = {"customerId"}, doNotUseGetters = true, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Affiliate {

    @NonNull
    UUID customerId;
    Boolean primaryDebtor = false;
}
