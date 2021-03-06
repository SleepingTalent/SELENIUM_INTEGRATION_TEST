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

    public String navigateToSearchForEmployees() {
        return NavigationOutcomes.SEARCH_FOR_EMPLOYEE.getOutcome();
    }

    public void showFindEmployeeDialogForEdit() {
          viewHelper.showFindEmployeeDialogForEdit();
    }

    public void showFindEmployeeDialogForDelete() {
        viewHelper.showFindEmployeeDialogForDelete();
    }

    public void showAddEmployeeDialog() {
        viewHelper.showAddEmployeeDialog();
    }

    public void showBrowseEmployeesDialog() {
        viewHelper.showBrowseEmployeesDialog();
    }

    public void showSearchResultsDialog() {
        viewHelper.showSearchResultsDialog();
    }

    public void hideAddEmployeeDialog() {
        viewHelper.hideAddEmployeeDialog();
    }

    public void showEditEmployeeDialog() {
        viewHelper.hideFindEmployeeDialogForEdit();
        viewHelper.showEditEmployeeDialog();
    }

    public void hideEditEmployeeDialog() {
        viewHelper.hideEditEmployeeDialog();
    }

    public void showDeleteEmployeeDialog() {
        viewHelper.hideFindEmployeeDialogForDelete();
        viewHelper.showDeleteEmployeeDialog();
    }

    public void showDeleteEmployeeDialogFromBrowse() {
        viewHelper.hideBrowseEmployeesDialog();
        viewHelper.showDeleteEmployeeDialog();
    }

    public void showEditEmployeeDialogFromBrowse() {
        viewHelper.hideBrowseEmployeesDialog();
        viewHelper.showEditEmployeeDialog();
    }

    public void showDeleteEmployeeDialogFromSearch() {
        viewHelper.hideSearchResultsDialog();
        viewHelper.showDeleteEmployeeDialog();
    }

    public void showEditEmployeeDialogFromSearch() {
        viewHelper.hideSearchResultsDialog();
        viewHelper.showEditEmployeeDialog();
    }

    public void hideDeleteEmployeeDialog() {
        viewHelper.hideDeleteEmployeeDialog();
    }
}
