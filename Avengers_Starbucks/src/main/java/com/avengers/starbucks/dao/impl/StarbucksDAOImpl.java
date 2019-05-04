
package com.avengers.starbucks.dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.avengers.starbucks.dao.StarbucksDAO;
import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;

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
	
	String passwordToHash = "password";
    String generatedPassword = null;
	

	@Override
	public void createUser(SignupUser userRequest) {
		
		String sql = "INSERT INTO Profile_Info (First_Name, Last_Name, Email_Id, Password) VALUES (?, ?, ?, ?)";
		
		passwordToHash = userRequest.getPassword();
		
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		
		jdbcTemplate.update(sql, userRequest.getFirstName(), userRequest.getLastName(), 
				userRequest.getEmailId(),generatedPassword );
	}

	@Override
	public UserDetailsDTO getUserDetails(LoginUser userLoginRequest) {
		
		String sql = "SELECT * FROM Profile_Info WHERE Email_Id = ? AND Password = ?";
		
		String pwd = userLoginRequest.getPassword();
		String check = null;
		
		try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pwd.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            check = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
		
		
		
		UserDetailsDTO userDetailsDTO = (UserDetailsDTO) jdbcTemplate.queryForObject(
				sql, new Object[] { userLoginRequest.getEmailId(), check }, 
				new BeanPropertyRowMapper(UserDetailsDTO.class));
		return userDetailsDTO;
	}


}


