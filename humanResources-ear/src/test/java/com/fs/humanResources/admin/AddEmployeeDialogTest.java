package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AddEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddEmployeeDialogTest extends BaseSeleniumTest {

    AddEmployeeDialog addEmployeeDialog;

    @Before
    public void setUp() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertPageIsPresent();

        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertAddEmployeeMenuItemDisplayed();

        addEmployeeDialog = humanResourcesTool.clickAddEmployeeMenuItem();

        addEmployeeDialog.assertDialogIsPresent();
    }

    @Test
    public void addEmployeeFormElements_displayedAsExpected() {
        Assert.assertEquals("First Name:", addEmployeeDialog.firstNameLabelDisplayed().getText());
        addEmployeeDialog.firstNameInputDisplayed();

        Assert.assertEquals("Last Name:", addEmployeeDialog.lastNameLabelDisplayed().getText());
        addEmployeeDialog.lastNameInputDisplayed();

        Assert.assertEquals("DOB (dd/mm/yyyy):", addEmployeeDialog.dateOfBirthLabelDisplayed().getText());
        addEmployeeDialog.dateOfBirthInputDisplayed();

        Assert.assertEquals("House No:", addEmployeeDialog.houseNumberLabelDisplayed().getText());
        addEmployeeDialog.houseNumberInputDisplayed();

        Assert.assertEquals("Address Line 1:", addEmployeeDialog.addressFirstLineLabelDisplayed().getText());
        addEmployeeDialog.addressFirstLineInputDisplayed();

        Assert.assertEquals("Address Line 2:", addEmployeeDialog.addressSecondLineLabelDisplayed().getText());
        addEmployeeDialog.addressSecondLineInputDisplayed();

        Assert.assertEquals("Town/City:", addEmployeeDialog.townCityLabelDisplayed().getText());
        addEmployeeDialog.townCityInputDisplayed();

        Assert.assertEquals("Postcode:", addEmployeeDialog.postCodeLabelDisplayed().getText());
        addEmployeeDialog.postCodeInputDisplayed();
    }

    @Test
    public void validationMessages_displayedAsExpected() {
        addEmployeeDialog.setFirstName("James");
        addEmployeeDialog.setLastName("Smith");
        addEmployeeDialog.setDateOfBirth("26122008");

        addEmployeeDialog.setHouseNumber("55");
        addEmployeeDialog.setAddressFirstLine("Maple Grove");
        addEmployeeDialog.setAddressSecondLine("Main Street");
        addEmployeeDialog.setTownCity("Meanwhile City");
        addEmployeeDialog.setPostcode("AB12CD");

        addEmployeeDialog.firstNameInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Firstname is required");

        addEmployeeDialog.setFirstName("James");

        addEmployeeDialog.lastNameInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Lastname is required");

        addEmployeeDialog.setLastName("Smith");

        addEmployeeDialog.houseNumberInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("House No is required");

        addEmployeeDialog.setHouseNumber("55");

        addEmployeeDialog.postCodeInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Postcode is required");

        addEmployeeDialog.setPostcode("AB12CD");

        addEmployeeDialog.dateOfBirthInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Date Of Birth is required");
    }
}
