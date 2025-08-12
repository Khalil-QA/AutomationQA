package com.tests.project_name.factory;

import com.tests.project_name.constants.DriverType;

public class DriverManagerFactory {

	public static DriverManager getManager(DriverType driverType) {
		switch (driverType) {
		case CHROME:
			return new ChromeDriverManager();
		case FIREFOX:
			return new FirefoxDriverManager();
		case EDGE:
			return new EdgeDriverManager();
		default:
			throw new IllegalStateException("Invalid driver initialization: " + driverType);
		}
	}
}
