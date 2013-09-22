package com.fs.humanResources.search.strategy;

import com.fs.common.BaseSearchTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.log4j.Logger;
import org.junit.*;

import java.text.ParseException;
import java.util.List;

public class EmployeeSearchStrategyIntegrationTest extends BaseSearchTest {

    Logger log = Logger.getLogger(EmployeeSearchStrategyIntegrationTest.class);

    EmployeeSearchStrategy employeeSearchStrategy;

    Employee employee;

    Address address;

    @Before
    public void setUp() throws ParseException {
        employeeSearchStrategy = new EmployeeSearchStrategy(getEntityManager());

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

        beginTransaction();
        employee = persitenceHelper.persistNewEmployee(employee);
        persitenceHelper.addDeletionCandidate(employee);
        commitTransaction();
    }

    @After
    public void tearDown() {
       persitenceHelper.deleteCandidates();

    }

    @Test
    public void default_Test() {
        Assert.assertTrue(true);
    }

    @Test
    @Ignore
    public void searchingForId_returns_ExpectedResults() {
      List<Employee> results = employeeSearchStrategy.executeSearch(employee.getId().toString());

      Assert.assertEquals(1,results.size());
      Assert.assertEquals(employee.getId(),results.get(0).getId());
    }

    @Test
    @Ignore
    public void searchingForUnknownId_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("57685");
        Assert.assertEquals(0,results.size());
    }

    @Test
    @Ignore
    public void searchingForFirstname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(employee.getFirstName());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getFirstName(),results.get(0).getFirstName());
    }

    @Test
    @Ignore
    public void searchingForUnknownFirstname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }

    @Test
    @Ignore
    public void searchingForLastname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(employee.getLastName());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getLastName(),results.get(0).getLastName());
    }

    @Test
    @Ignore
    public void searchingForUnknownLastname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }

    @Test
    @Ignore
    public void searchingForHousename_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(
                employee.getAddressList().get(0).getHouseNumber());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getAddressList().get(0).getHouseNumber(),
                results.get(0).getAddressList().get(0).getHouseNumber());
    }

    @Test
    @Ignore
    public void searchingForUnknownHousename_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }

    @Test
    @Ignore
    public void searchingForPostcode_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(
                employee.getAddressList().get(0).getPostCode());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getAddressList().get(0).getPostCode(),
                results.get(0).getAddressList().get(0).getPostCode());
    }

    @Test
    @Ignore
    public void searchingForUnknownPostcode_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }
}
