package com.tests.project_name.page_objects;

import com.tests.project_name.base.BasePage;
import com.tests.project_name.hooks.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {

	/* Retrieve Web Element */
	@FindBy(how = How.ID, using = "username")
	private static WebElement username;

	@FindBy(how = How.ID, using = "password")
	private static WebElement passwordField;

	@FindBy(how = How.XPATH, using = "//button[normalize-space()='Log in']")
	private static WebElement btnLogin;

	@FindBy(how = How.XPATH, using = "//a[@type='button']//span[@class='user-name'][normalize-space()='zied.hannachi.ext@groupama.com']")
	private static WebElement toolbarItemUser;

	public LoginPage() {
		super(Setup.getDriver());
	}

	public static WebElement getUsername() {
		waitForElementToBeVisible(username);
		return username;
	}

	public static WebElement getPasswordField() {
		waitForElementToBeVisible(passwordField);
		return passwordField;
	}

	public static WebElement getBtnLogin() {
		waitForElementToBeClickable(btnLogin);
		return btnLogin;
	}

	public boolean getUserNameAddress() {
		return find(toolbarItemUser, 1000);
	}

}
