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
        setAddress(address);
    }

    public EmployeeViewBean(Long id, String firstName, String lastName, Date dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setDateOfBirth(dateOfBirth);
        setEmployeeId(id);
    }

    public EmployeeViewBean() {
        setAddress(new AddressViewBean());
    }

    public EmployeeViewBean(EmployeeDTO employeeDTO) {
        setFirstName(employeeDTO.getFirstName());
        setLastName(employeeDTO.getLastName());
        setDateOfBirth(employeeDTO.getDateOfBirth());
        setEmployeeId(employeeDTO.getId());

        if(employeeDTO.getAddress() != null) {
            setAddress(new AddressViewBean(employeeDTO.getAddress()));
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

    public void setEmployeeId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(AddressViewBean address) {
        this.address = address;
    }

    public EmployeeDTO getDTO() {
        AddressDTO addressDTO = new AddressDTO(address.getId(),address.getHouseNumber(),address.getAddressFirstLine(),address.getAddressSecondLine(),address.getTownCity(),address.getPostCode(),true);

        return new EmployeeDTO(getEmployeeId(),
                getFirstName(),getLastName(),getDateOfBirth(),addressDTO);
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
