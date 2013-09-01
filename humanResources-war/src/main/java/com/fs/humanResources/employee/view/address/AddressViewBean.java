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
        this.id = id;
        this.houseNumber = houseNumber;
        this.addressFirstLine = addressFirstLine;
        this.addressSecondLine = addressSecondLine;
        this.townCity = townCity;
        this.postCode = postCode;
    }

    public AddressViewBean() {
    }

    public AddressViewBean(AddressDTO addressDTO) {
        this.id = addressDTO.getId();
        this.houseNumber = addressDTO.getHouseNumber();
        this.addressFirstLine = addressDTO.getAddressFirstLine();
        this.addressSecondLine = addressDTO.getAddressSecondLine();
        this.townCity = addressDTO.getTownCity();
        this.postCode = addressDTO.getPostCode();
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

    public Long geId() {
        return id;
    }
}