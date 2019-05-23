package com.org.hexadbakery.user;

import java.util.Objects;
import java.util.Scanner;

public class InputReader implements Reader {

	private static Reader reader;
	private static Scanner scanner;

	private InputReader() {
		scanner = new Scanner(System.in);
	}

	public static Reader getInstance() {
		if (Objects.isNull(reader))
			reader = new InputReader();
		return reader;
	}

	@Override
	public String read() {
		return scanner.nextLine();
	}

}
