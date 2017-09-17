package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.entity.AffiliationState;
import edu.noia.myoffice.customer.domain.vo.FolderVO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaFolderState extends JpaAuditableEntity implements FolderState {

    @NonNull
    @NotNull
    UUID id;
    @NonNull
    @NotNull
    String name;
    String notes;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "folder")
    Set<JpaAffiliationState> customers = new HashSet<>();

    @Override
    public void add(AffiliationState customer) {
        JpaAffiliationState affiliation = (JpaAffiliationState) customer;
        customers.add(affiliation);
        affiliation.setFolder(this);
    }

    @Override
    public FolderState setData(FolderVO data) {
        setName(data.getName());
        setNotes(data.getNotes());
        return this;
    }

    @Override
    public FolderVO getData() {
        return FolderVO.of(name, notes);
    }
}
