package com.fs.humanResources.dto.employee;


import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.employee.entities.Employee;
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
    private Long addressId;
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

        addressId = 3456l;
        houseNumber = "50" ;
        addressFirstLine = "Test Driven Way";
        addressSecondLine = "Domain Court";
        townCity = "Progammer City";
        postCode = "AB1 CDXY";

        address = new AddressDTO(addressId,houseNumber,addressFirstLine,addressSecondLine,townCity,postCode,true);
        employeeDTO = new EmployeeDTO(employeeId,firstName,lastName,dataOfBirth,address);
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
        Assert.assertEquals(employeeId, employeeDTO.getId());
    }

    @Test
    public void addressSetAsExpected() {
        Assert.assertEquals(address, employeeDTO.getAddress());
    }

    @Test
    public void getEntitySetAsExpected() {
        Employee employee = employeeDTO.getEntity();
        Assert.assertEquals(employeeId,employee.getId());
        Assert.assertEquals(firstName,employee.getFirstName());
        Assert.assertEquals(lastName,employee.getLastName());
        Assert.assertEquals(dataOfBirth,employee.getDateOfBirth());

        Assert.assertEquals(1,employee.getAddressList().size());

        for(Address address1 : employee.getAddressList()) {
            Assert.assertEquals(addressId,address1.getId());
            Assert.assertEquals(addressFirstLine,address1.getAddressFirstLine());
            Assert.assertEquals(addressSecondLine,address1.getAddressSecondLine());
            Assert.assertEquals(townCity,address1.getTownCity());
            Assert.assertEquals(postCode,address1.getPostCode());
            Assert.assertTrue("Expected Primary Address Set to True!",address1.isPrimaryAddress());
        }
    }
}
