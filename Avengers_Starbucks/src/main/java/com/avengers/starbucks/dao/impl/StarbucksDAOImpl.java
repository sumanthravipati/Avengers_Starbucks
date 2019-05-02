
package com.avengers.starbucks.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.avengers.starbucks.dao.StarbucksDAO;
import com.avengers.starbucks.dto.AddCardsRequest;

@Repository("mysql")
public class StarbucksDAOImpl implements StarbucksDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public Map<String, String> insertCardDetails(AddCardsRequest addCardsRequest) {
		
		HashMap<String, String> outputMap = new HashMap<>();
		
		String startingBalance = "100";
		
		try {
			String sql = "INSERT INTO Starbucks_AddCards (FirstName, LastName, EmailID, CardNumber,"
					+ " CardCode, CardBalance) VALUES (?, ?, ?, ?, ?, ?)";
			
			jdbcTemplate.update(sql, addCardsRequest.getFirstName(), addCardsRequest.getLastName(), addCardsRequest.getEmailId(),
					addCardsRequest.getCardNumber(), addCardsRequest.getCardCode(), startingBalance);
			
			outputMap.put("status", "true");
			
		}catch (Exception e)
		{
			outputMap.put("status", "false");
		}
		return outputMap;
	}

	@Override
	public Map<String, String> getCardDetails(AddCardsRequest addCardsRequest) {
		// TODO Auto-generated method stub
		return null;
	}

}
