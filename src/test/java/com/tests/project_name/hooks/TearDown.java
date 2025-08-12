package com.tests.project_name.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TearDown {

	private static final Logger LOGGER = Setup.getLogger();  // Reuse the logger from Setup class

	/**
	 * This method is used to close the browser and clean up after each scenario.
	 * It is invoked after each Cucumber scenario. If the scenario fails, a screenshot is taken.
	 *
	 * @param scenario The scenario object, containing information about the current test.
	 */
	@After
	public void quitDriver(Scenario scenario) {

		try {
			// If the scenario failed, capture a screenshot
			if (scenario.isFailed()) {
				final byte[] screenShot = ((TakesScreenshot) Setup.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenShot, "image/png", "Screenshot: " + scenario.getName());
				LOGGER.error("Scenario failed: {}. Screenshot attached.", scenario.getName());

				// Capture the exception (Throwable) if available
				Status error = scenario.getStatus();
				String errorDetails = "Error: " + (error != null ? error.name() : "Unknown error");
			}

			// Quit the WebDriver after the scenario is complete
			//Setup.getDriver().quit();
			LOGGER.info("Scenario '{}' finished with status: {}", scenario.getName(), scenario.getStatus());
		} catch (Exception e) {
			LOGGER.error("Error during tear down of scenario: {}", scenario.getName(), e);
			throw new RuntimeException("Error during tear down of the scenario.", e);
		}
	}
}

