package com.calculator;

import java.io.File;

public class CalculatorConstants {
	public static final String OPEN_BRACKETS = "(";
	public static final String CLOSED_BRACKETS = ")";
	public static final String OPERATORS_FILENAME = "com" + File.separator
			+ "calculator" + File.separator + "util" + File.separator + "ConfigOperators.properties";
	
	public static enum OPERATORS {
		PLUS('+'),
	    MINUS('-'),
	    BY('/'),
	    INTO('*');

	    public char asChar() {
	        return asChar;
	    }

	    private final char asChar;

	    OPERATORS(char asChar) {
	        this.asChar = asChar;
	    }
	}
	
	public static enum BRACKETS {
		OPEN('{'),
	    CLOSE('}');

	    public char asChar() {
	        return asChar;
	    }

	    private final char asChar;

	    BRACKETS(char asChar) {
	        this.asChar = asChar;
	    }
	}
	
	
	
}
