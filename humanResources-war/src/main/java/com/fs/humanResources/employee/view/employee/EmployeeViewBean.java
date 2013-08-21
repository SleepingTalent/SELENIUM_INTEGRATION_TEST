package com.fs.humanResources.employee.view.employee;
import com.fs.humanResources.employee.view.address.AddressViewBean;

import java.util.Date;

public class EmployeeViewBean {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long staffNumber;
    private AddressViewBean address;

    public EmployeeViewBean(String firstName, String lastName, Date dateOfBirth, Long staffNumber, AddressViewBean address) {
        this(firstName,lastName, dateOfBirth, staffNumber);

        this.address = address;
    }

    public EmployeeViewBean(String firstName, String lastName, Date dateOfBirth, Long staffNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.staffNumber = staffNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getStaffNumber() {
        return staffNumber;
    }

    public AddressViewBean getAddress() {
        return address;
    }
}
