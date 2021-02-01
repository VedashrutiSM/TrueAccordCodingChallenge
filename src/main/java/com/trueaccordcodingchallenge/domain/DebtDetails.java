package com.trueaccordcodingchallenge.domain;

public class DebtDetails {
	
	private int id;
	private double amount;
	private boolean is_in_payment_plan;
	private double remaining_amount;
	private String next_payment_due_date;
	
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
	public boolean isIs_in_payment_plan() {
		return is_in_payment_plan;
	}
	public void setIs_in_payment_plan(boolean is_in_payment_plan) {
		this.is_in_payment_plan = is_in_payment_plan;
	}
	public double getRemaining_amount() {
		return remaining_amount;
	}
	public void setRemaining_amount(double remaining_amount) {
		this.remaining_amount = remaining_amount;
	}
	public String getNext_payment_due_date() {
		return next_payment_due_date;
	}
	public void setNext_payment_due_date(String next_payment_due_date) {
		this.next_payment_due_date = next_payment_due_date;
	}
	
	public DebtDetails() {}
	
	public DebtDetails(int id, double amount, boolean is_in_payment_plan, double remaining_amount,
			String next_payment_due_date) {
		super();
		this.id = id;
		this.amount = amount;
		this.is_in_payment_plan = is_in_payment_plan;
		this.remaining_amount = remaining_amount;
		this.next_payment_due_date = next_payment_due_date;
	}
	@Override
	public String toString() {
		return "DebtDetails [id=" + id + ", amount=" + amount + ", is_in_payment_plan=" + is_in_payment_plan
				+ ", remaining_amount=" + remaining_amount + ", next_payment_due_date=" + next_payment_due_date + "]";
	}
	
	
	

}
