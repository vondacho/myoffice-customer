package edu.noia.myoffice.customer.data.jpa;

import edu.noia.myoffice.common.data.jpa.JpaAuditableEntity;
import edu.noia.myoffice.customer.domain.aggregate.CustomerState;
import edu.noia.myoffice.customer.domain.entity.AffiliationState;
import edu.noia.myoffice.customer.domain.vo.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor(staticName = "of")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JpaCustomerState extends JpaAuditableEntity implements CustomerState {

    @NonNull
    @NotNull
    UUID id;
    String salutation;
    String firstName;
    @NonNull
    @NotNull
    String lastName;
    LocalDate birthDate;
    String street;
    @NonNull
    @NotNull
    String zip;
    @NonNull
    @NotNull
    String city;
    String region;
    @NonNull
    @NotNull
    String country;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "phone_number1")),
            @AttributeOverride(name = "kind", column = @Column(name = "phone_kind1"))
    })
    @Valid
    PhoneNumber phoneNumber1;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "phone_number2")),
            @AttributeOverride(name = "kind", column = @Column(name = "phone_kind2"))
    })
    @Valid
    PhoneNumber phoneNumber2;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "phone_number3")),
            @AttributeOverride(name = "kind", column = @Column(name = "phone_kind3"))
    })
    @Valid
    PhoneNumber phoneNumber3;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "number", column = @Column(name = "phone_number4")),
            @AttributeOverride(name = "kind", column = @Column(name = "phone_kind4"))
    })
    @Valid
    PhoneNumber phoneNumber4;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address1")),
            @AttributeOverride(name = "kind", column = @Column(name = "email_kind1"))
    })
    @Valid
    EmailAddress emailAddress1;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address2")),
            @AttributeOverride(name = "kind", column = @Column(name = "email_kind2"))
    })
    @Valid
    EmailAddress emailAddress2;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "email_address3")),
            @AttributeOverride(name = "kind", column = @Column(name = "email_kind3"))
    })
    @Valid
    EmailAddress emailAddress3;
    String websiteUrl;
    @Embedded
    @Valid
    Social social;
    @Embedded
    @Valid
    Profile profile;
    String notes;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "customer")
    Set<JpaAffiliationState> folders = new HashSet<>();

    @Override
    public void add(AffiliationState folder) {
        JpaAffiliationState affiliation = (JpaAffiliationState) folder;
        folders.add(affiliation);
        affiliation.setCustomer(this);
    }

    @Override
    public JpaCustomerState setData(CustomerVO data) {
        setSalutation(data.getSalutation());
        setFirstName(data.getFirstName());
        setBirthDate(data.getBirthDate());
        setStreet(data.getStreet());
        setRegion(data.getRegion());
        setPhoneNumber1(data.getPhoneNumber1());
        setPhoneNumber2(data.getPhoneNumber2());
        setPhoneNumber3(data.getPhoneNumber3());
        setPhoneNumber4(data.getPhoneNumber4());
        setEmailAddress1(data.getEmailAddress1());
        setEmailAddress2(data.getEmailAddress2());
        setEmailAddress3(data.getEmailAddress3());
        setWebsiteUrl(data.getWebsiteUrl());
        setSocial(data.getSocial());
        setProfile(data.getProfile());
        setNotes(data.getNotes());
        return this;
    }

    @Override
    public CustomerVO getData() {
        return CustomerVO.of(
            getSalutation(),
            getFirstName(),
            getLastName(),
            getBirthDate(),
            getStreet(),
            getZip(),
            getCity(),
            getRegion(),
            getCountry(),
            getPhoneNumber1(),
            getPhoneNumber2(),
            getPhoneNumber3(),
            getPhoneNumber4(),
            getEmailAddress1(),
            getEmailAddress2(),
            getEmailAddress3(),
            getWebsiteUrl(),
            getSocial(),
            getProfile(),
            getNotes());
    }
}
