package com.tests.project_name.hooks;


import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.tests.project_name.factory.DriverManagerFactory;
import com.tests.project_name.constants.DriverType;

import io.restassured.RestAssured;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Setup {

    private static WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(Setup.class);

    private static final String JDBC_URL = "jdbc:mysql://localhost:3309/easyges?serverVersion=5.7.24&charset=utf8mb4";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "app";

    /*
    //@Before(order = 0)
    public void cleanTestData() {
        String url = "jdbc:mysql://172.30.131.169:3309/easyges?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
        String username = "root";
        String password = "app";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(url, username, password);
                 Statement statement = connection.createStatement()) {

                String query = "UPDATE easyges.bv_payment_slip SET status = 'paid' WHERE reference = 'VOTRE_REFERENCE_ICI'";
                statement.executeUpdate(query);

                LOGGER.info("Test data successfully updated.");

            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while updating test data", e);
        }
    }




    @Before(order = 1)
    public void sendAutomationIdentifier() {
        RestAssured.baseURI = "https://fo-gessousl-d.caas-nonprod.intra.groupama.fr";
        RestAssured.requestSpecification = RestAssured.given()
                .relaxedHTTPSValidation()
                .header("X-Test-Bot", "Selenium")
                .log().headers();

        System.out.println("Header global ajouté. Aucune requête n'est envoyée pour l'instant.");
    }

     */

    @Before
    public void startDriver(Scenario scenario) throws IOException {
        LOGGER.info("Scenario: '{}' - started", scenario.getName());

        try {
            String browser = System.getProperty("browser", DriverType.CHROME.name()).toUpperCase();
            driver = DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver();
            LOGGER.info("WebDriver initialized successfully for browser: {}", browser);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid browser type specified: {}", System.getProperty("browser"), e);
            throw new RuntimeException("Failed to initialize WebDriver due to invalid browser type.", e);
        } catch (Exception e) {
            LOGGER.error("Unexpected error while initializing WebDriver.", e);
            throw new RuntimeException("WebDriver initialization failed.", e);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Ensure startDriver() is called before use.");
        }
        return driver;
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
