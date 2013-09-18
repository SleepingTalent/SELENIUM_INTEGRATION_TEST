package com.fs.humanResources.dto.address;


import com.fs.humanResources.model.address.entities.Address;

public class AddressDTO {

    private Long id;
    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;
    private boolean primaryAddress;

    public AddressDTO(Long id, String houseNumber, String addressFirstLine, String addressSecondLine, String townCity, String postCode, boolean primaryAddress) {
        this.id = id;
        this.houseNumber = houseNumber;
        this.addressFirstLine = addressFirstLine;
        this.addressSecondLine = addressSecondLine;
        this.townCity = townCity;
        this.postCode = postCode;
        this.primaryAddress = primaryAddress;
    }

    public AddressDTO(Address address) {
        this(address.getId(),address.getHouseNumber(),
                address.getAddressFirstLine(),
                address.getAddressSecondLine(),
                address.getTownCity(),
                address.getPostCode(),address.isPrimaryAddress());
    }

    public Long getId() {
        return id;
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

    public boolean isPrimaryAddress() {
        return primaryAddress;
    }

    public Address getEntity() {
        Address address = new Address();
        address.setId(getId());
        address.setHouseNumber(getHouseNumber());
        address.setAddressFirstLine(getAddressFirstLine());
        address.setAddressSecondLine(getAddressSecondLine());
        address.setTownCity(getTownCity());
        address.setPostCode(getPostCode());
        address.setPrimaryAddress(isPrimaryAddress());
        return address;
    }
}
