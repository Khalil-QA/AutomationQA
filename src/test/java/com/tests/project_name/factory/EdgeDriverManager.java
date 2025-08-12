package com.tests.project_name.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager implements DriverManager {

	@Override
	public WebDriver createDriver() {
		WebDriver driver = new EdgeDriver();
	    EdgeOptions options = new EdgeOptions();
	    options.addArguments("--start-maximized");
	    options.addArguments("force-device-scale-factor=0.75");
	    driver = new EdgeDriver(options);
//		WebDriver driver = new EdgeDriver();
//		driver.manage().window().maximize();
		return driver;
	}
}