
package com.avengers.starbucks.dao;

import java.util.List;
import java.util.Map;

import com.avengers.starbucks.dto.AddCardsRequest;

public interface StarbucksDAO {
	
	Map<String, String> insertCardDetails(AddCardsRequest addCardsRequest);
	
	List<AddCardsRequest> getCardDetails(String emailId);
	
}
