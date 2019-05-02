package com.avengers.starbucks.service.impl;

import java.util.Map;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avengers.starbucks.dao.StarbucksDAO;
import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.service.StarbucksService;


@Component
public class StarbucksServiceImpl implements StarbucksService
{
	
	@Autowired
	StarbucksDAO starbucksDAO;
    
	@Override
	public StarbucksOutputMessage addCards(AddCardsRequest addCardsRequest) throws ValidationException {
		
		StarbucksOutputMessage outputMessage = new StarbucksOutputMessage();
		
		//Scenario -1: Check if the Card Number and the Card Code contains only Digits
		String regex = "\\d+";
		if (addCardsRequest.getCardNumber().matches(regex) && addCardsRequest.getCardCode().matches(regex)) {
			
			//Check now if the Card Number Length is 9 and Card Code Length is 3
			if (addCardsRequest.getCardNumber().length() == 9 && addCardsRequest.getCardCode().length() ==3) {
				Map<String, String> response = starbucksDAO.insertCardDetails(addCardsRequest);
				if (response.get("status").equalsIgnoreCase("true")){
					outputMessage.setSuccessResonse("Card Successfully Added to the DB");
				}else {
					outputMessage.setErrorResponse("Insert Card Details Unsuccessfull");
				}
			}else {
				//If the Card Number and Card Code doesn't match the length
				outputMessage.setErrorResponse("Card Number length should be 9 and Card Code length should be 3 exactly");
			}
			
		}else
		{
			//If the Card Number and Card Code doesn't contain numbers
			outputMessage.setErrorResponse("Card Number or Card Code will accept only Digits");
		}
		
		
		return outputMessage;
	}

}