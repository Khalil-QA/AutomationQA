package com.tests.project_name.step_definitions;

import com.tests.project_name.actions.SeleniumUtils;
import com.tests.project_name.page_objects.LoginPage;
import com.tests.project_name.utils.ConfigFileReader;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;
import org.junit.Assert;

public class LoginStepDefinition {

    private final SeleniumUtils seleniumUtils;
	private final ConfigFileReader configFileReader;

	public LoginStepDefinition() {
        LoginPage loginPage = new LoginPage();
		seleniumUtils = new SeleniumUtils();
		configFileReader = new ConfigFileReader();
	}

	/* TC_01 */
	@Etantdonnéque("Je suis sur la page de connexion de l'application EasyGes")
	public void iAmOnTheLoginPage() throws InterruptedException {
		seleniumUtils.get(configFileReader.getProperty("productUrl"));
		Thread.sleep(5000);
	}
	@Quand("Je saisis une adresse mail {string}")
	public void iFillAUserName(String email) {
		seleniumUtils.writeText(LoginPage.getUsername(), email);
	}

	@Quand("Je saisis un mot de passe {string}")
	public void iFillAPassword(String password) {
		seleniumUtils.writeText(LoginPage.getPasswordField(), password);
	}

	@Quand("Je clique sur le bouton Log in")
	public void iClickOnTheLoginButton() {
		seleniumUtils.click(LoginPage.getBtnLogin());
	}

	@Alors("Je suis redirigé vers la page d'accueil avec l'adresse {string}")
	public void iAmOnTheHomePage(String address) throws InterruptedException {
		Assert.assertTrue("User Address Found", new LoginPage().getUserNameAddress());
		Thread.sleep(5000);
	}
}
