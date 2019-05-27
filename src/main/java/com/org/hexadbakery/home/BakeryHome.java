package com.org.hexadbakery.home;

import com.org.hexadbakery.user.Reader;
import com.org.hexadbakery.utils.UserInputReader;

public class BakeryHome {
	private Reader reader = UserInputReader.getInstance();
	private OrderProcessor orderProcessor;
	private boolean open;

	public void open() {
		this.open = true;
		orderProcessor = new OrderProcessor(this);

		while (open) {
			System.out.println("Please place your order.(Type 0/exit to exit).");
			System.out.println(orderProcessor.process(reader.read()));
			System.out.println("\n");
		}
	}

	public void close() {
		this.open = false;
	}
}
