package com.org.hexadbakery.utils;

import static java.util.Objects.isNull;

import java.util.Scanner;

import com.org.hexadbakery.user.Reader;

public class UserInputReader implements Reader {
	private static Reader reader;
	private static Scanner scanner;

	private UserInputReader() {
		scanner = new Scanner(System.in);
	}

	public static Reader getInstance() {
		if (isNull(reader)) {
			reader = new UserInputReader();
		}

		return reader;
	}

	@Override
	public String read() {
		return scanner.nextLine();
	}
}
