package com.avengers.starbucks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.service.StarbucksService;

@RestController
public class StarbucksController {

    @Autowired
    private StarbucksService starbucksService;

    //User login in request mapping
    @RequestMapping(value = "/addCards", method = RequestMethod.POST, produces = "application/json")
   	@ResponseBody
   	public StarbucksOutputMessage addCards(@RequestBody AddCardsRequest addCardsRequest) {
    	StarbucksOutputMessage response = null;
   		try {
   			response = this.starbucksService.addCards(addCardsRequest);
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   		return response;
   	}
    
}