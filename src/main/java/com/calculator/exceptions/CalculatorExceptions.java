package com.calculator.exceptions;

@SuppressWarnings("serial")
public class CalculatorExceptions extends RuntimeException {
	
	private String exceptionMessage;

	public CalculatorExceptions(String exceptionMessage) {
		super(exceptionMessage);
		this.setExceptionMessage(exceptionMessage);
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
