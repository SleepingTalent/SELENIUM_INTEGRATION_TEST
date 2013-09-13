package com.fs.humanResources.view.helper;

import com.fs.common.BaseUnitTest;
import com.fs.common.FacesContextMocker;
import com.fs.humanResources.view.dialog.DialogHelper;
import com.fs.humanResources.view.dialog.Dialogs;
import com.fs.humanResources.view.messages.MessagesHelper;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.faces.context.FacesContext;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;


public class ViewHelperTest extends BaseUnitTest {

    @InjectMocks
    ViewHelper viewHelper;

    @Mock
    DialogHelper dialogHelper;

    @Mock
    MessagesHelper messagesHelper;

    FacesContext facesContext;

    @Before
    public void setUp() {
        facesContext = FacesContextMocker.mockFacesContext();
    }

    @After
    public void tearDown() {
        facesContext.release();
    }

    @Test
    public void failValidation_callsExpectedMethod() {
        viewHelper.failValidation();
        verify(facesContext,times(1)).validationFailed();
    }

    @Test
    public void addErrorMessage_callsExpectedMethod() {
        ArgumentCaptor<String> summaryCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> detailCaptor = ArgumentCaptor.forClass(String.class);
        viewHelper.addErrorMessage("mySummary","myDetail");

        verify(messagesHelper,times(1)).addErrorMessage(summaryCaptor.capture(),detailCaptor.capture());

        Assert.assertEquals("mySummary",summaryCaptor.getValue());
        Assert.assertEquals("myDetail",detailCaptor.getValue());
    }

    @Test
    public void showFindEmployeeDialogForEdit_callsExpectedMethod() {
        viewHelper.showFindEmployeeDialogForEdit();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.FIND_EMPLOYEE_FOR_EDIT));
    }

    @Test
    public void showFindEmployeeDialogForDelete_callsExpectedMethod() {
        viewHelper.showFindEmployeeDialogForDelete();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.FIND_EMPLOYEE_FOR_DELETE));
    }

    @Test
    public void showAddEmployeeDialog_callsExpectedMethod() {
        viewHelper.showAddEmployeeDialog();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.ADD_EMPLOYEE));
    }

    @Test
    public void showEditEmployeeDialog_callsExpectedMethod() {
        viewHelper.showEditEmployeeDialog();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.EDIT_EMPLOYEE));
    }

    @Test
    public void showDeleteEmployeeDialog_callsExpectedMethod() {
        viewHelper.showDeleteEmployeeDialog();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.DELETE_EMPLOYEE));
    }


    @Test
    public void showBrowseEmployeesDialog_callsExpectedMethod() {
        viewHelper.showBrowseEmployeesDialog();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.BROWSE_EMPLOYEES));
    }

    @Test
    public void hideBrowseEmployeesDialog_callsExpectedMethod() {
        viewHelper.hideBrowseEmployeesDialog();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.BROWSE_EMPLOYEES));
    }

    @Test
    public void hideFindEmployeeDialogForEdit_callsExpectedMethod() {
        viewHelper.hideFindEmployeeDialogForEdit();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.FIND_EMPLOYEE_FOR_EDIT));
    }

    @Test
    public void hideFindEmployeeDialogForDelete_callsExpectedMethod() {
        viewHelper.hideFindEmployeeDialogForDelete();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.FIND_EMPLOYEE_FOR_DELETE));
    }

    @Test
    public void hideAddEmployeeDialog_callsExpectedMethod() {
        viewHelper.hideAddEmployeeDialog();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.ADD_EMPLOYEE));
    }

    @Test
    public void hideEditEmployeeDialog_callsExpectedMethod() {
        viewHelper.hideEditEmployeeDialog();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.EDIT_EMPLOYEE));
    }

    @Test
    public void hideDeleteEmployeeDialog_callsExpectedMethod() {
        viewHelper.hideDeleteEmployeeDialog();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.DELETE_EMPLOYEE));
    }
}
