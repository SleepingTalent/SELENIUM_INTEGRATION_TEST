package com.fs.humanResources.admin.employee;

import com.fs.domain.page.admin.AdminPage;
import com.fs.domain.page.admin.dialog.FindEmployeeDialogForEdit;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FindEmployeeDialogTest extends BaseSeleniumTest {

    AdminPage adminPage;

    FindEmployeeDialogForEdit findEmployeeDialogForEdit;

    @Before
    public void setUp() {
        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
        adminPage.assertPageIsPresent();

        adminPage.assertEmployeeAdminMenuDisplayed().click();

        adminPage.assertEditEmployeeMenuItemDisplayed();
        findEmployeeDialogForEdit = adminPage.clickEditEmployeeMenuItem();

        findEmployeeDialogForEdit.assertDialogIsPresent();
    }

    @Test
    public void findEmployeeFormElements_displayedAsExpected() {
        Assert.assertEquals("Employee Id:", findEmployeeDialogForEdit.employeeIdLabelDisplayed().getText());
        findEmployeeDialogForEdit.employeeInputDisplayed();
    }

    @Test
    public void validationMessages_displayedAsExpected() {
        findEmployeeDialogForEdit.clickFindEmployeeBtn();
        findEmployeeDialogForEdit.assertGrowlMessageDisplayed("Employee Id is required");
    }

    @Test
    public void validationMessages_displayedAsExpected_whenIdNotFound() {
        String unknownId = "68785959";

        findEmployeeDialogForEdit.setEmployeeId(unknownId);
        findEmployeeDialogForEdit.clickFindEmployeeBtn();
        findEmployeeDialogForEdit.assertGrowlMessageDisplayed("Employee Id ("+unknownId+") not found!");
    }
}
