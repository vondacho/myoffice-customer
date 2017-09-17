package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.entity.AffiliationState;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaAffiliationState extends JpaAuditableEntity implements AffiliationState {

    Boolean isPrimaryDebtor;
    @ManyToOne
    JpaFolderState folder;
    @ManyToOne
    JpaCustomerState customer;
}
