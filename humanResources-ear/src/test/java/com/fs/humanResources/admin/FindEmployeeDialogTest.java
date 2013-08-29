package com.fs.humanResources.admin;

import com.fs.domain.page.admin.FindEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FindEmployeeDialogTest extends BaseSeleniumTest {

    FindEmployeeDialog findEmployeeDialog;

    @Before
    public void setUp() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertPageIsPresent();

        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertEditEmployeeMenuItemDisplayed();

        findEmployeeDialog = humanResourcesTool.clickEditEmployeeMenuItem();
        findEmployeeDialog.assertDialogIsPresent();
    }

    @Test
    public void findEmployeeFormElements_displayedAsExpected() {
       // Assert.assertEquals("Staff Number:", findEmployeeDialog.staffNumberLabelDisplayed().getText());
       // findEmployeeDialog.staffNumberInputDisplayed();
    }

    @Test
    public void validationMessages_displayedAsExpected() {
       // findEmployeeDialog.setStaffNumber("12345");

      //  findEmployeeDialog.staffNumberInputDisplayed().clear();
      //  findEmployeeDialog.assertGrowlMessageDisplayed("Staff Number is required");
      //  findEmployeeDialog.closeGrowlMessage();
    }
}
