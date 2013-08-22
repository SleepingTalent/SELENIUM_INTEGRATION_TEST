package com.fs.domain.helper;

import com.fs.domain.common.exception.SeleniumTimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

public class ElementHelper {

    private static final long WAIT_TIME = 10;

    WebDriver driver;

    WebDriverWait webDriverWait;

    public ElementHelper(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver,WAIT_TIME);
    }

    public WebElement findElementById(String id, boolean reThrowError) throws SeleniumTimeoutException {
           return waitUntilCondition(ExpectedConditions.visibilityOfElementLocated(FindByHelper.findById(id)),reThrowError);
    }

    private WebElement waitUntilCondition(ExpectedCondition<WebElement> condition, boolean reThrowError) throws SeleniumTimeoutException {
        WebElement webElement = null;

        try {
            webElement = webDriverWait.until(condition);
        } catch (Exception ex) {
            if (reThrowError) {
                throw new SeleniumTimeoutException();
            } else {
                fail(ex.getMessage());
            }
        }

        return webElement;
    }
}
