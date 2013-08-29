package com.fs.humanResources.admin;

import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdminPageTest extends BaseSeleniumTest {

    @Before
    public void setUp() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertPageIsPresent();
    }

    @Test
    public void menusDisplayed_asExpected() {
        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertEditEmployeeMenuItemDisplayed();
        humanResourcesTool.assertAddEmployeeMenuItemDisplayed();
        humanResourcesTool.assertDeleteEmployeeMenuItemDisplayed();

        humanResourcesTool.assertBrowseMenuDisplayed().click();
        humanResourcesTool.assertBrowseEmployeeMenuItemDisplayed();

        humanResourcesTool.assertSearchMenuDisplayed().click();
        humanResourcesTool.assertSearchForEmployeeMenuItemDisplayed();
    }

    @Test
    public void latestNewsDisplayed_asExpected() {
       String actualText = humanResourcesTool.assertLatestNewsPanelDisplayed().getText();
        Assert.assertEquals("Latest News\nWelcome to the Human Resources Admin Tool.",actualText);
    }
}
