package com.fs.humanResources.admin.search;

import com.fs.domain.page.admin.AdminPage;
import com.fs.domain.page.search.EmployeeSearchPage;
import com.fs.humanResources.common.BaseSeleniumTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

public class SearchEmployeesDialogTest extends BaseSeleniumTest {

    private final int EMPLOYEES_TO_ADD = 30;

    AdminPage adminPage;

    EmployeeSearchPage employeeSearchPage;

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

        persitenceHelper.reindex();

        humanResourcesHome.openHomePage();
        humanResourcesHome.assertPageIsPresent();

        adminPage = humanResourcesHome.clickLoginBtn();
        adminPage.assertPageIsPresent();

        adminPage.enterSearchTerm("AB1CDX");
        employeeSearchPage = adminPage.clickSearchButton();
        employeeSearchPage.assertDialogIsPresent();
    }

    @After
    public void tearDown() {
        persitenceHelper.deleteCandidates();
        super.tearDown();
    }

    @Test
    public void paginationWorksAsExpected() {
        employeeSearchPage.assertFirstNamePresent("James-0");
        employeeSearchPage.assertFirstNamePresent("James-9");

        employeeSearchPage.clickNextPageBtn();
        employeeSearchPage.assertFirstNamePresent("James-10");
        employeeSearchPage.assertFirstNamePresent("James-19");

        employeeSearchPage.clickNextPageBtn();
        employeeSearchPage.assertFirstNamePresent("James-20");
        employeeSearchPage.assertFirstNamePresent("James-29");
    }
}
