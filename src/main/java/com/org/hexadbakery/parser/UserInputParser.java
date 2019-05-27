package com.org.hexadbakery.parser;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;

import com.org.hexadbakery.exceptions.InputException;

public class UserInputParser implements Parser<Integer> {

	@Override
	public Map<String, Integer> parse(List<String> lines) {
		try {
			return lines.stream().map(line -> line.split(USER_INPUT_SEPARATOR))
					.collect(toMap(o -> o[1].trim(), o -> parseInt(o[0].trim())));
		} catch (Exception ex) {
			throw new InputException("Invalid input");
		}
	}
}
