package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AdminPage;
import com.fs.domain.page.admin.dialog.FindEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FindEmployeeDialogTest extends BaseSeleniumTest {

    AdminPage adminPage;

    FindEmployeeDialog findEmployeeDialog;

    @Before
    public void setUp() {
        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
        adminPage.assertPageIsPresent();

        adminPage.assertEmployeeAdminMenuDisplayed().click();

        adminPage.assertEditEmployeeMenuItemDisplayed();
        findEmployeeDialog = adminPage.clickEditEmployeeMenuItem();

        findEmployeeDialog.assertDialogIsPresent();
    }

    @Test
    public void findEmployeeFormElements_displayedAsExpected() {
        Assert.assertEquals("Employee Id:", findEmployeeDialog.employeeIdLabelDisplayed().getText());
        findEmployeeDialog.employeeInputDisplayed();
    }

    @Test
    public void validationMessages_displayedAsExpected() {
        findEmployeeDialog.clickFindEmployeeBtn();
        findEmployeeDialog.assertGrowlMessageDisplayed("Employee Id is required");
    }
}
