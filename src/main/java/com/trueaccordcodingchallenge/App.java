package com.trueaccordcodingchallenge;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.trueaccordcodingchallenge.App;
import com.trueaccordcodingchallenge.domain.Debt;
import com.trueaccordcodingchallenge.domain.DebtDetails;
import com.trueaccordcodingchallenge.domain.PaymentPlan;
import com.trueaccordcodingchallenge.domain.Payments;

/**
 * Hello world!
 *
 */
public class App {

	ArrayList<Debt> debtList;
	ArrayList<PaymentPlan> paymentPlanList;
	ArrayList<Payments> paymentsList;
	HashMap<Integer, PaymentPlan> paymentplanMap = new HashMap<>();

	public void populateLists() {

		Gson gson = new Gson();

		String debtsJson = readContent("debts");
		Type debtsListType = new TypeToken<ArrayList<Debt>>() {}.getType();
		debtList = gson.fromJson(debtsJson, debtsListType);

		String paymentPlansJson = readContent("payment_plans");
		Type paymentPlanListType = new TypeToken<ArrayList<PaymentPlan>>() {}.getType();
		paymentPlanList = gson.fromJson(paymentPlansJson, paymentPlanListType);
		
		for(PaymentPlan pp:paymentPlanList) {
			paymentplanMap.put(pp.getId(),pp);
		}

		String paymentsJson = readContent("payments");
		Type paymentsListType = new TypeToken<ArrayList<Payments>>() {}.getType();
		paymentsList = gson.fromJson(paymentsJson, paymentsListType);

	}
	
	public List<DebtDetails> calculateDebtDetails() {

		List<DebtDetails> debtDetailsList = new ArrayList<>();
		
		// iterate through every record in the debt list
		for (int i = 0; i < debtList.size(); i++) {

			// DebtDetails class for storing all the details given in the requirement
			DebtDetails debtDetails = new DebtDetails();

			// setting all the debt's fields returned from the api
			int debtId = debtList.get(i).getId();
			debtDetails.setId(debtId);
			debtDetails.setAmount(debtList.get(i).getAmount());
			
			// "is_in_payment_plan" set to true, if the debt is associated with a payment plan
			boolean isDebtInPaymentplan = false;
			if(paymentplanMap.containsKey(debtId)) {
				isDebtInPaymentplan = true;
			}

			debtDetails.setIs_in_payment_plan(isDebtInPaymentplan);
			
			//calculating remaining_amount, containing the calculated amount remaining to be paid on the debt
			double remainingAmount = 0.0;
			if(paymentplanMap.containsKey(debtId)) {
				remainingAmount = calculateRemainingAmount(debtId);
			}
			debtDetails.setRemaining_amount(remainingAmount);
			
			// calculating next_payment_due_date
			// next_payment_due_date is set to null if there is no payment plan or if the debt has been paid off
			if(remainingAmount == 0 || isDebtInPaymentplan == false) {
				debtDetails.setNext_payment_due_date(null);
			}else {
				debtDetails.setNext_payment_due_date(getNextPaymentDueDate(debtId));
			}			
			debtDetailsList.add(debtDetails);
		}
		return debtDetailsList;
	}
	
	public String getNextPaymentDueDate(int debtId) {
		
		PaymentPlan pp = paymentplanMap.get(debtId);
		String startdate = pp.getStartDate();
		LocalDate startlocalDate = LocalDate.parse(startdate);
		
		String nextPaymentDate = "";
		LocalDate nextPaymentInLocalDate = LocalDate.parse(startdate);
		
		String lastPaymentDate = "";
		
		// get the lastPaymentDate for the given debtId
		for(int i=0;i<paymentsList.size();i++) {
			if(paymentsList.get(i).getPaymentPlanId() == debtId) {
				lastPaymentDate = paymentsList.get(i).getDate();
			}
		}		
		// if lastPaymentDate is null i.e no payment is made till now then add installment_frequency to start_date
		if(lastPaymentDate == null) {
			
			if(pp.getInstallmentFrequency() == "BI_WEEKLY") {
				nextPaymentInLocalDate = startlocalDate.plusWeeks(2);
			}else if(pp.getInstallmentFrequency() == "WEEKLY") {
				nextPaymentInLocalDate = startlocalDate.plusWeeks(1);
			}
			
		}else { // else add installment_frequency to lastPaymentDate
			LocalDate lastPaymentInLocalDate = LocalDate.parse(lastPaymentDate);
			
			if(pp.getInstallmentFrequency().equals("BI_WEEKLY") ){
				nextPaymentInLocalDate = lastPaymentInLocalDate.plusWeeks(2);
			}else if(pp.getInstallmentFrequency().equals("WEEKLY")) {
				nextPaymentInLocalDate = lastPaymentInLocalDate.plusWeeks(1);
			}
		}		
		nextPaymentDate =  nextPaymentInLocalDate.format( DateTimeFormatter.ISO_LOCAL_DATE );
		return nextPaymentDate;
	}


	public double calculateRemainingAmount(int debtId) {

		// remainingAmount = amount_to_pay - (installmentAmount * no_of_payments)
		double remainingAmount = 0;
		double amountToPay = paymentPlanList.get(debtId).getAmountToPay();
		double installmentAmountPaid = 0;

		for (int j = 0; j < paymentsList.size(); j++) {

			if (paymentsList.get(j).getPaymentPlanId() == debtId) {
				installmentAmountPaid = installmentAmountPaid + paymentsList.get(j).getAmount();
			}
		}
		remainingAmount = amountToPay - installmentAmountPaid;
		return remainingAmount;
	}
	
	public String readContent(String queryUrl) {
		Scanner sc = null;
		StringBuffer sb = new StringBuffer();
		String url = "";

		try {
			switch (queryUrl) {

			case "debts":
				url = "https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/debts";
				break;
			case "payment_plans":
				url = "https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payment_plans";
				break;
			case "payments":
				url = "https://my-json-server.typicode.com/druska/trueaccord-mock-payments-api/payments";
				break;
			}
			URL myUrl = new URL(url);
			sc = new Scanner(myUrl.openStream());
			while (sc.hasNext()) {
				sb.append(sc.nextLine());
			}

		} catch (MalformedURLException e) {
			System.out.println("new URL failed");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Open connection failed ");
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		
		App app = new App();
		app.populateLists();		
		List<DebtDetails> debtDetails = app.calculateDebtDetails();
		
		for(DebtDetails dd: debtDetails) {
			System.out.println(dd);
		}		
	}
}
