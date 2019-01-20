package com.step2qa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Rahul R on 1/20/2019
 * @version 1.0.1
 */
public class FileUploadDemo {

    private WebDriver driver;
    private JavascriptExecutor js;

    @BeforeTest
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;

        driver.manage().window().maximize();

        driver.get("https://fineuploader.com/demos.html");

    }

    @Test
    public void file_upload() throws InterruptedException {

        String text;

        String filename = "test-file.txt";
        File file = new File(filename);
        String filepath = file.getAbsolutePath();

        WebElement btnFileUpload = driver.findElement(By.xpath("//button[contains(@id,'trigger-upload')]//..//input"));

        js.executeScript("arguments[0].scrollIntoView();", btnFileUpload);

        Thread.sleep(2000);

        btnFileUpload.sendKeys(filepath);

        text = driver.findElement(By.xpath("//span[contains(@class,'qq-upload-file')]")).getText();

        Thread.sleep(2000);

        Assert.assertEquals(text, filename, "Scenario failed, file is not loaded.");

    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}