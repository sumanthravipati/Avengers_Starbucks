package com.avengers.starbucks.service.impl;

import com.avengers.starbucks.dao.OrderDAO;
import com.avengers.starbucks.dao.StarbucksDAO;
import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.OrderRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.model.Product;
import com.avengers.starbucks.model.ProductRequest;
import com.avengers.starbucks.service.StarbucksService;
import com.avengers.starbucks.dao.UserInfo;
import com.avengers.starbucks.dto.GenericResponse;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class StarbucksServiceImpl implements StarbucksService {

  Logger logger = LoggerFactory.getLogger(StarbucksServiceImpl.class);

  @Autowired
  StarbucksDAO starbucksDAO;

  @Autowired
  OrderDAO orderDao;

  @Autowired
  private UserInfo userDAO;
	
	@Override
	public GenericResponse SignupRequest(SignupUser userRequest) {
		
		userDAO.createUser(userRequest);
		GenericResponse response = new GenericResponse("SUCCESS");
		return response;
	}

	@Override
	public UserDetailsDTO LoginRequest(LoginUser userLoginRequest) throws ValidationException {
		UserDetailsDTO userDetailsDTO = userDAO.getUserDetails(userLoginRequest);
		if (null == userDetailsDTO) {
			throw new ValidationException("Invalid email Id/password");
		}
		return userDetailsDTO;
	}
	
  @Override
  public StarbucksOutputMessage addCards(AddCardsRequest addCardsRequest) throws ValidationException {

    StarbucksOutputMessage outputMessage = new StarbucksOutputMessage();

    //Scenario -1: Check if the Card Number and the Card Code contains only Digits
    String regex = "\\d+";
    if (addCardsRequest.getCardNumber().matches(regex) && addCardsRequest.getCardCode().matches(regex)) {

      //Check now if the Card Number Length is 9 and Card Code Length is 3
      if (addCardsRequest.getCardNumber().length() == 9 && addCardsRequest.getCardCode().length() == 3) {
        Map<String, String> response = starbucksDAO.insertCardDetails(addCardsRequest);
        if (response.get("status").equalsIgnoreCase("true")) {
          outputMessage.setSuccessResponse("Card Successfully Added to the DB");
        } else {
          outputMessage.setErrorResponse("Insert Card Details Unsuccessfull");
        }
      } else {
        //If the Card Number and Card Code doesn't match the length
        outputMessage.setErrorResponse("Card Number length should be 9 and Card Code length should be 3 exactly");
      }

    } else {
      //If the Card Number and Card Code doesn't contain numbers
      outputMessage.setErrorResponse("Card Number or Card Code will accept only Digits");
    }


    return outputMessage;
  }

  @Override
  public StarbucksOutputMessage manageOrder(OrderRequest order) {

    StarbucksOutputMessage outputMessage = new StarbucksOutputMessage();
    List<Product> products = new ArrayList<>();

    for (int i = 0; i < order.getProducts().size(); i++) {
      ProductRequest productReq = order.getProducts().get(i);
      Product product = orderDao.getProductDetail(productReq.getProductId());
      if (product != null) {
        if (!(product.getQty() >= productReq.getQty())) {
          outputMessage.setErrorResponse("Product not in stock");
          return outputMessage;
        }
        products.add(product);
      } else {
        outputMessage.setErrorResponse("Invalid ProductID, cna not place order !!");
        return outputMessage;
      }
    }

    String orderDescription = generateDescription(products);
    logger.error("Description : " + orderDescription);
    float billingAmt = calcBillAmt(products, order.getProducts());

    if (orderDao.insertOrder(order.getEmailId(), orderDescription, billingAmt)) {
      outputMessage.setSuccessResponse("Order Placed Successfully.");
    } else {
      outputMessage.setErrorResponse("Can not place order!!");
    }

    return outputMessage;
  }

  private float calcBillAmt(List<Product> products, List<ProductRequest> orderedProducts) {
    float total = 0f;
    int i = 0;
    for (Product product : products) {
      total += product.getPrice() * orderedProducts.get(i).getQty();
      i++;
    }
    return total;
  }

  private String generateDescription(List<Product> products) {
    StringBuilder orderDescription = new StringBuilder();
    orderDescription.append(products.get(0).getProductName());
    for (int i = 1; i < products.size(); i++) {
      orderDescription.append(",");
      orderDescription.append(products.get(i).getProductName());
    }
    return orderDescription.toString();
  }

}