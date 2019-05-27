package com.org.hexadbakery.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import com.org.hexadbakery.exceptions.ReaderException;

public class FileUtils {
	public static List<String> getProducts(String fileName) {
		try (BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(FileUtils.class.getClassLoader().getResourceAsStream(fileName)))) {

			return bufferedReader.lines().skip(1).collect(Collectors.toList());
		} catch (Exception e) {
			throw new ReaderException("Error while reading csv: " + fileName);
		}
	}
}
