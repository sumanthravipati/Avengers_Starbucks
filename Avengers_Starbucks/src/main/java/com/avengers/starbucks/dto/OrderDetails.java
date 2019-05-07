package com.avengers.starbucks.dto;

import java.io.Serializable;

public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private float amount;
	private String description;
	private String qty;

	public OrderDetails() {
	}

	public OrderDetails(float amount, String description, String qty) {
		super();
		this.amount = amount;
		this.description = description;
		this.qty = qty;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}
	
	
}