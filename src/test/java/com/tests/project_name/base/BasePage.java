package com.tests.project_name.base;

import java.time.Duration;
import java.util.List;

import com.tests.project_name.hooks.Setup;
import com.tests.project_name.utils.ConfigLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains the declaration of the driver. and contains the
 * declaration of the logger.
 */
public class BasePage {

	protected WebDriver driver;
	protected static WebDriverWait wait;

	/**
	 * Instantiation base page.
	 * 
	 * @param driver
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	  public void load(String endPoint) {
	        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
	   }

	/**
	 * methode Wait for element to be clickable
	 *
	 * @param locator
	 */
	public static void waitForElementToBeClickable(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(Setup.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * methode Wait for element to be clickable
	 *
	 * @param locator
	 */
	public static void waitForElementToBeVisible(WebElement locator) {
		WebDriverWait wait = new WebDriverWait(Setup.getDriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public void waitForOverlaysToDisappear(By overlay) {
		List<WebElement> overlays = Setup.getDriver().findElements(overlay);
		if (overlays.size() > 0) {
			wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
		}
	}

	public boolean find(final WebElement element, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			return wait.until(new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					if (element.isDisplayed()) {
						return true;
					}
					return false;
				}
			});
		} catch (Exception e) {
			return false;
		}
	}
}
