/*package com.avengers.starbucks.controller;

import com.avengers.starbucks.dto.OrderRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.exception.InvalidRequestException;
import com.avengers.starbucks.service.StarbucksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManageOrderController {

  @Autowired
  private StarbucksService starbucksService;

  @PostMapping(path = "/manageOrder", consumes = {"application/json"}, produces = "application/json")
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

}*/
