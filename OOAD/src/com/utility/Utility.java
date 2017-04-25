package com.utility;

import java.util.Scanner;

public class Utility {

	/**
	 * Validate UserInput against accepted values
	 * 
	 * @param user_input
	 * @param accepted_valued
	 * @return
	 */
	public static boolean validateInput(String user_input,
			String accepted_valued) {

		if (accepted_valued.equals("")) {
			return true;
		}

		if (user_input == null || user_input.equals("")) {
			return false;
		}

		String[] inputOptions = accepted_valued.split(",");

		for (String inputOption : inputOptions) {
			if (inputOption.equals(user_input)) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Take user's input and validate
	 * 
	 * @param prompt
	 * @param accepetedValues
	 * @return
	 */
	public static String showPromptForInput(String prompt,
			String accepetedValues) {
		Scanner reader = new Scanner(System.in); // Reading from System.in
		String input = "";
		do {
			displayToScreen(prompt);
			input = reader.nextLine().trim();
		} while (!validateInput(input, accepetedValues));
		return input;
	}

	/**
	 * Show message on screen
	 * 
	 * @param msg
	 */
	public static void displayToScreen(String msg) {
		System.out.println(msg);
	}
}
