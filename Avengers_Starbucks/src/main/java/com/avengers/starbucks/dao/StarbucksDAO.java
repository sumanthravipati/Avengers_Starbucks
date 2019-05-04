
package com.avengers.starbucks.dao;

import java.util.Map;

import javax.xml.bind.ValidationException;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;

public interface StarbucksDAO {
	
	Map<String, String> insertCardDetails(AddCardsRequest addCardsRequest);
	
	Map<String, String> getCardDetails(AddCardsRequest addCardsRequest);
	
    void createUser(SignupUser userRequest);
	
	UserDetailsDTO getUserDetails(LoginUser userLoginRequest) throws ValidationException;
	
}
