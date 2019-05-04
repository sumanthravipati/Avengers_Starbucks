package com.avengers.starbucks.db;

import com.avengers.starbucks.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper {
  @Override
  public Object mapRow(ResultSet resultSet, int i) throws SQLException {
    Product product = new Product();
    product.setProductId(resultSet.getInt("id"));
    product.setProductName(resultSet.getString("ProductName"));
    product.setPrice(resultSet.getFloat("Price"));
    product.setQty(resultSet.getInt("Qty"));
    return product;
  }
}
