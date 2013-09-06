package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AdminPage;
import com.fs.domain.page.admin.dialog.EditEmployeeDialog;
import com.fs.domain.page.admin.dialog.FindEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.address.helper.AddressHelper;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditEmployeeDialogTest extends BaseSeleniumTest {

    public static final String DATE_FORMATE_STR = "dd/MM/yyyy";
    AdminPage adminPage;

    EditEmployeeDialog editEmployeeDialog;

    Employee employee;

    Address address;

    @Before
    public void setUp() throws ParseException {
        address = new Address();
        address.setHouseNumber("50");
        address.setAddressFirstLine(persitenceHelper.getUniqueString(8));
        address.setAddressSecondLine("Domain Court");
        address.setTownCity("Progammer City");
        address.setPostCode("AB1CDX");
        address.setPrimaryAddress(true);

        employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName(persitenceHelper.getUniqueString(8));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMATE_STR);
        employee.setDateOfBirth(simpleDateFormat.parse("15/07/1976"));

        employee.addAddress(address);

        persitenceHelper.beginTransaction();

        persitenceHelper.persistNewEmployee(employee);
        persitenceHelper.addDeletionCandidate(employee);
        persitenceHelper.commitTransaction();

        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
        adminPage.assertPageIsPresent();

        adminPage.assertEmployeeAdminMenuDisplayed().click();

        FindEmployeeDialog findEmployeeDialog = adminPage.clickEditEmployeeMenuItem();
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
        editEmployeeDialog.firstNameInputDisplayed().clear();
        editEmployeeDialog.clickEditEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Firstname is required");

        editEmployeeDialog.setFirstName(employee.getFirstName());

        editEmployeeDialog.lastNameInputDisplayed().clear();
        editEmployeeDialog.clickEditEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Lastname is required");

        editEmployeeDialog.setLastName(employee.getLastName());

        editEmployeeDialog.houseNumberInputDisplayed().clear();
        editEmployeeDialog.clickEditEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("House No is required");

        editEmployeeDialog.setHouseNumber(employee.getAddressList().get(0).getHouseNumber());

        editEmployeeDialog.postCodeInputDisplayed().clear();
        editEmployeeDialog.clickEditEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Postcode is required");

        editEmployeeDialog.setPostcode(employee.getAddressList().get(0).getPostCode());

        editEmployeeDialog.dateOfBirthInputDisplayed().clear();
        editEmployeeDialog.clickEditEmployeeBtn();
        editEmployeeDialog.assertGrowlMessageDisplayed("Date Of Birth is required");
    }

    @Test
    public void employeeDetails_displayedAsExpected() {
        Assert.assertEquals(employee.getId()+"",editEmployeeDialog.employeeIdInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getFirstName(),editEmployeeDialog.firstNameInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getLastName(),editEmployeeDialog.lastNameInputDisplayed().getAttribute("value"));
        Assert.assertEquals(formateDate(employee.getDateOfBirth(),DATE_FORMATE_STR),editEmployeeDialog.dateOfBirthInputDisplayed().getAttribute("value"));

        Assert.assertEquals(employee.getAddressList().get(0).getHouseNumber(),editEmployeeDialog.houseNumberInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getAddressList().get(0).getAddressFirstLine(),editEmployeeDialog.addressFirstLineInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getAddressList().get(0).getAddressSecondLine(),editEmployeeDialog.addressSecondLineInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getAddressList().get(0).getTownCity(),editEmployeeDialog.townCityInputDisplayed().getAttribute("value"));
        Assert.assertEquals("AB1-CDX",editEmployeeDialog.postCodeInputDisplayed().getAttribute("value"));
    }

    @Test
    public void employeeDetails_updatedAsExpected() {
        editEmployeeDialog.setFirstName(employee.getFirstName()+"-upt");
        editEmployeeDialog.setLastName(employee.getLastName()+"-upt");

        editEmployeeDialog.setHouseNumber(employee.getAddressList().get(0).getHouseNumber()+"-upt");

        editEmployeeDialog.clickEditEmployeeBtn();

        adminPage.assertPageIsPresent();

        FindEmployeeDialog findEmployeeDialog = adminPage.clickEditEmployeeMenuItem();
        findEmployeeDialog.assertDialogIsPresent();

        findEmployeeDialog.setEmployeeId(employee.getId() + "");
        editEmployeeDialog = findEmployeeDialog.clickFindEmployeeBtn();

        editEmployeeDialog.assertDialogIsPresent();

        Assert.assertEquals(employee.getId()+"",editEmployeeDialog.employeeIdInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getFirstName()+"-upt",editEmployeeDialog.firstNameInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getLastName()+"-upt",editEmployeeDialog.lastNameInputDisplayed().getAttribute("value"));

        Assert.assertEquals(employee.getAddressList().get(0).getHouseNumber()+"-upt",editEmployeeDialog.houseNumberInputDisplayed().getAttribute("value"));
    }

    private String formateDate(Date date, String formatString) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(formatString);
        return simpleDateFormat.format(date);
    }
}
