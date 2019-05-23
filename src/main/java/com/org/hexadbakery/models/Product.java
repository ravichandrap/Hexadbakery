package com.org.hexadbakery.models;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Product {

	private String name;
	private String code;
	private Map<Integer, Float> products;

	public Product(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void addProduct(Integer size, Float price) {
		this.products.put(size, price);
	}

	public Float getPrice(Integer size) {
		return this.products.get(size);
	}

	public List<Integer> getProductsList() {
		return this.products.keySet().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
	}

	@Override
	public String toString() {
		return "Products [name=" + name + ", code=" + code + "]";
	}

}
