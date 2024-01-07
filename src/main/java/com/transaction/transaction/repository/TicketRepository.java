package com.transaction.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.transaction.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
