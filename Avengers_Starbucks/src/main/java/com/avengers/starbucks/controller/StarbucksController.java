package com.avengers.starbucks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.OrderRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.exception.InvalidRequestException;
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
    
    @RequestMapping(path = "/manageOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public StarbucksOutputMessage manageOrder(@RequestBody OrderRequest orderRequest) {
      if (orderRequest == null) {
        throw new InvalidRequestException("Order can not be null");
      }

      if (orderRequest.getProducts() == null || orderRequest.getProducts().isEmpty()) {
        throw new InvalidRequestException("Ordered Items can not be empty");
      }

      if (orderRequest.getEmailId() == null || orderRequest.getEmailId().trim().isEmpty()) {
        throw new InvalidRequestException("EmailId can not be empty");
      }

      return starbucksService.manageOrder(orderRequest);
    }
    
}