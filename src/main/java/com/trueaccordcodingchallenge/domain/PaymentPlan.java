package com.trueaccordcodingchallenge.domain;


import com.google.gson.annotations.SerializedName;

public class PaymentPlan {
	
	//@Id
	@SerializedName(value= "id")
	private int id;
	
	//@OneToOne
	@SerializedName(value= "debt_id")
	private int debtId;
	
	@SerializedName(value= "amount_to_pay")
	private double amountToPay;
	
	@SerializedName(value= "installment_frequency")
	private String installmentFrequency;
	
	@SerializedName(value= "installment_amount")
	private double installmentAmount;
	
	@SerializedName(value= "start_date")
	private String startDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDebtId() {
		return debtId;
	}

	public void setDebtId(int debtId) {
		this.debtId = debtId;
	}

	public double getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(double amountToPay) {
		this.amountToPay = amountToPay;
	}

	public String getInstallmentFrequency() {
		return installmentFrequency;
	}

	public void setInstallmentFrequency(String installmentFrequency) {
		this.installmentFrequency = installmentFrequency;
	}

	public double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public PaymentPlan() {}

	public PaymentPlan(int id, int debtId, double amountToPay, String installmentFrequency, double installmentAmount,
			String startDate) {
		super();
		this.id = id;
		this.debtId = debtId;
		this.amountToPay = amountToPay;
		this.installmentFrequency = installmentFrequency;
		this.installmentAmount = installmentAmount;
		this.startDate = startDate;
	}

	@Override
	public String toString() {
		return "PaymentPlan [id=" + id + ", debtId=" + debtId + ", amountToPay=" + amountToPay
				+ ", installmentFrequency=" + installmentFrequency + ", installmentAmount=" + installmentAmount
				+ ", startDate=" + startDate + "]";
	}
	
	
	

}
