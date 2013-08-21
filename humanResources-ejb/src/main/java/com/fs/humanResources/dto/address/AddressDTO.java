package com.fs.humanResources.dto.address;


import com.fs.humanResources.model.address.entities.Address;

public class AddressDTO {

    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;

    public AddressDTO(String houseNumber, String addressFirstLine, String addressSecondLine, String townCity, String postCode) {
        this.houseNumber = houseNumber;
        this.addressFirstLine = addressFirstLine;
        this.addressSecondLine = addressSecondLine;
        this.townCity = townCity;
        this.postCode = postCode;
    }

    public AddressDTO(Address address) {
        this(address.getHouseNumber(),
                address.getAddressFirstLine(),
                address.getAddressSecondLine(),
                address.getTownCity(),
                address.getPostCode());
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
}
