package com.avengers.starbucks.service;

import javax.xml.bind.ValidationException;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.OrderRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.dto.GenericResponse;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;

public interface StarbucksService{
    
	StarbucksOutputMessage addCards(AddCardsRequest addCardsRequest) throws ValidationException;

	//StarbucksOutputMessage manageOrder(OrderRequest request);
	
	GenericResponse SignupRequest(SignupUser userRequest);
    
    UserDetailsDTO LoginRequest(LoginUser userLoginRequest) throws ValidationException;
}