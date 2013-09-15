package com.fs.humanResources.navigation.controller;


import com.fs.common.BaseUnitTest;
import com.fs.humanResources.view.helper.ViewHelper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

public class NavigationControllerTest extends BaseUnitTest {

    @InjectMocks
    NavigationController navigationController;

    @Mock
    ViewHelper viewHelper;

    @Test
    public void navigateToHome_returnsExpectedOutcome() {
        Assert.assertEquals("home",navigationController.navigateToHome());
    }

    @Test
    public void navigateToAdmin_returnsExpectedOutcome() {
        Assert.assertEquals("admin",navigationController.navigateToAdmin());
    }

    @Test
    public void navigateToSearchForEmployee_returnsExpectedOutcome() {
        Assert.assertEquals("searchForEmployee",navigationController.navigateToSearchForEmployees());
    }

    @Test
    public void postContruct_createsViewHelperInstance() {
        navigationController.postContruct();
        Assert.assertEquals("com.fs.humanResources.view.helper.ViewHelper",navigationController.viewHelper.getClass().getName());
    }

    @Test
    public void hideAddEmployeeDialog_callsExpectedMethod() {
        navigationController.hideAddEmployeeDialog();
        verify(viewHelper, times(1)).hideAddEmployeeDialog();
    }

    @Test
    public void hideEditEmployeeDialog_callsExpectedMethod() {
        navigationController.hideEditEmployeeDialog();
        verify(viewHelper, times(1)).hideEditEmployeeDialog();
    }

    @Test
    public void hideDeleteEmployeeDialog_callsExpectedMethod() {
        navigationController.hideDeleteEmployeeDialog();
        verify(viewHelper, times(1)).hideDeleteEmployeeDialog();
    }

    @Test
    public void showAddEmployeeDialog_callsExpectedMethod() {
        navigationController.showAddEmployeeDialog();
        verify(viewHelper, times(1)).showAddEmployeeDialog();
    }

    @Test
    public void showSearchResultsDialog_callsExpectedMethod() {
        navigationController.showSearchResultsDialog();
        verify(viewHelper, times(1)).showSearchResultsDialog();
    }

    @Test
    public void showEditEmployeeDialog_callsExpectedMethod() {
        navigationController.showEditEmployeeDialog();
        verify(viewHelper, times(1)).hideFindEmployeeDialogForEdit();
        verify(viewHelper, times(1)).showEditEmployeeDialog();
    }

    @Test
    public void showDeleteEmployeeDialog_callsExpectedMethod() {
        navigationController.showDeleteEmployeeDialog();
        verify(viewHelper, times(1)).hideFindEmployeeDialogForDelete();
        verify(viewHelper, times(1)).showDeleteEmployeeDialog();
    }

    @Test
    public void showDeleteEmployeeDialogFromBrowse_callsExpectedMethod() {
        navigationController.showDeleteEmployeeDialogFromBrowse();
        verify(viewHelper, times(1)).hideBrowseEmployeesDialog();
        verify(viewHelper, times(1)).showDeleteEmployeeDialog();
    }

    @Test
    public void showEditEmployeeDialogFromBrowse_callsExpectedMethod() {
        navigationController.showEditEmployeeDialogFromBrowse();
        verify(viewHelper, times(1)).hideBrowseEmployeesDialog();
        verify(viewHelper, times(1)).showEditEmployeeDialog();
    }

    @Test
    public void showFindEmployeeDialogForEdit_callsExpectedMethod() {
        navigationController.showFindEmployeeDialogForEdit();
        verify(viewHelper, times(1)).showFindEmployeeDialogForEdit();
    }

    @Test
    public void showFindEmployeeDialogForDelete_callsExpectedMethod() {
        navigationController.showFindEmployeeDialogForDelete();
        verify(viewHelper, times(1)).showFindEmployeeDialogForDelete();
    }

    @Test
    public void showBrowseEmployeesDialog_callsExpectedMethod() {
        navigationController.showBrowseEmployeesDialog();
        verify(viewHelper, times(1)).showBrowseEmployeesDialog();
    }

    @Test
    public void showSearchTermDialog_callsExpectedMethod() {
        navigationController.showSearchTermDialog();
        verify(viewHelper, times(1)).showSearchTermDialog();
    }
}

