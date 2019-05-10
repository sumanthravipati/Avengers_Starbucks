package com.avengers.starbucks.validations.util;

import org.springframework.stereotype.Component;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;

@Component
public class ValidationsUtil {
	
	public StarbucksOutputMessage addCardsInitialValidations(StarbucksOutputMessage outputMessage, AddCardsRequest addCardsRequest) {
		
		if (addCardsRequest.getCardCode() == null || addCardsRequest.getCardCode().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Card Code cannot be null or empty");
	    }else if(addCardsRequest.getCardNumber() == null || addCardsRequest.getCardNumber().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Card Number cannot be null or empty");
	    }else if (addCardsRequest.getEmailId() == null || addCardsRequest.getEmailId().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Email ID cannot be null or empty");
	    }
		return outputMessage;
	}

}
