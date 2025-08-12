package com.tests.project_name.utils;

import io.cucumber.java.Scenario;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.fr.Quand;

import java.time.Duration;

public class GetMyOTPHelper {

    private String otpCode; // Variable to store the OTP code

    // Method to retrieve OTP code
    public String retrieveOTPCode() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Step 1: Open GetMyMFA website
            driver.get("https://client.get.mymfa.io/mfa?phoneNumber=phone_01j517yraekzatvsj9k06jjf86");

            // Maximize window
            driver.manage().window().maximize();

            // Step 2: Log in to GetMyMFA (if required)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Locate username and password fields and login button
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Username or email address']")));
            WebElement passwordField = driver.findElement(By.xpath("//input[@placeholder='Password']"));
            WebElement loginButton = driver.findElement(By.xpath("//button[@class='ant-btn login-form-button login-form-margin ant-btn-primary']"));

            // Enter credentials and login //brawalid100@gmail.com
            usernameField.sendKeys("waelmarnissi123@hotmail.fr");
            passwordField.sendKeys("Test123456!");
            loginButton.click();

            // Wait for the page to load and locate the OTP button
            Thread.sleep(5000);
            WebElement accessLastOTPCode = driver.findElement(By.xpath("//span[contains(text(),'Access last MFA code')]"));
            accessLastOTPCode.click();

            // Step 3: Wait for OTP code to appear on the dashboard or in the specific section
            Thread.sleep(5000);
            WebElement otpCodeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/nz-layout/nz-content/div/nz-spin/div/app-last-mfa-code/div/nz-card/div[2]/nz-skeleton/div/h1")));
            otpCode = otpCodeElement.getText();

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }

        return otpCode; // Return the OTP code
    }

    @Quand("Je recupere mon code OTP")
    public void iGetMyCodeOTP() {
        // Call the retrieveOTPCode method
        String otpCode = retrieveOTPCode();
        if (otpCode != null) {
            System.out.println("Retrieved OTP Code: " + otpCode);
        } else {
            System.out.println("Failed to retrieve OTP Code.");
        }
    }

    // Getter for OTP code
    public String getOtpCode() {
        return otpCode;
    }
}
