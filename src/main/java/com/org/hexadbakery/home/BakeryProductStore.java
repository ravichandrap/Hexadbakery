package com.org.hexadbakery.home;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.org.hexadbakery.models.Product;
import com.org.hexadbakery.models.ProductPrice;
import com.org.hexadbakery.parser.Parser;
import com.org.hexadbakery.parser.ProductParser;
import com.org.hexadbakery.parser.ProductPriceParser;
import com.org.hexadbakery.utils.FileUtils;

public class BakeryProductStore {
	public static final String PRODUCT_CSV_FILE = "product.csv";
	public static final String PRODUCT_PRICE_CSV_FILE = "product_price.csv";

	private static BakeryProductStore bakeryProductStore;
	private static Map<String, Product> products;

	public BakeryProductStore getInstance() {
		if (Objects.isNull(bakeryProductStore)) {
			bakeryProductStore = new BakeryProductStore();
		}
		return bakeryProductStore;
	}

	private BakeryProductStore() {
		loadProduct();
		loadPriceInProduct();
	}

	private void loadPriceInProduct() {
		Map<String, List<ProductPrice>> productsPriceMap = null;
		Parser<List<ProductPrice>> parser = new ProductPriceParser();

		try {
			productsPriceMap = parser.parse(FileUtils.getProducts(PRODUCT_PRICE_CSV_FILE));
			productsPriceMap.entrySet().forEach(entry -> {
				Product product = products.get(entry.getKey());

				entry.getValue().forEach(productPrice -> {

					product.addProduct(productPrice.getProductSize(), productPrice.getPrice());
				});
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadProduct() {
		Parser<Product> parser = new ProductParser();
		try {
			products = parser.parse(FileUtils.getProducts(PRODUCT_CSV_FILE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Product findProduct(String code) {
		return products.get(code);
	}
}
