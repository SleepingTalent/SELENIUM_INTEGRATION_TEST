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
    public void showFindEmployeeDialog_callsExpectedMethod() {
        viewHelper.showFindEmployeeDialog();
        verify(dialogHelper, times(1)).showDialog(eq(Dialogs.FIND_EMPLOYEE));
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
    public void hideFindEmployeeDialog_callsExpectedMethod() {
        viewHelper.hideFindEmployeeDialog();
        verify(dialogHelper, times(1)).hideDialog(eq(Dialogs.FIND_EMPLOYEE));
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
}
