package com.fs.humanResources.view.address;

import com.fs.common.BaseUnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressViewBeanTest extends BaseUnitTest{

    AddressViewBean addressViewBean;

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

        addressViewBean = new AddressViewBean(houseNumber,addressFirstLine,addressSecondLine,townCity,postCode);
    }

    @Test
    public void houseNumberSetAsExpected() {
        Assert.assertEquals(houseNumber, addressViewBean.getHouseNumber());
    }

    @Test
    public void addressFirstLineSetAsExpected() {
        Assert.assertEquals(addressFirstLine, addressViewBean.getAddressFirstLine());
    }

    @Test
    public void addressSecondLineSetAsExpected() {
        Assert.assertEquals(addressSecondLine, addressViewBean.getAddressSecondLine());
    }

    @Test
    public void townCitySetAsExpected() {
        Assert.assertEquals(townCity, addressViewBean.getTownCity());
    }

    @Test
    public void postCodeSetAsExpected() {
        Assert.assertEquals(postCode, addressViewBean.getPostCode());
    }
}
