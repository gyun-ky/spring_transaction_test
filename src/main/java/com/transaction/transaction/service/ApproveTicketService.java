package com.transaction.transaction.service;

import org.springframework.stereotype.Service;

import com.transaction.transaction.model.Member;
import com.transaction.transaction.model.Ticket;
import com.transaction.transaction.repository.MemberRepository;
import com.transaction.transaction.repository.TicketRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApproveTicketService {

	private final TicketService ticketService;
	private final TicketRepository ticketRepository;
	private final MemberRepository memberRepository;

	@Transactional
	public void approveTicketWithNoProcess1() {

		// runtime Exception 발생, 새로운 transaction 생성
		try{
			ticketService.publishTicketWithNoProcess(1);
		} catch (Exception e) {
			// Do Nothing
			// publishTicketWithNoProcess에서 Exception 발생하여도 member는 저장되어야
		}

		memberRepository.save(Member.builder()
				.name("memberNoProcess")
			.build());

	}
	public void approveTicketWithProcessFail1() {

		for(int i = 0; i<=10; i++) {
			try{
				ticketService.publicTicketWithProcess1(i);
			} catch (Exception e) {
				// Do Nothing
				// 특정 publicTicketWithProcess에서 Exception 발생하더라도 member는 저장되어야
				// 특정 publicTicketWithProcess 실패시, 후처리할 예정
			}


		}

	}

	@Transactional
	public void approveTicketWithProcessSuccess1() {

		for(int i = 0; i<=10; i++) {
				try{
					ticketService.publishProcess1(i);
				} catch (Exception e) {

				}

				// 티켓 생성 프로세스2
				try{
					ticketService.publishProcess2(i);
				} catch (Exception e) {

				}

				// 티켓 생성
				ticketRepository.save(Ticket.builder()
					.name(Integer.toString(i))
					.build());

		}

	}

	@Transactional
	public void approveTicketWithProcessSuccess2() {

		for(int i = 0; i<=10; i++) {
			try{
				ticketService.publicTicketWithProcess2(i);
			} catch (Exception e) {
				// Do Nothing
				// 특정 publicTicketWithProcess에서 Exception 발생하더라도 member는 저장되어야
				// 특정 publicTicketWithProcess 실패시, 후처리할 예정
			}

			memberRepository.save(Member.builder()
				.name("memberNoProcess")
				.build());
		}

	}
}
