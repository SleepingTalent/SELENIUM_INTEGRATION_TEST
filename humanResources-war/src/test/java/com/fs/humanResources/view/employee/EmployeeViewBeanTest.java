package com.fs.humanResources.view.employee;


import com.fs.common.BaseUnitTest;
import com.fs.humanResources.view.address.AddressViewBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class EmployeeViewBeanTest extends BaseUnitTest {

    EmployeeViewBean employeeViewBean;

    private String firstName;
    private String lastName;
    private Date dataOfBirth;
    private Long employeeId;

    private AddressViewBean address;
    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;

    @Before
    public void setUp() {
        firstName = "Joe";
        lastName = "Smith";
        dataOfBirth = new Date();
        employeeId = 12345l;

        houseNumber = "50" ;
        addressFirstLine = "Test Driven Way";
        addressSecondLine = "Domain Court";
        townCity = "Progammer City";
        postCode = "AB1 CDXY";

        address = new AddressViewBean(houseNumber,addressFirstLine,addressSecondLine,townCity,postCode);
        employeeViewBean = new EmployeeViewBean(firstName,lastName,dataOfBirth,employeeId,address);
    }

    @Test
    public void firstNameSetAsExpected() {
       Assert.assertEquals(firstName, employeeViewBean.getFirstName());
    }

    @Test
    public void lastNameSetAsExpected() {
        Assert.assertEquals(lastName, employeeViewBean.getLastName());
    }

    @Test
    public void dateOfBirthSetAsExpected() {
        Assert.assertEquals(dataOfBirth, employeeViewBean.getDateOfBirth());
    }

    @Test
    public void employeeIdSetAsExpected() {
        Assert.assertEquals(employeeId, employeeViewBean.getEmpolyeeId());
    }

    @Test
    public void addressSetAsExpected() {
        Assert.assertEquals(address, employeeViewBean.getAddress());
    }
}
