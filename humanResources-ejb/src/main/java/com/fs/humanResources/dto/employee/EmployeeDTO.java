package com.fs.humanResources.dto.employee;


import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.model.address.helper.AddressHelper;
import com.fs.humanResources.model.employee.entities.Employee;

import java.util.Date;

public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long empolyeeId;
    private AddressDTO address;

    public EmployeeDTO(String firstName, String lastName, Date dateOfBirth, Long empolyeeId, AddressDTO address) {
        this(firstName,lastName, dateOfBirth,empolyeeId);
        this.address = address;
    }

    public EmployeeDTO(String firstName, String lastName, Date dateOfBirth, Long empolyeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.empolyeeId = empolyeeId;
    }

    public EmployeeDTO(Employee employee) {
        this(employee.getFirstName(),employee.getLastName(),
                employee.getDateOfBirth(),employee.getStaffNumber());

        AddressHelper addressHelper = new AddressHelper();
        this.address =  new AddressDTO(addressHelper.findPrimaryAddress(employee.getAddressList()));
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

    public AddressDTO getAddress() {
        return address;
    }
}
