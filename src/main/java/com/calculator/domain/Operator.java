package com.calculator.domain;

import com.calculator.CalculatorConstants;

public abstract class Operator {

	public abstract double performOperation(double a, double b);

	public abstract int fetchPrecedence();

	public static boolean isOperator(char ch) {
		return ch == CalculatorConstants.OPERATORS.PLUS.asChar()
				|| ch == CalculatorConstants.OPERATORS.MINUS.asChar()
				|| ch == CalculatorConstants.OPERATORS.INTO.asChar()
				|| ch == CalculatorConstants.OPERATORS.BY.asChar();
	}

	public static Operator getOperator(char operator) {
		if (CalculatorConstants.OPERATORS.PLUS.asChar() == operator) {
			return OperatorPlus.getSpecificOperator();
		} else if (CalculatorConstants.OPERATORS.MINUS.asChar() == operator) {
			return OperatorMinus.getSpecificOperator();
		} else if (CalculatorConstants.OPERATORS.INTO.asChar() == operator) {
			return OperatorInto.getSpecificOperator();
		} else if (CalculatorConstants.OPERATORS.BY.asChar() == operator) {
			return OperatorBy.getSpecificOperator();
		}
		return null;
	}

	public static int getPrecedence(char operator) {
		if (CalculatorConstants.OPERATORS.PLUS.asChar() == operator) {
			return OperatorPlus.getSpecificOperator().fetchPrecedence();
		} else if (CalculatorConstants.OPERATORS.MINUS.asChar() == operator) {
			return OperatorMinus.getSpecificOperator().fetchPrecedence();
		} else if (CalculatorConstants.OPERATORS.INTO.asChar() == operator) {
			return OperatorInto.getSpecificOperator().fetchPrecedence();
		} else if (CalculatorConstants.OPERATORS.BY.asChar() == operator) {
			return OperatorBy.getSpecificOperator().fetchPrecedence();
		}
		return 0;
	}

	public static void main(String[] args) {
		Operator.getOperator('+');
	}

}
