package com.calculator.domain;

public class OperatorPlus extends Operator {

	private static Operator plusOperator;

	public static Operator getSpecificOperator() {
		if (plusOperator == null) {
			plusOperator = new OperatorPlus();
		}
		return plusOperator;
	}

	@Override
	public double performOperation(double a, double b) {
		// TODO Auto-generated method stub
		return a + b;
	}

	@Override
	public int fetchPrecedence() {
		// TODO Auto-generated method stub
		return 1;
	}

}
