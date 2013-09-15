package com.fs.humanResources.search.view;


import com.fs.common.BaseUnitTest;
import com.fs.humanResources.browse.view.BrowseViewBean;
import com.fs.humanResources.employee.view.address.AddressViewBean;
import com.fs.humanResources.employee.view.employee.EmployeeViewBean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class SearchViewBeanTest extends BaseUnitTest {

    SearchViewBean searchViewBean;

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
        searchViewBean = new SearchViewBean(employeeViewBean);
    }

    @Test
    public void firstNameSetAsExpected() {
       Assert.assertEquals(firstName, searchViewBean.getEmployee().getFirstName());
    }

    @Test
    public void lastNameSetAsExpected() {
        Assert.assertEquals(lastName, searchViewBean.getEmployee().getLastName());
    }

    @Test
    public void dateOfBirthSetAsExpected() {
        Assert.assertEquals(dataOfBirth, searchViewBean.getEmployee().getDateOfBirth());
    }

    @Test
    public void employeeIdSetAsExpected() {
        Assert.assertEquals(employeeId, searchViewBean.getEmployee().getEmployeeId());
    }

    @Test
    public void addressSetAsExpected() {
        Assert.assertEquals(address, searchViewBean.getEmployee().getAddress());
    }

}
