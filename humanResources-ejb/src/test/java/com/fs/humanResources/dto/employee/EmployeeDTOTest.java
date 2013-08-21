package com.fs.humanResources.dto.employee;


import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.address.AddressDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class EmployeeDTOTest extends BaseUnitTest {

    EmployeeDTO employeeDTO;

    private String firstName;
    private String lastName;
    private Date dataOfBirth;
    private Long employeeId;

    private AddressDTO address;
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

        address = new AddressDTO(houseNumber,addressFirstLine,addressSecondLine,townCity,postCode);
        employeeDTO = new EmployeeDTO(firstName,lastName,dataOfBirth,employeeId,address);
    }

    @Test
    public void firstNameSetAsExpected() {
       Assert.assertEquals(firstName, employeeDTO.getFirstName());
    }

    @Test
    public void lastNameSetAsExpected() {
        Assert.assertEquals(lastName, employeeDTO.getLastName());
    }

    @Test
    public void dateOfBirthSetAsExpected() {
        Assert.assertEquals(dataOfBirth, employeeDTO.getDateOfBirth());
    }

    @Test
    public void employeeIdSetAsExpected() {
        Assert.assertEquals(employeeId, employeeDTO.getStaffNumber());
    }

    @Test
    public void addressSetAsExpected() {
        Assert.assertEquals(address, employeeDTO.getAddress());
    }
}
