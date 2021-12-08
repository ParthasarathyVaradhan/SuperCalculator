package com.calculator.domain;

public class OperatorMinus extends Operator {

	private static Operator minusOperator;

	public static Operator getSpecificOperator() {
		if (minusOperator == null) {
			minusOperator = new OperatorMinus();
		}
		return minusOperator;
	}

	@Override
	public double performOperation(double a, double b) {
		// TODO Auto-generated method stub
		return a - b;
	}

	@Override
	public int fetchPrecedence() {
		// TODO Auto-generated method stub
		return 1;
	}

}
