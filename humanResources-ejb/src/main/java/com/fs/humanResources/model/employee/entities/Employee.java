package com.fs.humanResources.model.employee.entities;

import com.fs.humanResources.model.address.entities.Address;
import com.fs.humanResources.model.common.entities.BaseEntity;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Indexed
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Field(index = Index.YES, analyze= Analyze.NO, store = Store.YES)
    private String firstName;

    @Field(index = Index.YES, analyze= Analyze.NO, store = Store.YES)
    private String lastName;

    @Field(index = Index.YES, analyze= Analyze.NO, store = Store.YES)
    @DateBridge(resolution = Resolution.DAY)
    private Date dateOfBirth;

    @IndexedEmbedded
    @OneToMany(mappedBy="employee",cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Address> addressList = new ArrayList();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public void setAddressList(List<Address> address) {
        this.addressList = address;
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

    public List<Address> getAddressList() {
        return addressList;
    }

    public void addAddress(Address address) {
        addressList.add(address);
        if(address.getEmployee() != this) {
           address.setEmployee(this);
        }
    }
}
