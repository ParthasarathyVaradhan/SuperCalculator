package com.calculator.domain;

public class OperatorBy extends Operator {

	private static Operator byOperator;

	public static Operator getSpecificOperator() {
		if (byOperator == null) {
			byOperator = new OperatorBy();
		}
		return byOperator;
	}

	@Override
	public double performOperation(double a, double b) {
		// TODO Auto-generated method stub
		return a / b;
	}

	@Override
	public int fetchPrecedence() {
		// TODO Auto-generated method stub
		return 2;
	}

}
