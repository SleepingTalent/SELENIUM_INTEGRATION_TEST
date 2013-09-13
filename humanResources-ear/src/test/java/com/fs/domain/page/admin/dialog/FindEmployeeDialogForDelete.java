package com.fs.domain.page.admin.dialog;

import com.fs.domain.common.page.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindEmployeeDialogForDelete extends PageObject {

    private static final String FORM = "findEmployeeForDelete";
    private static final String FIND_EMPLOYEE_DIALOG_FOR_DELETE = "findEmployeeDialogForDelete";

    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_LABEL_ID = "employeeIdlbl";

    private static final String FIND_EMPLOYEE_BTN = "findEmployeeBtn";

    private static final String DIALOG_VISIBLE_CLASS = "ui-overlay-visible";
    private static final String DIALOG_NOT_VISIBLE_CLASS = "ui-overlay-hidden";

    public FindEmployeeDialogForDelete(WebDriver driver) {
        super(driver);
    }

    public void assertDialogIsPresent() {

    }

    public void assertDialogIsNotPresent() {

    }

    public WebElement employeeInputDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_ID);
    }

    public WebElement employeeIdLabelDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_LABEL_ID);
    }

    public EditEmployeeDialog clickFindEmployeeBtnForEdit() {
        findFormElementById(FORM, FIND_EMPLOYEE_BTN).click();
        return new EditEmployeeDialog(getDriver());
    }

    public DeleteEmployeeDialog clickFindEmployeeBtnForDelete() {
        findFormElementById(FORM, FIND_EMPLOYEE_BTN).click();
        return new DeleteEmployeeDialog(getDriver());
    }

    public void setEmployeeId(String employeeId) {
        setInputTextOnElement(employeeInputDisplayed(), employeeId);
    }

}
