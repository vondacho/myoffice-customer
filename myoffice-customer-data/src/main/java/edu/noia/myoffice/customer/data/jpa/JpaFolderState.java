package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaBaseEntity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Audited
@Table(name = "folder_state")
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaFolderState extends JpaBaseEntity implements FolderState {

    UUID id;
    String name;
    String notes;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.AffiliateConverter")
    @Columns(columns = {
            @Column(name="customerId"),
            @Column(name="primaryDebtor")
    })
    @ElementCollection
    @CollectionTable(name = "folder_affiliates", joinColumns = @JoinColumn(name = "folder_pk"))
    Set<Affiliate> affiliates = new HashSet<>();

    public static JpaFolderState of(FolderState state) {
        return (JpaFolderState)(new JpaFolderState().modify(state).add(state.getAffiliates()));
    }

    @Override
    public FolderState modify(EntityState modifier) {
        return modifier instanceof JpaFolderState ? modify((JpaFolderState)modifier) : this;
    }

    @Override
    public FolderState patch(EntityState modifier) {
        return modifier instanceof JpaFolderState ? patch((JpaFolderState)modifier) : this;
    }
}
