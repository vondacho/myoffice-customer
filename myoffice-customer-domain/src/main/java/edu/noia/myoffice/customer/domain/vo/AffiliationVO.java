package edu.noia.myoffice.customer.domain.vo;

import edu.noia.myoffice.customer.domain.aggregate.Customer;
import edu.noia.myoffice.customer.domain.aggregate.Folder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AffiliationVO {
    @NonNull
    Folder folder;
    @NonNull
    Customer customer;
}
