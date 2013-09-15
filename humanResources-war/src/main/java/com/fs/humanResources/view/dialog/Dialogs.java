package com.fs.humanResources.view.dialog;

public enum Dialogs {

    FIND_EMPLOYEE_FOR_DELETE("_findEmployeeDialogForDelete"),
    FIND_EMPLOYEE_FOR_EDIT("_findEmployeeDialogForEdit"),
    ADD_EMPLOYEE("_addEmployeeDialog"),
    EDIT_EMPLOYEE("_editEmployeeDialog"),
    BROWSE_EMPLOYEES("_browseEmployeesDialog"),
    DELETE_EMPLOYEE("_deleteEmployeeDialog"),
    SEARCH_RESULTS("_searchResultsDialog");

    private final String dialogWidgetVar;

    Dialogs(String dialogWidgetVar) {
        this.dialogWidgetVar = dialogWidgetVar;
    }

    public String getDialogWidgetVar() {
        return dialogWidgetVar;
    }

}
