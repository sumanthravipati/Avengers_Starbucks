package com.avengers.starbucks.dto;
import java.io.Serializable;

public class PaymentRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String emailId;
	private String cardNumber;
	private int orderId;
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	

}
