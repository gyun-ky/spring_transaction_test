package com.transaction.transaction.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.transaction.transaction.model.Member;
import com.transaction.transaction.model.Ticket;
import com.transaction.transaction.model.TicketPublishProcess;
import com.transaction.transaction.repository.MemberRepository;
import com.transaction.transaction.repository.TicketPublishProcessRepository;
import com.transaction.transaction.repository.TicketRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;
	private final TicketPublishProcessRepository ticketPublishProcessRepository;
	private final MemberRepository memberRepository;

	private final TicketService2 ticketService2;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void publishTicketWithNoProcess(int i) {

		// 티켓 생성
		ticketRepository.save(Ticket.builder()
			.name(Integer.toString(i) + "-publishTicketWithNoProcess")
			.build());

		throw new RuntimeException("Ex");

	}

	@Transactional
	public void publicTicketWithProcess1(int i) {

		// 티켓 생성 프로세스1
		try{
			publishProcess1(i);
		} catch (Exception e) {

		}

		// 티켓 생성 프로세스2
		try{
			publishProcess2(i);
		} catch (Exception e) {

		}

		// 티켓 생성
		ticketRepository.save(Ticket.builder()
			.name(Integer.toString(i))
			.build());
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void publicTicketWithProcess2(int i) {

		// 티켓 생성 프로세스1
		try{
			ticketService2.publishProcess1(i);
		} catch (Exception e) {

		}

		// 티켓 생성 프로세스2
		try{
			ticketService2.publishProcess2(i);
		} catch (Exception e) {

		}

		// 티켓 생성
		ticketRepository.save(Ticket.builder()
			.name(Integer.toString(i))
			.build());

	}

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
