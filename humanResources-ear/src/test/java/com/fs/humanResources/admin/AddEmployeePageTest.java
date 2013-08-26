package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AddEmployeePage;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Before;
import org.junit.Test;

public class AddEmployeePageTest extends BaseSeleniumTest {

    AddEmployeePage addEmployeePage;

    @Before
    public void setUp() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertPageIsPresent();

        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertAddEmployeeMenuItemDisplayed();

        addEmployeePage = humanResourcesTool.clickAddEmployeeMenuItem();

        addEmployeePage.assertPageIsPresent();
    }

    @Test
    public void defaultTest() {
        //Test..
    }
}
