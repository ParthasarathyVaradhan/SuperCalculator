package com.calculator;

import java.util.Scanner;
import java.util.Stack;

import com.calculator.exceptions.CalculatorExceptions;
import com.calculator.util.OperatorsUtil;

public class SuperCalculator {

	private Stack<Character> stackOfOperators;
	private Stack<Double> stackOfOperands;
	private OperatorsUtil operators;

	public SuperCalculator() {
		stackOfOperators = new Stack<Character>();
		stackOfOperands = new Stack<Double>();
		operators = new OperatorsUtil();
	}

	private String changeStringOperators(String input) {
		for (String operatorkey : operators.getAllOperators().keySet()) {
			if (input.toLowerCase().contains(operatorkey)) {
				input = input.replace(operatorkey, operators.getAllOperators()
						.get(operatorkey));
			}
		}
		return input;
	}

	private void processOperator(char t) {
		double a, b;
		if (stackOfOperands.empty()) {
			System.out.println("Expression error.");
			throw new CalculatorExceptions("Expression error.");
		} else {
			b = stackOfOperands.peek();
			stackOfOperands.pop();
		}
		if (stackOfOperands.empty()) {
			System.out.println("Expression error.");
			throw new CalculatorExceptions("Expression error.");
		} else {
			a = stackOfOperands.peek();
			stackOfOperands.pop();
		}
		try {
			double r = operators.performOperation(t, a, b);
			stackOfOperands.push(r);
		} catch (Exception e) {
			System.out.println();
			throw new CalculatorExceptions(e.getMessage());
		}
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public void processInput(String input) {

		// Change the operators from string to character (plus -> +, minus -> -)
		input = changeStringOperators(input);

		// The tokens that make up the input
		String[] tokens = input.split(" ");

		// Processing all the input tokens
		for (int n = 0; n < tokens.length; n++) {
			String nextToken = tokens[n];
			if (nextToken.length() != 1) {
				if (isNumeric(nextToken)) {
					double value = Double.parseDouble(nextToken);
					stackOfOperands.push(value);
					continue;
				}
			} else {
				char ch = nextToken.charAt(0);
				if (ch >= '0' && ch <= '9') {
					double value = Double.parseDouble(nextToken);
					stackOfOperands.push(value);
				} else if (operators.isOperator(ch)) {
					if (stackOfOperators.empty()
							|| operators.fetchPrecedence(ch) > operators
									.fetchPrecedence(stackOfOperators.peek())) {
						stackOfOperators.push(ch);
					} else {
						while (!stackOfOperators.empty()
								&& operators.fetchPrecedence(ch) <= operators
										.fetchPrecedence(stackOfOperators
												.peek())) {
							char toProcess = stackOfOperators.peek();
							stackOfOperators.pop();
							processOperator(toProcess);
						}
						stackOfOperators.push(ch);
					}
				} else if (ch == '(') {
					stackOfOperators.push(ch);
				} else if (ch == ')') {
					while (!stackOfOperators.empty()
							&& operators.isOperator(stackOfOperators.peek())) {
						char toProcess = stackOfOperators.peek();
						stackOfOperators.pop();
						processOperator(toProcess);
					}
					if (!stackOfOperators.empty()
							&& stackOfOperators.peek() == '(') {
						stackOfOperators.pop();
					} else {
						System.out.println("Error: unbalanced parenthesis.");
						throw new CalculatorExceptions(
								"Error: unbalanced parenthesis.");
					}
				}
			}

		}
		// Empty out the stackOfOperators at the end of the input
		while (!stackOfOperators.empty()
				&& operators.isOperator(stackOfOperators.peek())) {
			char toProcess = stackOfOperators.peek();
			stackOfOperators.pop();
			processOperator(toProcess);
		}
		// Print the result
		double result = stackOfOperands.peek();
		stackOfOperands.pop();
		if (!stackOfOperators.empty() || !stackOfOperands.empty()) {
			throw new CalculatorExceptions("Expression error.");
		} else {
			System.out.println("The result is " + result);
		}
	}

	public static void main(String[] args) {
		String userInput = null;
		if (args.length == 0) {
			Scanner input = new Scanner(System.in);
			System.out.print("Input expression : ");
			userInput = input.nextLine();
			input.close();
		} else {
			userInput = args[0];
		}
		SuperCalculator calc = new SuperCalculator();
		calc.processInput(userInput);
		
	}
}