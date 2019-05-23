package com.org.hexadbakery.models;

public class ProductPrice {

	private String productCode;
	private Integer productSize;
	private Float price;

	public ProductPrice(String productCode, Integer productSize, Float price) {
		super();
		this.productCode = productCode;
		this.productSize = productSize;
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public Integer getProductSize() {
		return productSize;
	}

	public Float getPrice() {
		return price;
	}

}
