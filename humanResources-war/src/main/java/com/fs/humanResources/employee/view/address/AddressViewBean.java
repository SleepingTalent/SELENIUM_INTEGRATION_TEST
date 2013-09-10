package com.fs.humanResources.employee.view.address;

import com.fs.humanResources.dto.address.AddressDTO;

public class AddressViewBean {

    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;
    private Long id;

    public AddressViewBean(Long id, String houseNumber, String addressFirstLine, String addressSecondLine, String townCity, String postCode) {
        setId(id);
        setHouseNumber(houseNumber);
        setAddressFirstLine(addressFirstLine);
        setAddressSecondLine(addressSecondLine);
        setTownCity(townCity);
        setPostCode(postCode);
    }

    public AddressViewBean() {
    }

    public AddressViewBean(AddressDTO addressDTO) {
        this(addressDTO.getId(),addressDTO.getHouseNumber(),
                addressDTO.getAddressFirstLine(),
                addressDTO.getAddressSecondLine(),
                addressDTO.getTownCity(),addressDTO.getPostCode());
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getAddressFirstLine() {
        return addressFirstLine;
    }

    public String getAddressSecondLine() {
        return addressSecondLine;
    }

    public String getTownCity() {
        return townCity;
    }

    public String getPostCode() {
        return postCode;
    }

    public Long getId() {
        return id;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setAddressFirstLine(String addressFirstLine) {
        this.addressFirstLine = addressFirstLine;
    }

    public void setAddressSecondLine(String addressSecondLine) {
        this.addressSecondLine = addressSecondLine;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setId(Long id) {
        this.id = id;
    }
}