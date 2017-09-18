package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.customer.domain.aggregate.AffiliationState;
import edu.noia.myoffice.customer.domain.vo.AffiliationVO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"customer","folder"}, callSuper = false)
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaAffiliationState extends JpaAuditableEntity implements AffiliationState {

    Boolean isPrimaryDebtor = Boolean.FALSE;
    @NonNull
    @NotNull
    UUID customer;
    @NonNull
    @NotNull
    UUID folder;

    @Override
    public AffiliationState setData(AffiliationVO data) {
        setIsPrimaryDebtor(data.getIsPrimaryDebtor());
        return this;
    }

    @Override
    public AffiliationVO getData() {
        return AffiliationVO.of(isPrimaryDebtor, customer, folder);
    }
}
