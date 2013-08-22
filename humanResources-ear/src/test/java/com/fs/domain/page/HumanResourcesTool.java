package com.fs.domain.page;

import com.fs.domain.common.exception.SeleniumTimeoutException;
import com.fs.domain.common.page.PageObject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HumanResourcesTool extends PageObject {

    Logger log = Logger.getLogger(HumanResourcesTool.class);

    private static final String FORM = "adminTool";
    private static final String CONTENT_PANEL_ID = "contentPanel";

    public HumanResourcesTool(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        openHomePage("http://localhost:8181/humanResources/");
        assertHomePageDisplayed();
    }

    private void assertHomePageDisplayed() {
        Assert.assertEquals("Human Resources Home", getDriver().getTitle());
    }

    public void assertMainPanelPresent() {
        try {
            findElementById(FORM + ":" + CONTENT_PANEL_ID, true);
        } catch (SeleniumTimeoutException se) {
            log.info("Timed out trying to find element (" + FORM + ":" + CONTENT_PANEL_ID + ") retrying!");
            findElementById(FORM + ":" + CONTENT_PANEL_ID, false);
        }

    }
}
