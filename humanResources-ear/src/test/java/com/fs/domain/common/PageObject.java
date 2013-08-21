package com.fs.domain.common;

import org.openqa.selenium.WebDriver;

public abstract class PageObject {

    WebDriver driver;

    protected PageObject(WebDriver driver) {
        this.driver = driver;
    }

    protected void openHomePage(String url) {
       driver.get(url);
    }

    protected WebDriver getDriver() {
        return driver;
    }
}
