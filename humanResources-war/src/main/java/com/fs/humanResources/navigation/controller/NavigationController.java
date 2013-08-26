package com.fs.humanResources.navigation.controller;


import com.fs.humanResources.navigation.outcome.NavigationOutcomes;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NavigationController {

    Logger log = Logger.getLogger(NavigationController.class);

    public String navigateToEditEmployee() {
        return NavigationOutcomes.EDIT_EMPLOYEE.getOutcome();
    }

    public String navigateToAddEmployee() {
        return NavigationOutcomes.ADD_EMPLOYEE.getOutcome();
    }

    public String navigateToDeleteEmployee() {
        return NavigationOutcomes.DELETE_EMPLOYEE.getOutcome();
    }

    public String navigateToBrowseEmployees() {
        return NavigationOutcomes.BROWSE_TO_EMPLOYEE.getOutcome();
    }

    public String navigateToSearchForEmployees() {
        return NavigationOutcomes.SEARCH_FOR_EMPLOYEE.getOutcome();
    }

}
