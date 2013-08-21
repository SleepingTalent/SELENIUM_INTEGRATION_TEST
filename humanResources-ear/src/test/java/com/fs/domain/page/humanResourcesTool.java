package com.fs.domain.page;

import com.fs.domain.common.PageObject;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class HumanResourcesTool extends PageObject {

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
}
