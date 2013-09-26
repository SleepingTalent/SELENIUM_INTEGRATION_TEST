package com.fs.humanResources.search.service;

import com.fs.common.BaseSearchTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

public class SearchServiceIntegrationTest extends BaseSearchTest {

    Logger log = Logger.getLogger(SearchServiceIntegrationTest.class);

    SearchService searchService;

    Employee employee;

    Address address;

    int first = 0;
    int pageSize = 10;

    @Before
    public void setUp() throws ParseException {
        searchService = new SearchService();
        searchService.setEntityManager(getEntityManager());

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
    public void searchingForId_returns_ExpectedResults() {
      List<Employee> results = searchService.performSearch(employee.getId().toString(), first, pageSize);

      Assert.assertEquals(1,results.size());
      Assert.assertEquals(employee.getId(),results.get(0).getId());
    }

    @Test
    public void searchingForUnknownId_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch("57685", first, pageSize);
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForFirstname_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch(employee.getFirstName(), first, pageSize);

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getFirstName(),results.get(0).getFirstName());
    }

    @Test
    public void searchingForUnknownFirstname_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch("unknown", first, pageSize);
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForLastname_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch(employee.getLastName(), first, pageSize);

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getLastName(),results.get(0).getLastName());
    }

    @Test
    public void searchingForUnknownLastname_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch("unknown", first, pageSize);
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForHousename_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch(
                employee.getAddressList().get(0).getHouseNumber(), first, pageSize);

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getAddressList().get(0).getHouseNumber(),
                results.get(0).getAddressList().get(0).getHouseNumber());
    }

    @Test
    public void searchingForUnknownHousename_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch("unknown", first, pageSize);
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForPostcode_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch(
                employee.getAddressList().get(0).getPostCode(), first, pageSize);

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getAddressList().get(0).getPostCode(),
                results.get(0).getAddressList().get(0).getPostCode());
    }

    @Test
    public void searchingForUnknownPostcode_returns_ExpectedResults() {
        List<Employee> results = searchService.performSearch("unknown", first, pageSize);
        Assert.assertEquals(0,results.size());
    }
}
