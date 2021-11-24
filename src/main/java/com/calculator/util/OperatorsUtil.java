package com.calculator.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.calculator.CalculatorConstants;
import com.calculator.exceptions.CalculatorExceptions;

public class OperatorsUtil {
	private static Map<String, String> operatorsMap = null;
	private static Properties properties;

	public boolean isOperator(char ch) {
		return ch == '+' || ch == '-' || ch == '*' || ch == '/';
	}

	public int fetchPrecedence(char ch) {
		if (ch == '+' || ch == '-') {
			return 1;
		}
		if (ch == '*' || ch == '/') {
			return 2;
		}
		return 0;
	}

	public double performOperation(char t, double a, double b) throws Exception {
		double r = 0;
		if (t == '+') {
			r = a + b;
		} else if (t == '-') {
			r = a - b;
		} else if (t == '*') {
			r = a * b;
		} else if (t == '/') {
			r = a / b;
		} else {
			throw new Exception("Operator error");
		}
		return r;
	}

	public Map<String, String> getAllOperators() {
		if (operatorsMap != null) {
			return operatorsMap;
		} else {
			operatorsMap = new HashMap<String, String>();
			loadProperty();
			for (Entry<Object, Object> entry : properties.entrySet()) {
				operatorsMap.put((String) entry.getKey(),
						(String) entry.getValue());
			}
		}
		return operatorsMap;

	}
	public static void listf(String directoryName) {
	    File directory = new File(directoryName);

	    // Get all files from a directory.
	    File[] fList = directory.listFiles();
	    if(fList != null)
	        for (File file : fList) {      
	            if (file.isFile()) {
	                System.out.println(file.getAbsolutePath());
	            } else if (file.isDirectory()) {
	                listf(file.getAbsolutePath());
	            }
	        }
	}
	private static void loadProperty() {
		// propertyName is fileName
		try {
			properties = new Properties();
			System.out.println();
			FileInputStream fis = null;
			if (System.getenv("CONFIG_SUPER_CALCULATOR") != null) {
				if (new File(System.getenv("CONFIG_SUPER_CALCULATOR")).exists()) {
					fis = new FileInputStream(
							System.getenv("CONFIG_SUPER_CALCULATOR"));
				}
			} else {
				if (new File(System.getProperty("user.dir") + File.separator
						+ "src/main/resources" + File.separator
						+ CalculatorConstants.OPERATORS_FILENAME).exists()) {
					fis = new FileInputStream(System.getProperty("user.dir")
							+ File.separator + "src/main/resources"
							+ File.separator
							+ CalculatorConstants.OPERATORS_FILENAME);
				} else if (new File("ConfigOperators.properties").exists()){
					fis = new FileInputStream("ConfigOperators.properties");
				}
			}
			if (fis == null) {
				throw new CalculatorExceptions("ConfigOperators.properties file is not available");
			}
			properties.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
