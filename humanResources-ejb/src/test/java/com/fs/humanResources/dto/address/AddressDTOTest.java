package com.fs.humanResources.dto.address;

import com.fs.common.BaseUnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressDTOTest extends BaseUnitTest{

    AddressDTO addressDTO;

    private String houseNumber;
    private String addressFirstLine;
    private String addressSecondLine;
    private String townCity;
    private String postCode;

    @Before
    public void setUp() {

        houseNumber = "50" ;
        addressFirstLine = "Test Driven Way";
        addressSecondLine = "Domain Court";
        townCity = "Progammer City";
        postCode = "AB1 CDXY";

        addressDTO = new AddressDTO(houseNumber,addressFirstLine,addressSecondLine,townCity,postCode);
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
}
