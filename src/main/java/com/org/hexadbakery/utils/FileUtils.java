package com.org.hexadbakery.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

	public static List<String> getProducts(String fileName) {
		List<String> products = null;
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(fileName)))) {

			products = bufferedReader.lines().skip(1).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

	public static void main(String[] args) {
		System.out.println(getProducts(BakeryConstants.PRODUCT_FILE));
	}
}
