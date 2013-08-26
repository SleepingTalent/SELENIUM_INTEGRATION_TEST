package com.fs.humanResources.navigation.controller;


import com.fs.common.BaseUnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

public class NavigationControllerTest extends BaseUnitTest {

    @InjectMocks
    NavigationController navigationController;


    @Test
    public void navigateToEditEmployee_returnsExpectedOutcome() {
        Assert.assertEquals("editEmployee",navigationController.navigateToEditEmployee());
    }

    @Test
    public void navigateToAddEmployee_returnsExpectedOutcome() {
        Assert.assertEquals("addEmployee",navigationController.navigateToAddEmployee());
    }

    @Test
    public void navigateToDeleteEmployee_returnsExpectedOutcome() {
        Assert.assertEquals("deleteEmployee",navigationController.navigateToDeleteEmployee());
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

