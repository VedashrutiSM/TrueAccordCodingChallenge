package com.trueaccordcodingchallenge.domain;


import com.google.gson.annotations.SerializedName;

public class Debt {
	
	//@Id
	@SerializedName(value="id")
	private int id;
	
	@SerializedName(value="amount")
	private double amount;
	
	//@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "paymentPlan_id", referencedColumnName = "id")
	//private PaymentPlan paymentPlan;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Debt() {}
	
	public Debt(int id, double amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Debt [id=" + id + ", amount=" + amount + "]";
	}
	
	
	

}
