package com.fs.domain.page.admin.dialog;

import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.dialog.EditEmployeeDialog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindEmployeeDialogForEdit extends PageObject {

    private static final String FORM = "findEmployeeForEdit";
    private static final String FIND_EMPLOYEE_DIALOG_FOR_EDIT = "findEmployeeDialogForEdit";

    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_LABEL_ID = "employeeIdlbl";

    private static final String FIND_EMPLOYEE_BTN = "findEmployeeBtn";

    private static final String DIALOG_VISIBLE_CLASS = "ui-overlay-visible";
    private static final String DIALOG_NOT_VISIBLE_CLASS = "ui-overlay-hidden";

    public FindEmployeeDialogForEdit(WebDriver driver) {
        super(driver);
    }

    public void assertDialogIsPresent() {
        assertDialogIsPresent(false);
    }

    public void assertDialogIsPresent(boolean reThrow) {
        findElementById(FORM, reThrow);
    }

    public void assertDialogIsNotPresent() {

    }

    public WebElement employeeInputDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_ID);
    }

    public WebElement employeeIdLabelDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_LABEL_ID);
    }

    public EditEmployeeDialog clickFindEmployeeBtn() {
        findFormElementById(FORM, FIND_EMPLOYEE_BTN).click();
        return new EditEmployeeDialog(getDriver());
    }

    public void setEmployeeId(String employeeId) {
        setInputTextOnElement(employeeInputDisplayed(), employeeId);
    }

}
