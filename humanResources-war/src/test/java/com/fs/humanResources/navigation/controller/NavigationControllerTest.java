package com.fs.humanResources.navigation.controller;


import com.fs.common.BaseUnitTest;
import org.junit.Test;
import org.mockito.InjectMocks;

public class NavigationControllerTest extends BaseUnitTest {

    @InjectMocks
    NavigationController navigationController;

    @Test
    public void defaultTest() {
        navigationController.navigateToAddEmployee();
        navigationController.navigateToDeleteEmployee();
        navigationController.navigateToEditEmployee();

        navigationController.navigateToBrowseEmployees();
        navigationController.navigateToSearchForEmployees();
    }
}
