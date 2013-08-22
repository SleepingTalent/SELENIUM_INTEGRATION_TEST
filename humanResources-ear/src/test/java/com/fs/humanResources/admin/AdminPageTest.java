package com.fs.humanResources.admin;

import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Before;
import org.junit.Test;

public class AdminPageTest extends BaseSeleniumTest {

    @Before
    public void setUp() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertMainPanelPresent();
    }

    @Test
    public void employeeAdmin_menuDisplayed_asExpected() {
        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertEditEmployeeMenuItemDisplayed();
        humanResourcesTool.assertAddEmployeeMenuItemDisplayed();
        humanResourcesTool.assertDeleteEmployeeMenuItemDisplayed();

        humanResourcesTool.assertBrowseMenuDisplayed().click();

        humanResourcesTool.assertSearchMenuDisplayed().click();
    }
}
