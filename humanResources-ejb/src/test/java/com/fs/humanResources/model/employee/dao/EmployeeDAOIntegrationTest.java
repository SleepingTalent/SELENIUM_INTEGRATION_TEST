package com.fs.humanResources.model.employee.dao;

import com.fs.common.BaseDAOTest;
import com.fs.humanResources.model.address.dao.AddressDAO;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class EmployeeDAOIntegrationTest extends BaseDAOTest {

    EmployeeDAO employeeDAO;

    Employee employee;

    @Before
    public void setUp() {
        employeeDAO = new EmployeeDAO(getEntityManager());

        employee = new Employee();
        employee.setFirstName("Dave");
        employee.setLastName(persitenceHelper.getUniqueString(8));
        employee.setDateOfBirth(new Date());

        beginTransaction();
        employee = persitenceHelper.persistNewEmployee(employee);
    }

    @After
    public void tearDown() {
        endTransaction();
    }

    @Test
    public void create_createsEmployee_AsExpected() {
        Employee employee = new Employee();
        employee.setFirstName("Rachel");
        employee.setLastName("Smith");
        employee.setDateOfBirth(new Date());

        Assert.assertNull("Expected Id to be Null!", employee.getId());

        employeeDAO.create(employee);

        Assert.assertNotNull("Expected Id to be populated!", employee.getId());
    }

    @Test(expected = NoResultException.class)
    public void delete_removesEmployee_AsExpected() {
        employeeDAO.delete(employee);
        employeeDAO.findById(employee.getId());
    }

    @Test
    public void countAll_returns_AsExpected() {
        Assert.assertTrue("Expected at least 1 result", employeeDAO.countAll() >= 1);
    }

    @Test
    public void update_updatesEmployee_AsExpected() {
        employee.setLastName("modified");

        employeeDAO.update(employee);

        Employee actual = employeeDAO.findById(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals("modified", actual.getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());
    }

    @Test
    public void findById_returns_AsExpected() {
        Employee actual = employeeDAO.findById(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());
    }

    @Test
    public void getEmployeesByLastname_returns_AsExpected() {
        List<Employee> actual = employeeDAO.getEmployeesByLastname(employee.getLastName());

        Assert.assertEquals(1, actual.size());
        Assert.assertEquals(employee.getId(), actual.get(0).getId());
        Assert.assertEquals(employee.getFirstName(), actual.get(0).getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.get(0).getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.get(0).getDateOfBirth());
    }

    @Test
    public void getEmployeeDetails_returns_AsExpected() {
        Employee actual = employeeDAO.getEmployeeDetails(employee.getId());

        Assert.assertEquals(employee.getId(), actual.getId());
        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());
    }

    @Test
    public void employee_updatesAsExpected_newAddressAdded() {
        AddressDAO addressDAO = new AddressDAO(getEntityManager());

        Address newAddress = new Address();
        newAddress.setHouseNumber("20");
        newAddress.setAddressFirstLine("Long Acre");
        newAddress.setAddressSecondLine("Sandgrove Park");
        newAddress.setTownCity("Meanwhile City");
        newAddress.setPostCode("AB1 CD4");
        newAddress.setPrimaryAddress(true);

        Assert.assertEquals(0, employee.getAddressList().size());

        employee.addAddress(newAddress);

        employeeDAO.update(employee);

        getEntityManager().refresh(employee);

        Assert.assertEquals(1, employee.getAddressList().size());
        Assert.assertEquals(1, addressDAO.getEmployeeAddressList(employee).size());

    }

    @Test
    public void findAll_returns_AsExpected() {
        List<Employee> actual = employeeDAO.findAll(0,10);
        Assert.assertTrue("Expected at least 1 result", actual.size() >= 1);
    }

}
