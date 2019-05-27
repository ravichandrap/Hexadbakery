package com.org.hexadbakery.home;

import static java.util.Arrays.asList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.org.hexadbakery.exceptions.InputException;
import com.org.hexadbakery.models.Product;
import com.org.hexadbakery.parser.Parser;
import com.org.hexadbakery.parser.UserInputParser;

public class OrderProcessor {
	public static final List<String> EXIT_COMMANDS = asList("EXIT", "exit", "0");
	public static final String NEWLINE = "\n";
	public static final String CURRENCY = "$";
	public static final String SPACE = " ";
	public static final String TABSPACE = "\t";

	BakeryProductStore store = BakeryProductStore.getInstance();
	Parser<Integer> parser = new UserInputParser();
	BakeryHome bakery = null;

	OrderProcessor(BakeryHome backery) {
		this.bakery = backery;
	}

	public String process(String inputString) {
		String result = null;
		if (!EXIT_COMMANDS.contains(inputString.trim())) {
			try {
				Map<String, Integer> input = parser.parse(Collections.singletonList(inputString));

				input.entrySet().stream().map(userInputEntry -> {
					final Product product = store.findProduct(userInputEntry.getKey());
					final Integer quantity = userInputEntry.getValue();

					return printBill(calculateBill(product, quantity), product, quantity);
				}).collect(Collectors.joining(NEWLINE));

			} catch (InputException ie) {
				return ie.getMessage();
			}
		}
		return result;
	}

	private String printBill(Map<Integer, Integer> output, Product product, Integer quantity) {
		if (output.isEmpty()) {
			return "Invalid Product count";
		} else {
			StringBuffer outputBuffer = new StringBuffer();
			float totalOrderValue = 0f;

			for (Integer packSize : output.keySet()) {
				totalOrderValue += output.get(packSize) * product.getPrice(packSize);

				outputBuffer.append(NEWLINE + TABSPACE + output.get(packSize) + " X " + packSize + CURRENCY
						+ product.getPrice(packSize));
			}

			return quantity + SPACE + product + SPACE + CURRENCY + totalOrderValue + outputBuffer.toString();
		}
	}

	private Map<Integer, Integer> calculateBill(Product product, Integer quantity) {
		Map<Integer, Integer> output = new HashMap<>();

		List<Integer> packSizeList = product.getProductsList();

		int q = quantity;
		int start = 0;
		int packSize = 0;

		while (q > 0 && start < packSizeList.size()) {
			if (packSize > 0) {
				if (packSizeList.indexOf(packSize) + 1 == packSizeList.size()) {
					packSize = packSizeList.get(0);
				}

				if (output.containsKey(packSize)) {
					q = q + packSize;

					if (output.get(packSize) > 1) {
						output.put(packSize, output.get(packSize) - 1);
					} else {
						output.remove(packSize);
					}

					start = packSizeList.indexOf(packSize) + 1;
				}
			}

			for (int i = start; i < packSizeList.size(); i++) {
				if (q / packSizeList.get(i) > 0) {
					packSize = packSizeList.get(i);
					output.put(packSize, q / packSize);
					q = q % packSize;
				}
			}

			start++;
		}

		if (q > 0) {
			output.clear();
		}

		return output;
	}
}
