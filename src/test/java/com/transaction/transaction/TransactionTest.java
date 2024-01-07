package com.transaction.transaction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.transaction.transaction.service.ApproveTicketService;
import com.transaction.transaction.service.MemberService;
import com.transaction.transaction.service.TicketService;

@SpringBootTest
public class TransactionTest {

	@Autowired
	private MemberService memberService;

	@Autowired
	private TicketService ticketService;

	@Autowired
	private ApproveTicketService approveTicketService;

	@Test
	void depthTwoTransactionTest() {
		approveTicketService.approveTicketWithNoProcess1();
	}

	@Test
	void depthTwoTransactionFailTest() {
		approveTicketService.approveTicketWithProcessFail1();
	}

	@Test
	void depthTwoTransactionSuccessTest() {
		approveTicketService.approveTicketWithProcessSuccess1();
	}

	@Test
	void depthTwoTransactionSuccessTest2() {
		approveTicketService.approveTicketWithProcessSuccess2();
	}
}
