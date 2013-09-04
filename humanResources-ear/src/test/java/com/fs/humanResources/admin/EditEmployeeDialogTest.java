package com.fs.humanResources.admin;

import com.fs.domain.page.admin.EditEmployeeDialog;
import com.fs.domain.page.admin.FindEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.address.helper.AddressHelper;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.util.Date;

public class EditEmployeeDialogTest extends BaseSeleniumTest {

    EditEmployeeDialog editEmployeeDialog;

    Employee employee;

    Address address;

    @Before
    public void setUp() {
        address = new Address();
        address.setHouseNumber("50");
        address.setAddressFirstLine("Test Driven Way");
        address.setAddressSecondLine("Domain Court");
        address.setTownCity("Progammer City");
        address.setPostCode("AB1 CDXY");
        address.setPrimaryAddress(true);

        AddressHelper addressHelper = new AddressHelper();

        employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName("Jones");
        employee.setDateOfBirth(new Date());
        employee.addAddress(address);

        persitenceHelper.beginTransaction();

        persitenceHelper.persistNewEmployee(employee);
        persitenceHelper.addDeletionCandidate(employee);
        persitenceHelper.commitTransaction();

        humanResourcesTool.openHomePage();
        humanResourcesTool.assertPageIsPresent();

        humanResourcesTool.assertEmployeeAdminMenuDisplayed().click();
        humanResourcesTool.assertAddEmployeeMenuItemDisplayed();

        FindEmployeeDialog findEmployeeDialog = humanResourcesTool.clickEditEmployeeMenuItem();
        findEmployeeDialog.assertDialogIsPresent();

        findEmployeeDialog.setEmployeeId(employee.getId() + "");
        editEmployeeDialog = findEmployeeDialog.clickFindEmployeeBtn();

        editEmployeeDialog.assertDialogIsPresent();
    }

    @After
    public void tearDown() {
        persitenceHelper.deleteCandidates();
        super.tearDown();
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
