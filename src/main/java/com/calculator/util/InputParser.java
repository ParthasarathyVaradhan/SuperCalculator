package com.calculator.util;

import com.calculator.exceptions.CalculatorExceptions;

public class InputParser {
	private TextPropertiesLoader operatorsUtil;
	private static InputParser parser = new InputParser();

	public static InputParser getParser() {
		return parser;
	}

	public InputParser() {
		operatorsUtil = new TextPropertiesLoader();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input = "1235*(3+4)-456";
		System.out.println(InputParser.getParser().parse(input));
	}

	private String changeStringOperators(String input) {
		for (String operatorkey : operatorsUtil.getAllOperators().keySet()) {
			if (input.toLowerCase().contains(operatorkey)) {
				input = input.replace(operatorkey, operatorsUtil
						.getAllOperators().get(operatorkey));
			}
		}
		return input;
	}

	public String parse(String input) {
		// TODO Auto-generated method stub

		// replacing all whitespaces
		input = input.replaceAll("\\s", "");

		// Changing input text operators to chars
		input = changeStringOperators(input);

		// Return input only if the input is valid
		if (inputPatternCheck(input)) {
			return input;
		} else {
			throw new CalculatorExceptions("Failed in parsing the input "
					+ input);
		}
	}

	private boolean inputPatternCheck(String input) {
		// TODO Auto-generated method stub
		return input.matches("[0-9+#-\\*\\.\\-\\s+\\/()\\(\\)]+");
	}

}
