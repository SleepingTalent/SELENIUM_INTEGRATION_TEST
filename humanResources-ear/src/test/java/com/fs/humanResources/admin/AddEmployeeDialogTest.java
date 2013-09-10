package com.fs.humanResources.admin;

import com.fs.domain.page.admin.AdminPage;
import com.fs.domain.page.admin.dialog.AddEmployeeDialog;
import com.fs.domain.page.admin.dialog.EditEmployeeDialog;
import com.fs.domain.page.admin.dialog.FindEmployeeDialog;
import com.fs.humanResources.common.BaseSeleniumTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class AddEmployeeDialogTest extends BaseSeleniumTest {

    AdminPage adminPage;

    AddEmployeeDialog addEmployeeDialog;

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

        employee.setDateOfBirth(simpleDateFormat.parse("15/07/1976"));

        employee.addAddress(address);

        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
        adminPage.assertPageIsPresent();

        adminPage.assertEmployeeAdminMenuDisplayed().click();

        addEmployeeDialog = adminPage.clickAddEmployeeMenuItem();
        addEmployeeDialog.assertDialogIsPresent();
    }

    @After
    public void tearDown() {
        persitenceHelper.deleteCandidates();
        super.tearDown();
    }

    @Test
    @Ignore
    public void employeeAddedAsExpected() {
        addEmployeeDialog.setFirstName(employee.getFirstName());
        addEmployeeDialog.setLastName(employee.getLastName());
        addEmployeeDialog.setDateOfBirth(formateDate(employee.getDateOfBirth()));

        addEmployeeDialog.setHouseNumber(employee.getAddressList().get(0).getHouseNumber());
        addEmployeeDialog.setAddressFirstLine(employee.getAddressList().get(0).getAddressFirstLine());
        addEmployeeDialog.setAddressSecondLine(employee.getAddressList().get(0).getAddressSecondLine());
        addEmployeeDialog.setTownCity(employee.getAddressList().get(0).getTownCity());
        addEmployeeDialog.setPostcode(employee.getAddressList().get(0).getPostCode());
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertDialogIsNotPresent();

        List<Employee> employeeList = persitenceHelper.findEmployeesByLastname(employee.getLastName());
        Assert.assertEquals(1,employeeList.size());
        Assert.assertEquals(employee.getLastName(),employeeList.get(0).getLastName());
        employee = employeeList.get(0);

        persitenceHelper.addDeletionCandidate(employeeList.get(0));

        adminPage.assertEmployeeAdminMenuDisplayed().click();

        FindEmployeeDialog findEmployeeDialog = adminPage.clickEditEmployeeMenuItem();
        findEmployeeDialog.assertDialogIsPresent();

        findEmployeeDialog.setEmployeeId(employeeList.get(0).getId() + "");
        EditEmployeeDialog editEmployeeDialog = findEmployeeDialog.clickFindEmployeeBtn();
        findEmployeeDialog.assertDialogIsNotPresent();

        editEmployeeDialog.assertDialogIsPresent();

        Assert.assertNotNull(employee.getId()+"",editEmployeeDialog.employeeIdInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getFirstName(),editEmployeeDialog.firstNameInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getLastName(),editEmployeeDialog.lastNameInputDisplayed().getAttribute("value"));
        Assert.assertEquals(formateDate(employee.getDateOfBirth()),editEmployeeDialog.dateOfBirthInputDisplayed().getAttribute("value"));

        Assert.assertEquals(employee.getAddressList().get(0).getHouseNumber(),editEmployeeDialog.houseNumberInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getAddressList().get(0).getAddressFirstLine(),editEmployeeDialog.addressFirstLineInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getAddressList().get(0).getAddressSecondLine(),editEmployeeDialog.addressSecondLineInputDisplayed().getAttribute("value"));
        Assert.assertEquals(employee.getAddressList().get(0).getTownCity(),editEmployeeDialog.townCityInputDisplayed().getAttribute("value"));
        Assert.assertEquals("AB1-CDX",editEmployeeDialog.postCodeInputDisplayed().getAttribute("value"));
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
    public void validationMessages_displayedAsExpected() throws ParseException {
        addEmployeeDialog.setFirstName(employee.getFirstName());
        addEmployeeDialog.setLastName(employee.getLastName());
        addEmployeeDialog.setDateOfBirth(formateDate(employee.getDateOfBirth()));

        addEmployeeDialog.setHouseNumber(employee.getAddressList().get(0).getHouseNumber());
        addEmployeeDialog.setAddressFirstLine(employee.getAddressList().get(0).getAddressFirstLine());
        addEmployeeDialog.setAddressSecondLine(employee.getAddressList().get(0).getAddressSecondLine());
        addEmployeeDialog.setTownCity(employee.getAddressList().get(0).getTownCity());
        addEmployeeDialog.setPostcode(employee.getAddressList().get(0).getPostCode());

        addEmployeeDialog.firstNameInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Firstname is required");

        addEmployeeDialog.setFirstName(employee.getFirstName());

        addEmployeeDialog.lastNameInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Lastname is required");

        addEmployeeDialog.setLastName(employee.getLastName());

        addEmployeeDialog.houseNumberInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("House No is required");

        addEmployeeDialog.setHouseNumber(employee.getAddressList().get(0).getHouseNumber());

        addEmployeeDialog.postCodeInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Postcode is required");

        addEmployeeDialog.setPostcode(employee.getAddressList().get(0).getPostCode());

        addEmployeeDialog.dateOfBirthInputDisplayed().clear();
        addEmployeeDialog.clickAddEmployeeBtn();
        addEmployeeDialog.assertGrowlMessageDisplayed("Date Of Birth is required");
    }
}
