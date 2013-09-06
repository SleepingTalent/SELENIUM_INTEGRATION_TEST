package com.fs.humanResources.navigation.controller;


import com.fs.common.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

public class NavigationControllerTest extends BaseUnitTest {

    @InjectMocks
    NavigationController navigationController;

    @Test
    public void navigateToHome_returnsExpectedOutcome() {
        Assert.assertEquals("home",navigationController.navigateToHome());
    }

    @Test
    public void navigateToAdmin_returnsExpectedOutcome() {
        Assert.assertEquals("admin",navigationController.navigateToAdmin());
    }

    @Test
    public void navigateToBrowseEmployee_returnsExpectedOutcome() {
        Assert.assertEquals("browseToEmployee",navigationController.navigateToBrowseEmployees());
    }

    @Test
    public void navigateToSearchForEmployee_returnsExpectedOutcome() {
        Assert.assertEquals("searchForEmployee",navigationController.navigateToSearchForEmployees());
    }
}

