package com.fs.humanResources.search.strategy;

import com.fs.common.BaseSearchTest;
import com.fs.common.BaseUnitTest;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.dao.EmployeeDAO;
import com.fs.humanResources.model.employee.entities.Employee;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.log4j.Logger;
import org.apache.lucene.search.Query;
import org.hibernate.search.SearchFactory;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.VarargMatcher;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

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
    public void searchingForId_returns_ExpectedResults() {
      List<Employee> results = employeeSearchStrategy.executeSearch(employee.getId().toString());

      Assert.assertEquals(1,results.size());
      Assert.assertEquals(employee.getId(),results.get(0).getId());
    }

    @Test
    public void searchingForUnknownId_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("57685");
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForFirstname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(employee.getFirstName());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getFirstName(),results.get(0).getFirstName());
    }

    @Test
    public void searchingForUnknownFirstname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForLastname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(employee.getLastName());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getLastName(),results.get(0).getLastName());
    }

    @Test
    public void searchingForUnknownLastname_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForHousename_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(
                employee.getAddressList().get(0).getHouseNumber());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getAddressList().get(0).getHouseNumber(),
                results.get(0).getAddressList().get(0).getHouseNumber());
    }

    @Test
    public void searchingForUnknownHousename_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }

    @Test
    public void searchingForPostcode_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch(
                employee.getAddressList().get(0).getPostCode());

        Assert.assertEquals(1,results.size());
        Assert.assertEquals(employee.getAddressList().get(0).getPostCode(),
                results.get(0).getAddressList().get(0).getPostCode());
    }

    @Test
    public void searchingForUnknownPostcode_returns_ExpectedResults() {
        List<Employee> results = employeeSearchStrategy.executeSearch("unknown");
        Assert.assertEquals(0,results.size());
    }
}
