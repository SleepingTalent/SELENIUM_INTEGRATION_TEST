package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AddEmployeePage;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
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
    public void addEmployeeFormElements_displayedAsExpected() {
        Assert.assertEquals("First Name:",addEmployeePage.firstNameLabelDisplayed().getText());
        addEmployeePage.firstNameInputDisplayed();

        Assert.assertEquals("Last Name:",addEmployeePage.lastNameLabelDisplayed().getText());
        addEmployeePage.lastNameInputDisplayed();

        Assert.assertEquals("Date Of Birth:",addEmployeePage.dateOfBirthLabelDisplayed().getText());
        addEmployeePage.dateOfBirthInputDisplayed();

        Assert.assertEquals("House No:",addEmployeePage.houseNumberLabelDisplayed().getText());
        addEmployeePage.houseNumberInputDisplayed();

        Assert.assertEquals("Address Line 1:",addEmployeePage.addressFirstLineLabelDisplayed().getText());
        addEmployeePage.addressFirstLineInputDisplayed();

        Assert.assertEquals("Address Line 2:",addEmployeePage.addressSecondLineLabelDisplayed().getText());
        addEmployeePage.addressSecondLineInputDisplayed();

        Assert.assertEquals("Town/City:",addEmployeePage.townCityLabelDisplayed().getText());
        addEmployeePage.townCityInputDisplayed();

        Assert.assertEquals("Postcode:",addEmployeePage.postCodeLabelDisplayed().getText());
        addEmployeePage.postCodeInputDisplayed();
    }
}
