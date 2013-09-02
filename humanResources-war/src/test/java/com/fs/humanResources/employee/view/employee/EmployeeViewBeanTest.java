package com.fs.humanResources.employee.view.employee;


import com.fs.common.BaseUnitTest;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.view.address.AddressViewBean;
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

        addressId = 5667l;
        houseNumber = "50" ;
        addressFirstLine = "Test Driven Way";
        addressSecondLine = "Domain Court";
        townCity = "Progammer City";
        postCode = "AB1 CDXY";

        address = new AddressViewBean(addressId,houseNumber,addressFirstLine,addressSecondLine,townCity,postCode);
        employeeViewBean = new EmployeeViewBean(employeeId,firstName,lastName,dataOfBirth,address);
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
        Assert.assertEquals(employeeId, employeeViewBean.getEmployeeId());
    }

    @Test
    public void addressSetAsExpected() {
        Assert.assertEquals(address, employeeViewBean.getAddress());
    }

    @Test
    public void dtoConstructorSetAsExpected() {
        AddressDTO addressDTO = new AddressDTO(addressId,houseNumber,addressFirstLine,addressSecondLine,townCity,postCode,true);
        EmployeeDTO employeeDTO = new EmployeeDTO(employeeId,firstName,lastName,dataOfBirth,addressDTO);

        EmployeeViewBean employeeView = new EmployeeViewBean(employeeDTO);

        Assert.assertEquals(employeeId, employeeView.getEmployeeId());
        Assert.assertEquals(firstName, employeeView.getFirstName());
        Assert.assertEquals(lastName, employeeView.getLastName());
        Assert.assertEquals(dataOfBirth, employeeView.getDateOfBirth());

        Assert.assertEquals(addressId, employeeView.getAddress().getId());
        Assert.assertEquals(houseNumber, employeeView.getAddress().getHouseNumber());
        Assert.assertEquals(addressFirstLine, employeeView.getAddress().getAddressFirstLine());
        Assert.assertEquals(addressSecondLine, employeeView.getAddress().getAddressSecondLine());
        Assert.assertEquals(townCity, employeeView.getAddress().getTownCity());
        Assert.assertEquals(postCode, employeeView.getAddress().getPostCode());
    }
}
