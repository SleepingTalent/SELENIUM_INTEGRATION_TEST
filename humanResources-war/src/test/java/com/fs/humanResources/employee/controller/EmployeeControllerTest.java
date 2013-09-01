package com.fs.humanResources.employee.controller;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.model.EmployeeModel;
import com.fs.humanResources.employee.view.address.AddressViewBean;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import com.fs.humanResources.service.HumanResourcesService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;

import java.util.Date;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class EmployeeControllerTest extends BaseUnitTest {

    @InjectMocks
    EmployeeController employeeController;

    @Mock
    EmployeeModel employeeModel;

    @Mock
    HumanResourcesService humanResourcesService;

    EmployeeViewBean employee;

    @Before
    public void setUp() {
        employee = createEmployee();
        when(employeeModel.getEmployee()).thenReturn(employee);
    }

    private EmployeeViewBean createEmployee() {
        String firstName = "Joe";
        String lastName = "Smith";
        Date dataOfBirth = new Date();
        Long employeeId = 12345l;

        Long addressId = 5678l;
        String houseNumber = "50";
        String addressFirstLine = "Test Driven Way";
        String addressSecondLine = "Domain Court";
        String townCity = "Progammer City";
        String postCode = "AB1 CDXY";

        AddressViewBean address = new AddressViewBean(addressId,houseNumber, addressFirstLine, addressSecondLine, townCity, postCode);
        return new EmployeeViewBean(employeeId,firstName, lastName, dataOfBirth, address);
    }

    @Test
    public void getEmployee_returnsAsExpected() {
        EmployeeViewBean actual = employeeController.getEmployee();

        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getEmployeeId(), actual.getEmployeeId());
        Assert.assertEquals(employee.getDateOfBirth(), actual.getDateOfBirth());

        AddressViewBean expectedAddress = employee.getAddress();
        AddressViewBean actualAddress = actual.getAddress();

        Assert.assertEquals(expectedAddress.getHouseNumber(), actualAddress.getHouseNumber());
        Assert.assertEquals(expectedAddress.getAddressFirstLine(), actualAddress.getAddressFirstLine());
        Assert.assertEquals(expectedAddress.getAddressSecondLine(), actualAddress.getAddressSecondLine());
        Assert.assertEquals(expectedAddress.getTownCity(), actualAddress.getTownCity());
        Assert.assertEquals(expectedAddress.getPostCode(), actualAddress.getPostCode());
    }

    @Test
    public void clearEmployeeViewBean_setsModel_asExpected() {
        employeeController.clearEmployee();
        verify(employeeModel, times(1)).setEmployee(Matchers.<EmployeeViewBean>anyObject());
    }

    @Test
    public void saveEmployee_savesEmployee_asExpected() {
        employeeController.saveEmployee();
        verify(employeeModel, times(1)).getEmployee();
        verify(humanResourcesService, times(1)).saveEmployeeDetails(Matchers.<EmployeeDTO>anyObject());
    }

    @Test
    public void updateEmployee_savesEmployee_asExpected() {
        employeeController.updateEmployee();
        verify(employeeModel, times(1)).getEmployee();
        verify(humanResourcesService, times(1)).updateEmployeeDetails(Matchers.<EmployeeDTO>anyObject());
    }
}