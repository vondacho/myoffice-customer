package edu.noia.myoffice.customer.data.adapter;

import edu.noia.myoffice.customer.data.jpa.JpaAffiliationState;
import edu.noia.myoffice.customer.data.jpa.JpaAffiliationStateRepository;
import edu.noia.myoffice.customer.domain.aggregate.Affiliation;
import edu.noia.myoffice.customer.domain.repository.AffiliationRepository;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AffiliationRepositoryAdapter implements AffiliationRepository {

    @NonNull
    JpaAffiliationStateRepository repository;

    @Override
    public Optional<Affiliation> findOne(UUID customer, UUID folder) {
        return repository
                .findByCustomerAndFolder(customer, folder)
                .map(Affiliation::of);
    }

    @Override
    public List<Affiliation> findAllByCustomer(UUID customer) {
        return repository.findByCustomer(customer)
                .stream()
                .map(Affiliation::of)
                .collect(toList());
    }

    @Override
    public List<Affiliation> findAllByFolder(UUID folder) {
        return repository.findByFolder(folder)
                .stream()
                .map(Affiliation::of)
                .collect(toList());
    }

    @Override
    public Affiliation save(Affiliation affiliation) {
        return Affiliation.of(repository.save((JpaAffiliationState) (affiliation.getState())));
    }

    @Override
    public void delete(UUID customer, UUID folder) {
        repository.deleteByCustomerAndFolder(customer, folder);
    }
}
