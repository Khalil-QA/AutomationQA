package com.tests.project_name.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    /**
     * Captures a screenshot of a specific WebElement and saves it to the specified path.
     *
     * @param driver         The WebDriver instance.
     * @param elementLocator The locator of the WebElement to capture.
     * @param saveDirectoryPath       The path where the screenshot will be saved.
     * @throws IOException If there is an error during the file operation.
     */
    public static void captureElementScreenshot(WebDriver driver, By elementLocator, String saveDirectoryPath) throws IOException {
        // Vérifier si le chemin est un dossier
        File saveDirectory = new File(saveDirectoryPath);
        if (!saveDirectory.exists()) {
            if (!saveDirectory.mkdirs()) {
                throw new IOException("Impossible de créer le répertoire de destination : " + saveDirectoryPath);
            }
        } else if (!saveDirectory.isDirectory()) {
            throw new IOException("Le chemin spécifié n'est pas un dossier : " + saveDirectoryPath);
        }

        // Générer un nom de fichier unique
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "screenshot_" + timestamp + ".png";
        File imageFile = new File(saveDirectory, fileName);

        // Localiser l'élément Web
        WebElement element = driver.findElement(elementLocator);

        // Capturer la capture d'écran de l'élément
        Screenshot screenshot = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, element);

        // Sauvegarder l'image
        ImageIO.write(screenshot.getImage(), "png", imageFile);

        // Vérification de l'enregistrement
        if (imageFile.exists()) {
            System.out.println("Capture d'écran enregistrée : " + imageFile.getAbsolutePath());
        } else {
            System.out.println("Échec de l'enregistrement de la capture d'écran.");
        }
    }
}

