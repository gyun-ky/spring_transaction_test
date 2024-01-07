package com.transaction.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.transaction.model.TicketPublishProcess;
import com.transaction.transaction.repository.TicketPublishProcessRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService2 {

	private final TicketPublishProcessRepository ticketPublishProcessRepository;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void publishProcess1(int i) {
		ticketPublishProcessRepository.save(
			TicketPublishProcess.builder()
				.name(Integer.toString(i) + "-process1")
				.build()
		);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void publishProcess2(int i) throws RuntimeException{
		ticketPublishProcessRepository.save(
			TicketPublishProcess.builder()
				.name(Integer.toString(i) + "-process2")
				.build()
		);

		if(i==3) {
			throw new RuntimeException("excpetion runtime");
		}
	}
}
