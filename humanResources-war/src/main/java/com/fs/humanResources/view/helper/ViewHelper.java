package com.fs.humanResources.view.helper;

import com.fs.humanResources.view.dialog.DialogHelper;
import com.fs.humanResources.view.dialog.Dialogs;
import com.fs.humanResources.view.messages.MessagesHelper;
import org.primefaces.context.RequestContext;

import javax.faces.context.FacesContext;

public class ViewHelper {

    DialogHelper dialogHelper;

    MessagesHelper messagesHelper;

    public ViewHelper() {
        this(new DialogHelper(),new MessagesHelper());
    }

    public ViewHelper(DialogHelper dialogHelper, MessagesHelper messagesHelper) {
        this.dialogHelper = dialogHelper;
        this.messagesHelper = messagesHelper;
    }

    public void failValidation() {
        FacesContext.getCurrentInstance().validationFailed();
    }

    public void addErrorMessage(String summary, String detail) {
        messagesHelper.addErrorMessage(summary, detail);
    }

    public void showFindEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.FIND_EMPLOYEE);
    }

    public void showEditEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.EDIT_EMPLOYEE);
    }

    public void showAddEmployeeDialog() {
        dialogHelper.showDialog(Dialogs.ADD_EMPLOYEE);
    }

    public void showBrowseEmployeesDialog() {
        dialogHelper.showDialog(Dialogs.BROWSE_EMPLOYEES);
    }

    public void hideFindEmployeeDialog() {
        dialogHelper.hideDialog(Dialogs.FIND_EMPLOYEE);
    }

    public void hideEditEmployeeDialog() {
        dialogHelper.hideDialog(Dialogs.EDIT_EMPLOYEE);
    }

    public void hideAddEmployeeDialog() {
        dialogHelper.hideDialog(Dialogs.ADD_EMPLOYEE);
    }
}