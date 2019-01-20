package com.step2qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Rahul R on 1/20/2019
 * @version 1.0.1
 */
public class FileDownloadDemo {

    private WebDriver driver;

    @BeforeTest
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.get("https://sample-videos.com/download-sample-text-file.php");

    }

    @Test
    public void file_download() {

        WebElement downloadButton = driver.findElement(By.xpath("//table/tbody/tr[1]/td[3]/a"));

        String sourceLocation = downloadButton.getAttribute("href");

        String run_wget = "cmd /c wget.exe -P " + System.getProperty("user.dir") + " --no-check-certificate " + sourceLocation;

        try {
            Process exec = Runtime.getRuntime().exec(run_wget);
            int exitVal = exec.waitFor();
            System.out.println("Exit value: " + exitVal);
        } catch (InterruptedException | IOException ex) {
            System.out.println(ex.toString());
        }

    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
