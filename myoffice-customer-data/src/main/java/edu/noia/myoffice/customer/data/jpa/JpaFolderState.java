package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.aggregate.MutableFolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaFolderState extends JpaAuditableEntity implements MutableFolderState {

    UUID id;
    String name;
    String notes;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.AffiliateConverter")
    @Columns(columns = {
            @Column(name="customerId"),
            @Column(name="primaryDebtor")
    })
    @ElementCollection
    @CollectionTable(name = "folder_affiliates", joinColumns = @JoinColumn(name = "folderId"))
    Set<Affiliate> affiliates = new HashSet<>();

    public static JpaFolderState of(FolderState state) {
        return (JpaFolderState)(new JpaFolderState().modify(state).add(state.getAffiliates()));
    }
}
