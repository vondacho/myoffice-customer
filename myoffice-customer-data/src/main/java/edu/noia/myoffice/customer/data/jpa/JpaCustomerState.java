package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.common.domain.entity.EntityState;
import edu.noia.myoffice.common.domain.event.Event;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.vo.EmailAddress;
import edu.noia.myoffice.customer.domain.vo.PhoneNumber;
import edu.noia.myoffice.customer.domain.vo.Profile;
import edu.noia.myoffice.customer.domain.vo.Social;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Audited
@Table(name = "customer_state")
@ToString
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Accessors(chain=true)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaCustomerState extends JpaAuditableEntity implements CustomerState {

    UUID id;
    String salutation;
    String firstName;
    String lastName;
    LocalDate birthDate;
    String streetNo;
    String zip;
    String city;
    String region;
    String country;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.PhoneNumberConverter")
    @Columns(columns = {
            @Column(name="phone_number1"),
            @Column(name="phone_kind1")
    })
    PhoneNumber phoneNumber1;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.PhoneNumberConverter")
    @Columns(columns = {
            @Column(name="phone_number2"),
            @Column(name="phone_kind2")
    })
    PhoneNumber phoneNumber2;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.PhoneNumberConverter")
    @Columns(columns = {
            @Column(name="phone_number3"),
            @Column(name="phone_kind3")
    })
    PhoneNumber phoneNumber3;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.PhoneNumberConverter")
    @Columns(columns = {
            @Column(name="phone_number4"),
            @Column(name="phone_kind4")
    })
    PhoneNumber phoneNumber4;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.EmailAddressConverter")
    @Columns(columns = {
            @Column(name="email_address1"),
            @Column(name="email_kind1")
    })
    EmailAddress emailAddress1;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.EmailAddressConverter")
    @Columns(columns = {
            @Column(name="email_address2"),
            @Column(name="email_kind2")
    })
    EmailAddress emailAddress2;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.EmailAddressConverter")
    @Columns(columns = {
            @Column(name="email_address3"),
            @Column(name="email_kind3")
    })
    EmailAddress emailAddress3;

    String websiteUrl;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.SocialConverter")
    @Columns(columns = {
            @Column(name="soc_skypeUrl"),
            @Column(name="soc_facebookUrl"),
            @Column(name="soc_googleplusUrl"),
            @Column(name="soc_linkedinUrl"),
            @Column(name="soc_twitterUrl"),
            @Column(name="soc_instagramUrl")
    })
    Social social;

    @Type(type = "edu.noia.myoffice.customer.data.jpa.hibernate.converter.ProfileConverter")
    @Columns(columns = {
            @Column(name="prof_hasMoved"),
            @Column(name="prof_isSubcontractor"),
            @Column(name="prof_sendInvitation")
    })
    Profile profile;

    String notes;

    @Transient
    List<Event> domainEvents = new ArrayList<>();

    public static JpaCustomerState of(CustomerState state) {
        return (JpaCustomerState)(new JpaCustomerState().modify(state));
    }

    @Override
    public CustomerState modify(EntityState modifier) {
        return modifier instanceof JpaCustomerState ? modify((JpaCustomerState)modifier) : this;
    }

    @Override
    public CustomerState patch(EntityState modifier) {
        return modifier instanceof JpaCustomerState ? patch((JpaCustomerState)modifier) : this;
    }

    @DomainEvents
    public List<Event> domainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @AfterDomainEventPublication
    void clearDomainEvents() {
        domainEvents.clear();
    }
}
