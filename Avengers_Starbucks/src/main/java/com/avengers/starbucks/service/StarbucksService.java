package com.avengers.starbucks.service;

import com.avengers.starbucks.dto.*;

import javax.xml.bind.ValidationException;

public interface StarbucksService {

  StarbucksOutputMessage addCards(AddCardsRequest addCardsRequest) throws ValidationException;

  StarbucksOutputMessage manageOrder(OrderRequest request) throws ValidationException;

  GenericResponse SignupRequest(SignupUser userRequest);

  GenericResponse LoginRequest(LoginUser userLoginRequest) throws ValidationException;

  StarbucksOutputMessage doPayment(String emailId, String cardNumber, int orderId);

  boolean checkduplicate();
}