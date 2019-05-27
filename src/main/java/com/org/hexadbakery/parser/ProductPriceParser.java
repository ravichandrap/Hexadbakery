package com.org.hexadbakery.parser;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;
import java.util.Map;

import com.org.hexadbakery.models.ProductPrice;

public class ProductPriceParser implements Parser<List<ProductPrice>> {

	@Override
	public Map<String, List<ProductPrice>> parse(List<String> lines) {
		return lines.stream().map(this::getProductQuantityPrice).collect(groupingBy(ProductPrice::getProductCode));
	}

	private ProductPrice getProductQuantityPrice(String rowValue) {
		String[] values = rowValue.split(CSV_SEPARATOR);
		return new ProductPrice(values[0].trim(), parseInt(values[1].trim()), parseFloat(values[2].trim()));
	}
}
