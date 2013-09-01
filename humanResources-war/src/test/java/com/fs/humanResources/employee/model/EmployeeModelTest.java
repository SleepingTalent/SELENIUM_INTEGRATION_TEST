package com.fs.humanResources.employee.model;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.employee.view.address.AddressViewBean;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmployeeModelTest extends BaseUnitTest{

    @InjectMocks
    EmployeeModel employeeModel;

    EmployeeViewBean employee;

    @Before
    public void setUp() {
        employee = createEmployee();
        employeeModel.setEmployee(employee);
    }

    private EmployeeViewBean createEmployee() {
        String firstName = "Joe";
        String lastName = "Smith";
        Date dataOfBirth = new Date();
        Long employeeId = 12345l;

        Long addressId = 5668l;
        String houseNumber = "50" ;
        String addressFirstLine = "Test Driven Way";
        String addressSecondLine = "Domain Court";
        String townCity = "Progammer City";
        String postCode = "AB1 CDXY";

        AddressViewBean address = new AddressViewBean(addressId,houseNumber,addressFirstLine,addressSecondLine,townCity,postCode);
        return new EmployeeViewBean(employeeId,firstName,lastName,dataOfBirth,address);
    }

    @Test
    public void getEmployee_returnsAsExpected() {
        EmployeeViewBean actual = employeeModel.getEmployee();

        Assert.assertEquals(employee.getFirstName(), actual.getFirstName());
        Assert.assertEquals(employee.getLastName(), actual.getLastName());
        Assert.assertEquals(employee.getEmployeeId(), actual.getEmployeeId());


        DateFormat dateFormat = new SimpleDateFormat();

        Assert.assertEquals(dateFormat.format(employee.getDateOfBirth()), dateFormat.format(actual.getDateOfBirth()));

        AddressViewBean expectedAddress = employee.getAddress();
        AddressViewBean actualAddress = actual.getAddress();

        Assert.assertEquals(expectedAddress.getHouseNumber(), actualAddress.getHouseNumber());
        Assert.assertEquals(expectedAddress.getAddressFirstLine(), actualAddress.getAddressFirstLine());
        Assert.assertEquals(expectedAddress.getAddressSecondLine(), actualAddress.getAddressSecondLine());
        Assert.assertEquals(expectedAddress.getTownCity(), actualAddress.getTownCity());
        Assert.assertEquals(expectedAddress.getPostCode(), actualAddress.getPostCode());
    }
}
