package edu.noia.myoffice.customer.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPersistentEventRepository extends JpaRepository<JpaPersistentEvent, Long> {
}
