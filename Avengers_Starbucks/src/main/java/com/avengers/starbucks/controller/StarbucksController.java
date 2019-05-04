package com.avengers.starbucks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.StarbucksOutputMessage;
import com.avengers.starbucks.service.StarbucksService;
import com.avengers.starbucks.dto.GenericResponse;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;

@RestController
public class StarbucksController {

    @Autowired
    private StarbucksService starbucksService;

    /User sign up request mapping
    @RequestMapping(value = "/userSignup", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public ResponseEntity<GenericResponse> userSignup(@RequestBody SignupUser userRequest) {
		ResponseEntity<GenericResponse> responseEntity = null;
		try {
			GenericResponse response = this.starbucksService.SignupRequest(userRequest);
			response.setMessage("User Sign Up Successful");
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
	public ResponseEntity<UserDetailsDTO> userLogin(@RequestBody LoginUser userLoginRequest) {
		ResponseEntity<UserDetailsDTO> responseEntity = null;
		try {
			UserDetailsDTO response = this.starbucksService.LoginRequest(userLoginRequest);
			responseEntity = new ResponseEntity<UserDetailsDTO>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<UserDetailsDTO>(new UserDetailsDTO(), HttpStatus.EXPECTATION_FAILED);
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
    
}