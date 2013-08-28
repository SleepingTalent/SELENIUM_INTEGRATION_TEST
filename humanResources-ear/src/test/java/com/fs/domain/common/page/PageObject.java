package com.fs.domain.common.page;

import com.fs.domain.common.exception.SeleniumTimeoutException;
import com.fs.domain.helper.ElementHelper;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;

import java.util.List;

import static org.junit.Assert.fail;

public abstract class PageObject {

    Logger log = Logger.getLogger(PageObject.class);

    private static String GROWL_MESSAGE_CLASS = "ui-growl-title";
    private static String GROWL_MESSAGE_CLOSE_CLASS = "ui-growl-icon-close";

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
        return findElementById(form + ":" + id);
    }

    protected WebElement findElementById(String id) {
        try {
            return elementHelper.findElementById(id, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element (" + id + ") retrying!");
            return elementHelper.findElementById(id, false);
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

    public void assertGrowlMessageDisplayed(String text) {
        findElementByClassWithText(GROWL_MESSAGE_CLASS, text);
    }

    public void closeGrowlMessage() {
        try {
            elementHelper.findByClass(GROWL_MESSAGE_CLASS, true).click();
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element with class (" + GROWL_MESSAGE_CLASS + ") retrying!");
            elementHelper.findByClass(GROWL_MESSAGE_CLASS, false).click();
        }
    }

    protected void setInputTextOnElement(WebElement element, String text) {
        Keyboard keyboard = ((HasInputDevices) getDriver()).getKeyboard();
        element.click();
        element.clear();
        keyboard.sendKeys(text);
    }


}
