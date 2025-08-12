package com.tests.project_name.factory;

import com.tests.project_name.utils.ConfigFileReader;
import com.tests.project_name.utils.ProxyUtils;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v133.network.Network;
import org.openqa.selenium.devtools.v133.network.model.Headers;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

public class ChromeDriverManager implements DriverManager {

    private static final Logger LOGGER = Logger.getLogger(ChromeDriverManager.class.getName());
    private final ConfigFileReader configFileReader;

    public ChromeDriverManager() {
        this.configFileReader = new ConfigFileReader();
    }

    @Override
    public WebDriver createDriver() {
        try {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--start-maximized");
            chromeOptions.addArguments("--disable-popup-blocking");
            chromeOptions.addArguments("--disable-notifications");
            chromeOptions.addArguments("chrome.switches", "--disable-extensions");

            //configureProxy(chromeOptions);
            ChromeDriver driver = new ChromeDriver(chromeOptions);

            // Activation de DevTools et ajout du header custom
            //configureDevTools(driver);

            LOGGER.info("ChromeDriver initialized with options: " + chromeOptions.toString());
            return driver;
        } catch (Exception e) {
            LOGGER.info("Failed to create ChromeDriver: " + e.getMessage());
            throw new RuntimeException("Error initializing ChromeDriver", e);
        }
    }

    /*
    private void configureProxy(ChromeOptions chromeOptions) {
        String proxyUrl = configFileReader.getProperty("url.proxy");
        String proxyPort = configFileReader.getProperty("port.proxy");

        if (proxyUrl == null || proxyPort == null) {
            LOGGER.warning("Proxy configuration is missing or incomplete. Proceeding without proxy.");
            return;
        }

        try {
            String httpProxy = proxyUrl + ":" + proxyPort;
            String sslProxy = proxyUrl + ":" + proxyPort;

            Proxy proxy = ProxyUtils.getHttpAndSslProxy(httpProxy, sslProxy);
            chromeOptions.setCapability("proxy", proxy);
            LOGGER.info("Proxy configured: HTTP = " + httpProxy + ", SSL = " + sslProxy);
        } catch (IllegalArgumentException e) {
            LOGGER.severe("Invalid proxy configuration: " + e.getMessage());
            throw e;
        }
    }

     */

    /*
    public static void configureDevTools(ChromeDriver driver) {
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        // Activation du monitoring réseau
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Ajout du header custom
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("X-Test-Bot", "Selenium");
        Headers headers = new Headers(headersMap);

        devTools.send(Network.setExtraHTTPHeaders(headers));
        LOGGER.info("Custom header 'X-Test-Bot: Selenium' ajouté via DevTools.");
    }

     */
}

