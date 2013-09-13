package com.fs.humanResources.dto.employee;


import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.model.address.helper.AddressHelper;
import com.fs.humanResources.model.employee.entities.Employee;

import java.util.Date;

public class EmployeeDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private AddressDTO address;

    public EmployeeDTO(Long id, String firstName, String lastName, Date dateOfBirth, AddressDTO address) {
        this(id, firstName,lastName, dateOfBirth);
        this.address = address;
    }

    public EmployeeDTO(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public EmployeeDTO(Employee employee) {
        this(employee.getId(),employee.getFirstName(),employee.getLastName(),
                employee.getDateOfBirth());

        AddressHelper addressHelper = new AddressHelper();
        this.address =  new AddressDTO(addressHelper.findPrimaryAddress(employee.getAddressList()));
    }

    public Long getId() {
        return id;
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

    public AddressDTO getAddress() {
        return address;
    }

    public Employee getEntity() {
        Employee employee = new Employee();
        employee.setId(getId());
        employee.setFirstName(getFirstName());
        employee.setLastName(getLastName());
        employee.setDateOfBirth(getDateOfBirth());
        employee.addAddress(getAddress().getEntity());
        return employee;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
