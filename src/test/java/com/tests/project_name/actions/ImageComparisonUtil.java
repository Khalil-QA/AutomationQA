package com.tests.project_name.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.tests.project_name.hooks.Setup;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageComparisonUtil {

    /**
     * Compares an expected image with a screenshot of a WebElement.
     *
     * @param driver             The WebDriver instance.
     * @param elementLocator     The locator of the WebElement to capture.
     * @param expectedImagePath  Path to the expected image file.
     * @return True if images are identical, false otherwise.
     * @throws IOException If there is an error during image reading or writing.
     */
    public static boolean compareElementImage(WebDriver driver, By elementLocator, String expectedImagePath) throws IOException {
        // Load the expected image
        BufferedImage expectedImage = ImageIO.read(new File(expectedImagePath));

        // Locate the WebElement
        WebElement element = driver.findElement(elementLocator);

        // Capture the WebElement's screenshot
        Screenshot actualScreenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(Setup.getDriver(), element);
        BufferedImage actualImage = actualScreenshot.getImage();

        // Compare the images using ImageDiffer
        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff diff = imgDiffer.makeDiff(expectedImage, actualImage);

        // Check for differences and return the result
        if (diff.hasDiff()) {
            System.out.println("Images are NOT the same.");
            return false;
        } else {
            System.out.println("Images are the same.");
            return true;
        }
    }
}

