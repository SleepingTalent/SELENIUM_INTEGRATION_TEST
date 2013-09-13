package com.fs.humanResources.admin.browse;

import com.fs.domain.page.admin.AdminPage;
import com.fs.domain.page.browse.BrowseEmployeesPage;
import com.fs.humanResources.common.BaseSeleniumTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;

public class BrowseEmployeesDialogTest extends BaseSeleniumTest {

    private final int EMPLOYEES_TO_ADD = 30;

    AdminPage adminPage;

    BrowseEmployeesPage browseEmployeesPage;

    @Before
    public void setUp() throws ParseException {

        persitenceHelper.beginTransaction();

        for (int i = 0; i < EMPLOYEES_TO_ADD; i++) {
            Address address = new Address();
            address.setHouseNumber("50");
            address.setAddressFirstLine(persitenceHelper.getUniqueString(8));
            address.setAddressSecondLine("Domain Court");
            address.setTownCity("Progammer City");
            address.setPostCode("AB1CDX");
            address.setPrimaryAddress(true);

            Employee employee = new Employee();
            employee.setFirstName("James-"+i);
            employee.setLastName(persitenceHelper.getUniqueString(8));

            employee.setDateOfBirth(simpleDateFormat.parse("15/07/1976"));

            employee.addAddress(address);

            persitenceHelper.persistNewEmployee(employee);
            persitenceHelper.addDeletionCandidate(employee);
        }

        persitenceHelper.commitTransaction();

        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
        adminPage.assertPageIsPresent();

        adminPage.assertBrowseMenuDisplayed().click();
        browseEmployeesPage = adminPage.clickBrowseEmployeesMenuItem();
        browseEmployeesPage.assertDialogIsPresent();
    }

    @After
    public void tearDown() {
        persitenceHelper.deleteCandidates();
        super.tearDown();
    }

    @Test
    public void paginationWorksAsExpected() {
        browseEmployeesPage.assertFirstNamePresent("James-0");
        browseEmployeesPage.assertFirstNamePresent("James-9");

        browseEmployeesPage.clickNextPageBtn();
        browseEmployeesPage.assertFirstNamePresent("James-10");
        browseEmployeesPage.assertFirstNamePresent("James-19");

        browseEmployeesPage.clickNextPageBtn();
        browseEmployeesPage.assertFirstNamePresent("James-20");
        browseEmployeesPage.assertFirstNamePresent("James-29");
    }
}
