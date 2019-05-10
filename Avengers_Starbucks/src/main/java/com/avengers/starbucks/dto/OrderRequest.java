package com.avengers.starbucks.dto;

import com.avengers.starbucks.model.ProductRequest;

import java.util.List;


public class OrderRequest {

  private List<ProductRequest> products;
  private String emailId;

  public OrderRequest() {

  }

  public OrderRequest(List<ProductRequest> products, String emailId) {
    this.products = products;
    this.emailId = emailId;
  }

  public String getEmailId() {
    return emailId;
  }

  public void setEmailId(String emailId) {
    this.emailId = emailId;
  }

  public List<ProductRequest> getProducts() {
    return products;
  }

  public void setProducts(List<ProductRequest> products) {
    this.products = products;
  }

}
