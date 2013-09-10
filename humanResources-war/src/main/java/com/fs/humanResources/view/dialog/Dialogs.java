package com.fs.humanResources.view.dialog;

public enum Dialogs {

    FIND_EMPLOYEE("_findEmployeeDialog"),
    ADD_EMPLOYEE("_addEmployeeDialog"),
    EDIT_EMPLOYEE("_editEmployeeDialog");

    private final String dialogWidgetVar;

    Dialogs(String dialogWidgetVar) {
        this.dialogWidgetVar = dialogWidgetVar;
    }

    public String getDialogWidgetVar() {
        return dialogWidgetVar;
    }

}
