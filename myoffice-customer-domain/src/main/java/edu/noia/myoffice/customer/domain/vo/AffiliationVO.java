package edu.noia.myoffice.customer.domain.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AffiliationVO {
    Boolean isPrimaryDebtor;
    @NotNull
    UUID customer;
    @NotNull
    UUID folder;
}
