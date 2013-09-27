package com.fs.domain.common.page;

import com.fs.domain.common.exception.SeleniumTimeoutException;
import com.fs.domain.helper.ElementHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Keyboard;

import java.util.List;

import static org.junit.Assert.fail;

public abstract class PageObject {

    Logger log = Logger.getLogger(PageObject.class);

    private static String GROWL_MESSAGE_CLASS = "ui-growl-title";

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
        return findElementById(id, false);
    }

    protected WebElement findElementById(String id, boolean reThrow) {
        try {
            return elementHelper.findElementById(id, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element (" + id + ") retrying!");
            return elementHelper.findElementById(id, reThrow);
        }
    }

    protected WebElement findElementByIdAndClass(String id, String cssClass) {
        try {
            return elementHelper.findByIdAndClass(id, cssClass, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element (" + id + ") retrying!");
            return elementHelper.findByIdAndClass(id, cssClass, false);
        }
    }

    protected WebElement findElementByIdAndAttribute(String id, String attributeName, String attributeValue) {
        try {
            return elementHelper.findByIdAndAttribute(id, attributeName, attributeValue, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element (" + id + ") with Attr (@" + attributeName + "=\"" + attributeValue + "\") retrying!");
            return elementHelper.findByIdAndAttribute(id, attributeName, attributeValue, false);
        }
    }

    protected WebElement findTableDataWithText(String text) {
        try {
            return elementHelper.findTableDataWithText(text, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find td with (" + text + ") retrying!");
            return elementHelper.findTableDataWithText(text, false);
        }
    }

    protected WebElement findTableRowWithText(String text) {
        try {
            return elementHelper.findTableRowWithText(text, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find tr with (" + text + ") retrying!");
            return elementHelper.findTableRowWithText(text, false);
        }
    }

    protected WebElement findElementByClassWithText(String className, String text) {
        try {
            return elementHelper.findElementsByClassContainingText(className, text, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element with class (" + className + ") containing text (" + text + ") retrying!");
            return elementHelper.findElementsByClassContainingText(className, text, false);
        }
    }

    protected WebElement findElementByClass(String className) {
        try {
            return elementHelper.findByClass(className, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element with class (" + className + ") retrying!");
            return elementHelper.findByClass(className, false);
        }
    }

    protected WebElement findLinkByText(String text) {
        try {
            return elementHelper.findByLinkText(text, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find link with text (" + text + ") retrying!");
            return elementHelper.findByLinkText(text, false);
        }
    }

    public void assertGrowlMessageDisplayed(String text) {
        findElementByClassWithText(GROWL_MESSAGE_CLASS, text);
    }

    protected void moveToElement(WebElement element) {
        new Actions(getDriver()).moveToElement(element).perform();
    }

    protected void rightClickOnElement(WebElement element) {
        new Actions(getDriver()).contextClick(element).perform();
    }

    protected void setInputTextOnElement(WebElement element, String text) {
        Keyboard keyboard = ((HasInputDevices) getDriver()).getKeyboard();
        element.click();
        element.clear();
        keyboard.sendKeys(text);
    }


}
