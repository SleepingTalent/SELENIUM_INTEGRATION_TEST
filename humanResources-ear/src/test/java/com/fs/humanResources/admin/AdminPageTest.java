package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AdminPage;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AdminPageTest extends BaseSeleniumTest {

    AdminPage adminPage;

    @Before
    public void setUp() {
        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
    }

    @Test
    public void logoutLink_logsOutAsExpected() {
        adminPage.clickLogoutLink();
        humanResourcesHome.assertPageIsPresent();
    }

    @Test
    public void menusDisplayed_asExpected() {
        adminPage.assertEmployeeAdminMenuDisplayed().click();
        adminPage.assertEditEmployeeMenuItemDisplayed();
        adminPage.assertAddEmployeeMenuItemDisplayed();
        adminPage.assertDeleteEmployeeMenuItemDisplayed();

        adminPage.assertBrowseMenuDisplayed().click();
        adminPage.assertBrowseEmployeeMenuItemDisplayed();

        adminPage.assertSearchMenuDisplayed().click();
        adminPage.assertSearchForEmployeeMenuItemDisplayed();
    }

    @Test
    public void latestNewsDisplayed_asExpected() {
       String actualText = adminPage.assertLatestNewsPanelDisplayed().getText();
        Assert.assertEquals("Latest News\nWelcome to the Human Resources Admin Tool.",actualText);
    }
}
