package com.fs.humanResources.admin;

import com.fs.domain.page.admin.EditEmployeeDialog;
import com.fs.domain.page.admin.FindEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EditEmployeeDialogTest extends BaseSeleniumTest {

    EditEmployeeDialog editEmployeeDialog;

    @Before
    public void setUp() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertPageIsPresent();

        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertAddEmployeeMenuItemDisplayed();

        FindEmployeeDialog findEmployeeDialog = humanResourcesTool.clickEditEmployeeMenuItem();
        findEmployeeDialog.assertDialogIsPresent();

        findEmployeeDialog.setEmployeeId("12345");
        editEmployeeDialog = findEmployeeDialog.clickFindEmployeeBtn();

        editEmployeeDialog.assertDialogIsPresent();
    }

    @Test
    public void editEmployeeFormElements_displayedAsExpected() {
        Assert.assertEquals("First Name:", editEmployeeDialog.firstNameLabelDisplayed().getText());
        editEmployeeDialog.firstNameInputDisplayed();

        Assert.assertEquals("Last Name:", editEmployeeDialog.lastNameLabelDisplayed().getText());
        editEmployeeDialog.lastNameInputDisplayed();

        Assert.assertEquals("DOB (dd/mm/yyyy):", editEmployeeDialog.dateOfBirthLabelDisplayed().getText());
        editEmployeeDialog.dateOfBirthInputDisplayed();

        Assert.assertEquals("House No:", editEmployeeDialog.houseNumberLabelDisplayed().getText());
        editEmployeeDialog.houseNumberInputDisplayed();

        Assert.assertEquals("Address Line 1:", editEmployeeDialog.addressFirstLineLabelDisplayed().getText());
        editEmployeeDialog.addressFirstLineInputDisplayed();

        Assert.assertEquals("Address Line 2:", editEmployeeDialog.addressSecondLineLabelDisplayed().getText());
        editEmployeeDialog.addressSecondLineInputDisplayed();

        Assert.assertEquals("Town/City:", editEmployeeDialog.townCityLabelDisplayed().getText());
        editEmployeeDialog.townCityInputDisplayed();

        Assert.assertEquals("Postcode:", editEmployeeDialog.postCodeLabelDisplayed().getText());
        editEmployeeDialog.postCodeInputDisplayed();
    }

    @Test
    public void validationMessages_displayedAsExpected() {
        editEmployeeDialog.setFirstName("James");
        editEmployeeDialog.setLastName("Smith");
        editEmployeeDialog.setDateOfBirth("26122008");

        editEmployeeDialog.setHouseNumber("55");
        editEmployeeDialog.setAddressFirstLine("Maple Grove");
        editEmployeeDialog.setAddressSecondLine("Main Street");
        editEmployeeDialog.setTownCity("Meanwhile City");
        editEmployeeDialog.setPostcode("AB12CD");

        editEmployeeDialog.firstNameInputDisplayed().clear();
        editEmployeeDialog.clickAddEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Firstname is required");

        editEmployeeDialog.setFirstName("James");

        editEmployeeDialog.lastNameInputDisplayed().clear();
        editEmployeeDialog.clickAddEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Lastname is required");

        editEmployeeDialog.setLastName("Smith");

        editEmployeeDialog.houseNumberInputDisplayed().clear();
        editEmployeeDialog.clickAddEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("House No is required");

        editEmployeeDialog.setHouseNumber("55");

        editEmployeeDialog.postCodeInputDisplayed().clear();
        editEmployeeDialog.clickAddEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Postcode is required");

        editEmployeeDialog.setPostcode("AB12CD");

        editEmployeeDialog.dateOfBirthInputDisplayed().clear();
        editEmployeeDialog.clickAddEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Date Of Birth is required");
    }
}
