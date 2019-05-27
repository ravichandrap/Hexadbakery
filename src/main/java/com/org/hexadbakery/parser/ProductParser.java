package com.org.hexadbakery.parser;

import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

import com.org.hexadbakery.models.Product;

public class ProductParser implements Parser<Product> {

	@Override
	public Map<String, Product> parse(List<String> lines) {
		return lines.stream().map(this::getProduct).collect(toMap(Product::getCode, product -> product));
	}

	private Product getProduct(String row) {
		String[] values = row.split(CSV_SEPARATOR);
		return new Product(values[0].trim(), values[1].trim());
	}
}
