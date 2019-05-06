package com.avengers.starbucks.validations.util;

import org.springframework.stereotype.Component;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;

@Component
public class ValidationsUtil {
	
	public StarbucksOutputMessage addCardsInitialValidations(StarbucksOutputMessage outputMessage, AddCardsRequest addCardsRequest) {
		
		if (addCardsRequest.getCardCode().equalsIgnoreCase("null") || addCardsRequest.getCardCode().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Card Code cannot be null or empty");
	    }else if(addCardsRequest.getCardNumber().equalsIgnoreCase("null") || addCardsRequest.getCardNumber().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Card Number cannot be null or empty");
	    }else if (addCardsRequest.getEmailId().equalsIgnoreCase("null") || addCardsRequest.getEmailId().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Email ID cannot be null or empty");
	    }else if (addCardsRequest.getFirstName().equalsIgnoreCase("null") || addCardsRequest.getFirstName().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("First Name cannot be null or empty");
	    }else if (addCardsRequest.getLastName().equalsIgnoreCase("null") || addCardsRequest.getLastName().equalsIgnoreCase("")) {
	    	outputMessage.setErrorResponse("Last Name cannot be null or empty");
	    }
		return outputMessage;
	}

}
