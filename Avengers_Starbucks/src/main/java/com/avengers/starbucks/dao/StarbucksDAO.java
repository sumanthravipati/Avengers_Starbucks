
package com.avengers.starbucks.dao;

import java.util.List;
import java.util.Map;
import javax.xml.bind.ValidationException;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.GenericResponse;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;
import com.avengers.starbucks.model.Product;

public interface StarbucksDAO {
	
	Map<String, String> insertCardDetails(AddCardsRequest addCardsRequest);
	
	List<AddCardsRequest> getCardDetails(String emailId);
	
	boolean insertOrder(String emailId, String orderDescription, float billingAmt);

	Product getProductDetail(int productId);

	void updateStock(int productId, int quantity);
	
	GenericResponse createUser(SignupUser userRequest);
	
	UserDetailsDTO getUserDetails(LoginUser userLoginRequest) throws ValidationException;
	
	float getCardBalance(String emailId, String cardNumber);
	
	float getOrderAmount(String emailId, int orderId);
	
	void updateOnSuccessfulPayment(String emailId, String cardNumber, int orderId, String new_balance);
	
}
