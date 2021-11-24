package com.calculator;

import org.junit.Test;


public class CalculatorTest {

	@Test
	public void operatorStringTest() throws Exception {
		SuperCalculator calc = new SuperCalculator();
		String test = "1 plus 3 minus 3";
		calc.processInput(test);
	}
	
	@Test
	public void complexExprStringTest() throws Exception {
		SuperCalculator calc = new SuperCalculator();
		String test = "1 plus 3 into 5 minus 25 by 8 plus 76";
		calc.processInput(test);
	}
	
	@Test
	public void bracketExprTest() throws Exception {
		SuperCalculator calc = new SuperCalculator();
		String test = "1 + 3 * ( 254 - 55 - 91 ) / 8 + ( 76 - 65 + 62 ) / 2 + 2 * ( 3 / 9 * 7 )";
		calc.processInput(test);
	}
}
	
