package com.fs.humanResources.view.employee;
import com.fs.humanResources.view.address.AddressViewBean;

import java.util.Date;

public class EmployeeViewBean {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long empolyeeId;
    private AddressViewBean address;

    public EmployeeViewBean(String firstName, String lastName, Date dateOfBirth, Long empolyeeId, AddressViewBean address) {
        this(firstName,lastName, dateOfBirth,empolyeeId);

        this.address = address;
    }

    public EmployeeViewBean(String firstName, String lastName, Date dateOfBirth, Long empolyeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.empolyeeId = empolyeeId;
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

    public Long getEmpolyeeId() {
        return empolyeeId;
    }

    public AddressViewBean getAddress() {
        return address;
    }
}
