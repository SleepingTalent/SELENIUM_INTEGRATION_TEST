package com.fs.domain.common.page;

import com.fs.domain.helper.ElementHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class PageObject {

    private WebDriver driver;

    private ElementHelper elementHelper;

    protected PageObject(WebDriver driver) {
        this.driver = driver;
        this.elementHelper = new ElementHelper(driver);
    }

    protected void openHomePage(String url) {
        driver.get(url);
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected WebElement findElementById(String id, boolean reThrowError) {
       return elementHelper.findElementById(id,reThrowError);
    }
}
