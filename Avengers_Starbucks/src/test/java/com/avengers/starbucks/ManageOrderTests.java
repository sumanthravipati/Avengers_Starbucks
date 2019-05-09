package com.avengers.starbucks;

import com.avengers.starbucks.controller.StarbucksController;
import com.avengers.starbucks.dto.OrderRequest;
import com.avengers.starbucks.model.ProductRequest;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class ManageOrderTests {

  StarbucksController controller = new StarbucksController();


  @Test(expected = ResponseStatusException.class)
  public void manageOrderNullProductTest() {
    OrderRequest request = new OrderRequest();
    request.setProducts(null);
    controller.manageOrder(request);
  }

  @Test(expected = ResponseStatusException.class)
  public void manageOrderEmptyProductTest() {
    List<ProductRequest> products = new ArrayList<>();
    OrderRequest request = new OrderRequest();
    request.setProducts(products);
    controller.manageOrder(request);
  }

  @Test(expected = ResponseStatusException.class)
  public void manageOrderNullEmailTest() {
    List<ProductRequest> products = new ArrayList<>();
    products.add(new ProductRequest(1, 5));

    OrderRequest request = new OrderRequest();
    request.setProducts(products);
    request.setEmailId(null);
    controller.manageOrder(request);
  }

  @Test(expected = ResponseStatusException.class)
  public void manageOrderEmptyEmailTest1() {
    List<ProductRequest> products = new ArrayList<>();
    products.add(new ProductRequest(1, 5));

    OrderRequest request = new OrderRequest();
    request.setProducts(products);
    request.setEmailId("");
    controller.manageOrder(request);
  }

  @Test(expected = ResponseStatusException.class)
  public void manageOrderEmptyEmailTest2() {
    List<ProductRequest> products = new ArrayList<>();
    products.add(new ProductRequest(1, 5));

    OrderRequest request = new OrderRequest();
    request.setProducts(products);
    request.setEmailId("  ");
    controller.manageOrder(request);
  }
}
