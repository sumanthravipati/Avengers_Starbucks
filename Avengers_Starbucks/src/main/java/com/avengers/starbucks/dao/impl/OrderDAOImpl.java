/*package com.avengers.starbucks.dao.impl;

import com.avengers.starbucks.dao.OrderDAO;
import com.avengers.starbucks.db.ProductRowMapper;
import com.avengers.starbucks.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("mysql")
public class OrderDAOImpl implements OrderDAO {

  private final String TABLE_PRODUCT = "Starbucks_Product";
  private final String TABLE_ORDER = "Starbucks_Order";
  @Autowired
  private JdbcTemplate jdbcTemplate;

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
}*/
