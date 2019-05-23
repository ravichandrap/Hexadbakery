package com.org.hexadbakery.parser;

import java.util.List;
import java.util.Map;

@FunctionalInterface
public interface Parser<T> {
	String CSV_SEPARATOR = ",";
	String USER_INPUT_SEPARATOR = " ";

	Map<String, T> parse(List<String> lines);
}
