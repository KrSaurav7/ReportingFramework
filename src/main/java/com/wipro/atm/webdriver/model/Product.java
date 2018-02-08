package com.wipro.atm.webdriver.model;

public class Product {
    private String productQty;

    public Product(String productQty) {

        this.productQty = productQty;
    }
    public String getProductQty() {

        return productQty;
    }

    public void setProductQty(String productQty) {

        this.productQty = productQty;
    }

}
