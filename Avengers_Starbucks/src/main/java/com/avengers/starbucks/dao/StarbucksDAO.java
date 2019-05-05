
package com.avengers.starbucks.dao;

import java.util.List;
import java.util.Map;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.model.Product;

public interface StarbucksDAO {
	
	Map<String, String> insertCardDetails(AddCardsRequest addCardsRequest);
	
	List<AddCardsRequest> getCardDetails(String emailId);
	
	boolean insertOrder(String emailId, String orderDescription, float billingAmt);

	Product getProductDetail(int productId);

	void updateStock(int productId, int quantity);
	
}
