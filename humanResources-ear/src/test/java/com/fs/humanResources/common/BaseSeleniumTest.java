package com.fs.humanResources.common;

import com.fs.domain.page.HumanResourcesTool;
import org.apache.log4j.Logger;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public abstract class BaseSeleniumTest {

    private Logger log = Logger.getLogger(BaseSeleniumTest.class);

    private WebDriver driver;

    protected HumanResourcesTool humanResourcesTool;

    protected BaseSeleniumTest() {
        driver = initialiseDriver();
        humanResourcesTool = new HumanResourcesTool(driver);
    }

    @After
    public void tearDown() {
       driver.close();
    }

    private WebDriver initialiseDriver() {
        if (isDriverRun(System.getProperty("selenium.driver.type"), "htmlUnit")) {
            log.info("Running with HtmlUnitDriver");
            return new HtmlUnitDriver();
        } else {
            log.info("Running with FirefoxDriver");
            return new FirefoxDriver();
        }
    }

    protected WebDriver getDriver() {
        return driver;
    }

    private boolean isDriverRun(String sysPropsDriverType, String expectedDriverType) {
        log.info("System Property Driver Type : "+sysPropsDriverType);
        return (sysPropsDriverType != null && !sysPropsDriverType.isEmpty() && sysPropsDriverType.equals(expectedDriverType));
    }

}
