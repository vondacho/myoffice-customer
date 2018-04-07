package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaBaseEntity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.customer.domain.aggregate.FolderState;
import edu.noia.myoffice.customer.domain.vo.Affiliate;
import edu.noia.myoffice.customer.domain.vo.FolderId;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static edu.noia.myoffice.common.util.exception.ExceptionSuppliers.notFound;

@Audited
@Table(name = "folder")
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaFolderState extends JpaBaseEntity implements FolderState {

    FolderId id;
    String name;
    String notes;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.type.AffiliateType")
    @Columns(columns = {
            @Column(name="customerId"),
            @Column(name="primaryDebtor")
    })
    @ElementCollection
    @CollectionTable(name = "folder_affiliate", joinColumns = @JoinColumn(name = "fk_folder"))
    Set<Affiliate> affiliates = new HashSet<>();

    public static JpaFolderState of(FolderState state) {
        return new JpaFolderState().modify((EntityState)state);
    }

    @Override
    public JpaFolderState modify(EntityState modifier) {
        return modifier instanceof FolderState ? (JpaFolderState)modify((FolderState)modifier) : this;
    }

    @Override
    public JpaFolderState patch(EntityState modifier) {
        return modifier instanceof FolderState ? (JpaFolderState)patch((FolderState)modifier) : this;
    }

    @Override
    public JpaFolderState addAffiliate(Affiliate affiliate) {
        getAffiliates().add(affiliate);
        return this;
    }

    @Override
    public JpaFolderState addAffiliates(Collection<Affiliate> affiliates) {
        getAffiliates().addAll(affiliates);
        return this;
    }

    @Override
    public JpaFolderState removeAffiliate(Affiliate affiliate) {
        if (!getAffiliates().remove(affiliate)) {
            throw notFound(Affiliate.class, affiliate.getCustomerId()).get();
        }
        return this;
    }

    @Override
    public JpaFolderState clearAffiliates() {
        getAffiliates().clear();
        return this;
    }

}
