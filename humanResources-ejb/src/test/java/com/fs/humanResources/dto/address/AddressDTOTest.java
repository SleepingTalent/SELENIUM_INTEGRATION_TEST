package com.fs.humanResources.dto.address;

import com.fs.common.BaseUnitTest;
import com.fs.humanResources.model.address.entities.Address;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressDTOTest extends BaseUnitTest{

    AddressDTO addressDTO;

    private Long addressId;
    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;

    @Before
    public void setUp() {

        addressId = 1234l;
        houseNumber = "50" ;
        addressFirstLine = "Test Driven Way";
        addressSecondLine = "Domain Court";
        townCity = "Progammer City";
        postCode = "AB1 CDXY";

        addressDTO = new AddressDTO(addressId,houseNumber,addressFirstLine,addressSecondLine,townCity,postCode,true);
    }

    @Test
    public void idSetAsExpected() {
        Assert.assertEquals(addressId, addressDTO.getId());
    }

    @Test
    public void houseNumberSetAsExpected() {
        Assert.assertEquals(houseNumber, addressDTO.getHouseNumber());
    }

    @Test
    public void addressFirstLineSetAsExpected() {
        Assert.assertEquals(addressFirstLine, addressDTO.getAddressFirstLine());
    }

    @Test
    public void addressSecondLineSetAsExpected() {
        Assert.assertEquals(addressSecondLine, addressDTO.getAddressSecondLine());
    }

    @Test
    public void townCitySetAsExpected() {
        Assert.assertEquals(townCity, addressDTO.getTownCity());
    }

    @Test
    public void postCodeSetAsExpected() {
        Assert.assertEquals(postCode, addressDTO.getPostCode());
    }

    @Test
    public void primartAddressSetAsExpected() {
        Assert.assertTrue("Expected Primary Address Set to True!",addressDTO.isPrimaryAddress());
    }

    @Test
    public void getEntitySetAsEpected() {
        Address address = addressDTO.getEntity();
        Assert.assertEquals(addressId,address.getId());
        Assert.assertEquals(addressFirstLine,address.getAddressFirstLine());
        Assert.assertEquals(addressSecondLine,address.getAddressSecondLine());
        Assert.assertEquals(townCity,address.getTownCity());
        Assert.assertEquals(postCode,address.getPostCode());
        Assert.assertTrue("Expected Primary Address Set to True!",address.isPrimaryAddress());
    }
}
