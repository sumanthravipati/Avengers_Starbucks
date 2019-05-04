package com.avengers.starbucks.model;

public class ProductRequest {

  private int productId;
  private int qty;

  public ProductRequest(int productId, int qty) {
    this.productId = productId;
    this.qty = qty;
  }

  public int getProductId() {
    return productId;
  }


  public int getQty() {
    return qty;
  }

}
