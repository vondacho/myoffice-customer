package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@EqualsAndHashCode(of = {"customer","folder"}, doNotUseGetters = true, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Affiliation {

    @NonNull
    Folder folder;
    @NonNull
    Customer customer;
    Boolean isPrimaryDebtor = false;
}
