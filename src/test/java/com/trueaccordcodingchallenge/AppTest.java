package com.trueaccordcodingchallenge;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.trueaccordcodingchallenge.App;
import com.trueaccordcodingchallenge.domain.DebtDetails;

import junit.framework.Assert;


/**
 * Unit test for simple App.
 */
@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class AppTest {
	
	@InjectMocks
	App app;
	
	List<DebtDetails> list;
	
	@Before
	public void initialization() {
		app.populateLists();
		list = app.calculateDebtDetails();
	}
	
	@Test
	public void testDebtId0() {
		
		// for debtId 0, debt amount is paid from 2 installments so the remaining amount to be paid is $0
		DebtDetails debtDetails0 = list.get(0);
		double remaining_amount0 = debtDetails0.getRemaining_amount();
		Assert.assertEquals(0.0,remaining_amount0);

	}
	
	@Test
	public void testDebtId1() {
		
		// for debtId1 100 is the total amount to pay but 2 installments of 25 each is paid so remaining amount to be paid is 50
		DebtDetails debtDetails1 = list.get(1);
		double remaining_amount1 = debtDetails1.getRemaining_amount();
		Assert.assertEquals(50.0,remaining_amount1);
		
		// from payments 2020-08-08 is the last installment paid and it is paid weekly so the next payment date is 2020-08-15
		String next_paymentdate =  debtDetails1.getNext_payment_due_date();
		Assert.assertEquals("2020-08-15", next_paymentdate);
	}
	
	@Test
	public void testDebtId2() {
		
		// from payments 2020-08-08 is the last installment paid and it is paid bi-weekly so the next payment date is 2020-08-22
		DebtDetails debtDetails2 = list.get(2);
		String next_paymentdate =  debtDetails2.getNext_payment_due_date();
		Assert.assertEquals("2020-08-22", next_paymentdate);
	}
	
	@Test
	public void testDebtId3() {
		
		// from payments 2020-08-15 is the last installment paid and it is paid weekly so the next payment date is 2020-08-22
		DebtDetails debtDetails3 = list.get(3);
		String next_paymentdate =  debtDetails3.getNext_payment_due_date();
		Assert.assertEquals("2020-08-22", next_paymentdate);
	}
	
	@Test
	public void testDebtId4() {
		
		DebtDetails debtDetails4 = list.get(4);
		boolean isInPaymentPlan = debtDetails4.isIs_in_payment_plan();
		Assert.assertEquals(false,isInPaymentPlan);
	}
}

