package com.calculator.domain;

public class OperatorInto extends Operator {

	private static Operator intoOperator;

	public static Operator getSpecificOperator() {
		if (intoOperator == null) {
			intoOperator = new OperatorInto();
		}
		return intoOperator;
	}

	@Override
	public double performOperation(double a, double b) {
		// TODO Auto-generated method stub
		return a * b;
	}

	@Override
	public int fetchPrecedence() {
		// TODO Auto-generated method stub
		return 2;
	}

}
