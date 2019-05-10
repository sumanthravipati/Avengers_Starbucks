package com.avengers.starbucks.controller;

import com.avengers.starbucks.dto.*;
import com.avengers.starbucks.service.StarbucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;

@RestController
public class StarbucksController {

  @Autowired
  private StarbucksService starbucksService;

  //User sign up request mapping
  @RequestMapping(value = "/userSignup", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public ResponseEntity<GenericResponse> userSignup(@RequestBody SignupUser userRequest) {
    ResponseEntity<GenericResponse> responseEntity = null;
    try {
      GenericResponse response = this.starbucksService.SignupRequest(userRequest);
      responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      responseEntity = new ResponseEntity<GenericResponse>(new GenericResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
    }
    return responseEntity;
  }

  //User login request mapping
  @RequestMapping(value = "/userLogin", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public ResponseEntity<GenericResponse> userLogin(@RequestBody LoginUser userLoginRequest) {
    ResponseEntity<GenericResponse> responseEntity = null;
    try {
      GenericResponse response = this.starbucksService.LoginRequest(userLoginRequest);
      responseEntity = new ResponseEntity<GenericResponse>(response, HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      responseEntity = new ResponseEntity<GenericResponse>(new GenericResponse(), HttpStatus.EXPECTATION_FAILED);
    }
    return responseEntity;
  }

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
    StarbucksOutputMessage outputMessage = new StarbucksOutputMessage();
    if (orderRequest.getProducts() == null || orderRequest.getProducts().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "Ordered Items can not be empty");
    }

    if (orderRequest.getEmailId() == null || orderRequest.getEmailId().trim().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "EmailId can not be empty");
    }

    try {
      outputMessage = starbucksService.manageOrder(orderRequest);
    } catch (ValidationException e) {
      outputMessage.setErrorResponse("EmailID not registered !!");
    }
    return outputMessage;
  }


  //order payment
  @RequestMapping(value = "/doPayment", method = RequestMethod.POST, produces = "application/json")
  @ResponseBody
  public StarbucksOutputMessage doOrderPaymnet(@RequestBody PaymentRequest paymentRequest) {
    String emailId = paymentRequest.getEmailId();
    String cardNumber = paymentRequest.getCardNumber();
    int orderId = paymentRequest.getOrderId();
    
    StarbucksOutputMessage response =null;
    try {
    	response = this.starbucksService.doPayment(emailId, cardNumber, orderId);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

}