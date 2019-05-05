
package com.avengers.starbucks.dao.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.avengers.starbucks.dao.StarbucksDAO;
import com.avengers.starbucks.db.ProductRowMapper;
import com.avengers.starbucks.dto.AddCardsRequest;
import com.avengers.starbucks.dto.LoginUser;
import com.avengers.starbucks.dto.SignupUser;
import com.avengers.starbucks.dto.UserDetailsDTO;
import com.avengers.starbucks.model.Product;

@Repository("mysql")
public class StarbucksDAOImpl implements StarbucksDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String TABLE_PRODUCT = "Starbucks_Product";
	private final String TABLE_ORDER = "Starbucks_Order";
	
	String passwordToHash = "password";
    String generatedPassword = null;


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
	public List<AddCardsRequest> getCardDetails(String emailId) {
		
		String sql = "SELECT * FROM Starbucks_AddCards where EmailID = ?";
		
		List<AddCardsRequest> addCardsList = new ArrayList<AddCardsRequest>();
		
		List<java.util.Map<String, Object>> result = jdbcTemplate.queryForList(sql, emailId);
		
		for(java.util.Map<String, Object> obj : result)
		{
			AddCardsRequest addCardDetails = new AddCardsRequest();
			
			addCardDetails.setCardCode((String)obj.get("CardCode"));
			addCardDetails.setCardNumber((String)obj.get("CardNumber"));
			addCardDetails.setEmailId((String)obj.get("EmailID"));
			addCardDetails.setFirstName((String)obj.get("FirstName"));
			addCardDetails.setLastName((String)obj.get("LastName"));
			
			addCardsList.add(addCardDetails);
		}
		return addCardsList;
	}
	
	@Override
	public float getCardBalance(String emailId, String cardNumber) {
		float cardBalance = -999999999;
		String cardBalanceStr = null;
		String sql = "SELECT CardBalance FROM Starbucks_AddCards where EmailID = ? and CardNumber = ?";
		try {
			cardBalanceStr = (String) jdbcTemplate.queryForObject(sql, new Object[]{emailId, cardNumber}, String.class);
		}catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();
		}		
		if(cardBalanceStr != null)
			cardBalance = Float.parseFloat(cardBalanceStr);
		return cardBalance;
	}
	
	@Override
	public float getOrderAmount(String emailId, int orderId) {
		float orderAmount = -999999999;
	//	String cardBalanceStr = null;
		String sql = "SELECT Amount FROM Starbucks_Order where EmailID = ? and id = ?";
		try {
			orderAmount = jdbcTemplate.queryForObject(sql, new Object[]{emailId, orderId}, Float.class);
		}catch(EmptyResultDataAccessException e) {
			//e.printStackTrace();
		}		
		return orderAmount;
	}
	
	@Override
	public void updateOnSuccessfulPayment(String emailId, String cardNumber, int orderId, String new_balance) {
		//UPDATE `starbucks`.`Starbucks_Order` SET `Amount` = '5' WHERE (`id` = '3');
		String sql = "UPDATE Starbucks_AddCards SET CardBalance = ? WHERE EmailID = ? and CardNumber = ?";
		jdbcTemplate.update(sql, new_balance, emailId, cardNumber);
		
		//set paid true in order table
		String sql1 = "UPDATE Starbucks_Order SET Paid = ? WHERE id = ?";
		jdbcTemplate.update(sql1, 1, orderId);
	}
	
	@Override
	  public boolean insertOrder(String emailId, String orderDescription, float billingAmt) {
	    String sql = "INSERT INTO " + TABLE_ORDER + " (EmailID, Description, Amount, Paid)"
	        + " VALUES (?, ?, ?, ?)";
	    return jdbcTemplate.update(sql, emailId, orderDescription, billingAmt, false) > 0;
	  }

	  @Override
	  public Product getProductDetail(int productId) {
	    String query = "Select * from " + TABLE_PRODUCT + " where id = ?";
	    List<Product> products = jdbcTemplate.query(query, new Object[]{productId}, new ProductRowMapper());
	    if (products.isEmpty()) {
	      return null;
	    } else {
	      return products.get(0);
	    }
	  }

	  @Override
	  public void updateStock(int productId, int quantity) {

	  }
	  
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
