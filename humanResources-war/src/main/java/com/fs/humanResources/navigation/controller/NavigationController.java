package com.fs.humanResources.navigation.controller;


import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class NavigationController {

    Logger log = Logger.getLogger(NavigationController.class);

    public void navigateToEditEmployee() {
        log.info("Navigating..");
    }

    public void navigateToAddEmployee() {
        log.info("Navigating..");
    }

    public void navigateToDeleteEmployee() {
        log.info("Navigating..");
    }

    public void navigateToBrowseEmployees() {
        log.info("Navigating..");
    }

    public void navigateToSearchForEmployees() {
        log.info("Navigating..");
    }

}
