package com.org.hexadbakery.home;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.org.hexadbakery.models.Product;

public class BakeryStore {

	private BakeryStore bakeryStore;
	private Map<String, Product> products = new HashMap<String, Product>();

	private BakeryStore() {

	}

	private BakeryStore getInstance() {
		if (Objects.isNull(bakeryStore)) {
			bakeryStore = new BakeryStore();
		}
		return bakeryStore;
	}

	public Product getProduct(String code) {
		return this.products.get(code);
	}
}
