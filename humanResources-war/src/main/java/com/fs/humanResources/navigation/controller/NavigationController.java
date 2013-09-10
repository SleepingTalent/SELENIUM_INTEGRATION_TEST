package com.fs.humanResources.navigation.controller;

import com.fs.humanResources.navigation.outcome.NavigationOutcomes;
import com.fs.humanResources.view.helper.ViewHelper;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class NavigationController implements Serializable {

    Logger log = Logger.getLogger(NavigationController.class);

    ViewHelper viewHelper;

    @PostConstruct
    public void postContruct() {
        viewHelper = new ViewHelper();
    }

    public String navigateToHome() {
        log.info("Navigating to home page.");
        return NavigationOutcomes.HOME.getOutcome();
    }

    public String navigateToAdmin() {
        log.info("Navigating to admin page.");
        return NavigationOutcomes.ADMIN.getOutcome();
    }

    public String navigateToBrowseEmployees() {
        return NavigationOutcomes.BROWSE_TO_EMPLOYEE.getOutcome();
    }

    public String navigateToSearchForEmployees() {
        return NavigationOutcomes.SEARCH_FOR_EMPLOYEE.getOutcome();
    }

    public void showFindEmployeeDialog() {
          viewHelper.showFindEmployeeDialog();
    }

    public void showAddEmployeeDialog() {
        viewHelper.showAddEmployeeDialog();
    }

    public void hideAddEmployeeDialog() {
        viewHelper.hideAddEmployeeDialog();
    }

    public void showEditEmployeeDialog() {
        viewHelper.hideFindEmployeeDialog();
        viewHelper.showEditEmployeeDialog();
    }

    public void hideEditEmployeeDialog() {
        viewHelper.hideEditEmployeeDialog();
    }

}
