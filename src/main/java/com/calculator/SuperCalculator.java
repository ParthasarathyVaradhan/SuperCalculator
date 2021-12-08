package com.calculator;

import java.util.Scanner;
import java.util.Stack;

import com.calculator.domain.Operator;
import com.calculator.exceptions.CalculatorExceptions;
import com.calculator.util.InputParser;

public class SuperCalculator {

	private Stack<Character> stackOfOperators;
	private Stack<Double> stackOfOperands;

	public SuperCalculator() {
		stackOfOperators = new Stack<Character>();
		stackOfOperands = new Stack<Double>();
	}

	private void processOperator(char t) {
		 Operator opr = Operator.getOperator(t);
		double a, b;
		if (stackOfOperands.empty()) {
			System.out.println("Expression error.");
			throw new CalculatorExceptions("Expression error.");
		} else {
			b = stackOfOperands.peek();
			stackOfOperands.pop();
		}
		if (stackOfOperands.empty()) {
			a = 0;
		} else {
			a = stackOfOperands.peek();
			stackOfOperands.pop();
		}
		try {
			double r = opr.performOperation(a, b);
			stackOfOperands.push(r);
		} catch (Exception e) {
			System.out.println();
			throw new CalculatorExceptions(e.getMessage());
		}
	}

	@SuppressWarnings("unused")
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
		input = InputParser.getParser().parse(input);
		
		// Processing all the input tokens
		boolean previousCharIsOperand = false;
		for ( int i = 0; i < input.length(); i++){
			char ch = input.charAt(i);
				if (ch >= '0' && ch <= '9') {
					Double previousChar = new Double("0");
					if(previousCharIsOperand){
						previousChar = stackOfOperands.peek();
						previousChar = previousChar*10;
						stackOfOperands.pop();
					}
					double value = (double) (ch - '0');
					value = previousChar + value ;
					stackOfOperands.push(value);
					previousCharIsOperand = true;
				} else if (Operator.isOperator(ch)) {
					if (stackOfOperators.empty()
							|| Operator.getPrecedence(ch) > Operator.getPrecedence(stackOfOperators.peek())) {
						stackOfOperators.push(ch);
					} else {
						while (!stackOfOperators.empty()
								&& Operator.getPrecedence(ch) <= Operator.getPrecedence(stackOfOperators.peek())) {
							char toProcess = stackOfOperators.peek();
							stackOfOperators.pop();
							processOperator(toProcess);
						}
						stackOfOperators.push(ch);
					}
					previousCharIsOperand = false;
				} else if (ch == CalculatorConstants.BRACKETS.OPEN.asChar()) {
					stackOfOperators.push(ch);
					previousCharIsOperand = false;
				} else if (ch == CalculatorConstants.BRACKETS.CLOSE.asChar()) {
					while (!stackOfOperators.empty()
							&& Operator.isOperator(stackOfOperators.peek())) {
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
					previousCharIsOperand = false;
				}
//			}

		}
		// Empty out the stackOfOperators at the end of the input
		while (!stackOfOperators.empty()
				&& Operator.isOperator(stackOfOperators.peek())) {
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