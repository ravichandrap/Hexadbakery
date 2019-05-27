package com.org.hexadbakery.parser;

import static java.lang.Integer.parseInt;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserInputParser implements Parser<Integer> {

	@Override
	public Map<String, Integer> parse(List<String> lines) {

		lines.stream().map(line -> line.split(","))
				.collect(Collectors.toMap(o -> o[1].trim(), o -> parseInt(o[0].trim())));
		return null;
	}

}
