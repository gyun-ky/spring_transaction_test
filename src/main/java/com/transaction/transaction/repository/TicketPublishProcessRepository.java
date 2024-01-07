package com.transaction.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.transaction.model.TicketPublishProcess;

@Repository
public interface TicketPublishProcessRepository extends JpaRepository<TicketPublishProcess, Long> {
}
