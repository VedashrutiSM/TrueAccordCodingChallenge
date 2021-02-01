package com.trueaccordcodingchallenge.domain;


import com.google.gson.annotations.SerializedName;

public class Payments {
	
	@SerializedName(value="amount")
	private double amount;
	
	@SerializedName(value= "date")
	private String date;
	
	
	//@ManyToOne
	@SerializedName(value= "payment_plan_id")
	private int paymentPlanId;
	
	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPaymentPlanId() {
		return paymentPlanId;
	}

	public void setPaymentPlanId(int paymentPlanId) {
		this.paymentPlanId = paymentPlanId;
	}

	public Payments() {}

	public Payments(double amount, String date, int paymentPlanId) {
		super();
		this.amount = amount;
		this.date = date;
		this.paymentPlanId = paymentPlanId;
	}

	@Override
	public String toString() {
		return "Payments [amount=" + amount + ", date=" + date + ", paymentPlanId=" + paymentPlanId + "]";
	}
	
	
	
}
