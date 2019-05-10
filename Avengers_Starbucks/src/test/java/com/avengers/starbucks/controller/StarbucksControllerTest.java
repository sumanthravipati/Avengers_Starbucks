package com.avengers.starbucks.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.avengers.starbucks.AbstractClass;
import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.OrderRequest;
import com.avengers.starbucks.dto.PaymentRequest;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.model.ProductRequest;

public class StarbucksControllerTest extends AbstractClass {
	
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void testuserLogin() throws Exception {
		String uri = "/userLogin";
		
		LoginUser userLoginRequest = new LoginUser();
		
		userLoginRequest.setEmailId("sunny@gmail.com");
		userLoginRequest.setPassword("sunny");
		
		String inputJson = super.mapToJson(userLoginRequest);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(statusCode, 200);
	}
	
	@Test
	public void testUserSignUp() throws Exception {
		String uri = "/userSignup";
		
		SignupUser signupRequest = new SignupUser();
		
		signupRequest.setEmailId("sunny@gmail.com");
		signupRequest.setFirstName("Sumanth");
		signupRequest.setLastName("Ravipati");
		signupRequest.setPassword("1234");
		
		String inputJson = super.mapToJson(signupRequest);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(statusCode, 200);
	}
	
	@Test
	public void testAddCards() throws Exception {
		String uri = "/addCards";
		
		AddCardsRequest addCardsRequest = new AddCardsRequest();
		
		addCardsRequest.setEmailId("sunny@gmail.com");
		addCardsRequest.setCardNumber("123456789");
		addCardsRequest.setCardCode("123");
		
		String inputJson = super.mapToJson(addCardsRequest);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(statusCode, 200);
	}
	
	@Test
	public void testManageOrder() throws Exception {
		String uri = "/manageOrder";
		
		OrderRequest orderRequest = new OrderRequest();
		
		List<ProductRequest> list = new ArrayList<ProductRequest>();
		ProductRequest productRequest  = new ProductRequest(1, 2);
		list.add(productRequest);
		orderRequest.setProducts(list);
		orderRequest.setEmailId("sunny@gmail.com");
		
		String inputJson = super.mapToJson(orderRequest);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(statusCode, 200);
	}
	
	@Test
	public void testPaymentAPI() throws Exception {
		String uri = "/doPayment";
		
		PaymentRequest paymentRequest = new PaymentRequest();
		
		paymentRequest.setCardNumber("123456789");
		paymentRequest.setEmailId("sunny@gmail.com");
		paymentRequest.setOrderId(1);
		
		String inputJson = super.mapToJson(paymentRequest);
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
		int statusCode = mvcResult.getResponse().getStatus();
		assertEquals(statusCode, 200);
	}
	
	
}

