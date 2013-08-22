package com.fs.domain.common.page;

import com.fs.domain.common.exception.SeleniumTimeoutException;
import com.fs.domain.helper.ElementHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.fail;

public abstract class PageObject {

    Logger log = Logger.getLogger(PageObject.class);

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

    protected WebElement findFormElementById(String form, String id) {
        try {
            return elementHelper.findElementById(form + ":" + id, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element (" + form + ":" + id + ") retrying!");
            return elementHelper.findElementById(form + ":" + id, false);
        }
    }

    protected WebElement findElementByClassWithText(String className, String text) {
        try {
            return elementHelper.findElementsByClassContainingText(className, text, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element with class (" + className + ") containing text (" + text + ") retrying!");
            return elementHelper.findElementsByClassContainingText(className, text, true);
        }
    }
}
