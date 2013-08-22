package com.fs.domain.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementHelper {

    WebDriver driver;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElementById(String id) {
        driver.findElement(FindByHelper.findById(id));
    }
}
