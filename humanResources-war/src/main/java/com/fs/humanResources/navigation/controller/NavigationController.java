package com.fs.humanResources.navigation.controller;

import com.fs.humanResources.navigation.outcome.NavigationOutcomes;
import org.apache.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationController implements Serializable {

    Logger log = Logger.getLogger(NavigationController.class);

    public String navigateToEditEmployee() {
        return NavigationOutcomes.EDIT_EMPLOYEE.getOutcome();
    }

    public String navigateToAddEmployee() {
        log.info("Navigating to addEmployee page.");
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
