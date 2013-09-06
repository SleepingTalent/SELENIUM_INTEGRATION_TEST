package com.fs.humanResources.employee.view.employee;
import com.fs.humanResources.dto.address.AddressDTO;
import com.fs.humanResources.dto.employee.EmployeeDTO;
import com.fs.humanResources.employee.view.address.AddressViewBean;

import java.util.Date;

public class EmployeeViewBean {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Long id;
    private AddressViewBean address;

    public EmployeeViewBean(Long id, String firstName, String lastName, Date dateOfBirth, AddressViewBean address) {
        this(id, firstName,lastName, dateOfBirth);

        this.address = address;
    }

    public EmployeeViewBean(Long id, String firstName, String lastName, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.id = id;
    }

    public EmployeeViewBean() {
        address = new AddressViewBean();
    }

    public EmployeeViewBean(EmployeeDTO employeeDTO) {
        this.firstName = employeeDTO.getFirstName();
        this.lastName = employeeDTO.getLastName();
        this.dateOfBirth = employeeDTO.getDateOfBirth();
        this.id = employeeDTO.getId();

        if(employeeDTO.getAddress() != null) {
            address = new AddressViewBean(employeeDTO.getAddress());
        }

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

    public Long getEmployeeId() {
        return id;
    }

    public AddressViewBean getAddress() {
        return address;
    }

    public EmployeeDTO getDTO() {
        AddressDTO addressDTO = new AddressDTO(address.getId(),address.getHouseNumber(),address.getAddressFirstLine(),address.getAddressSecondLine(),address.getTownCity(),address.getPostCode(),true);

        return new EmployeeDTO(getEmployeeId(),
                getFirstName(),getFirstName(),getDateOfBirth(),addressDTO);
    }

    @Override
    public String toString() {
        return "EmployeeViewBean{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
