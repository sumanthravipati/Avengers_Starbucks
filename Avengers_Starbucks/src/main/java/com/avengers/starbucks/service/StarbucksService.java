package com.avengers.starbucks.service;

import javax.xml.bind.ValidationException;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;

public interface StarbucksService{
    
	StarbucksOutputMessage addCards(AddCardsRequest addCardsRequest) throws ValidationException;
    
}