package com.fs.domain.page.admin;

import com.fs.domain.common.page.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindEmployeeDialog extends PageObject {

    private static final String FORM = "findEmployee";
    private static final String FIND_EMPLOYEE_DIALOG = "findEmployeeDialog";

    private static final String STAFF_NUMBER_INPUT_ID = "staffNumber";
    private static final String STAFF_NUMBER_LABEL_ID = "staffNumberlbl";

    private static final String FIND_EMPLOYEE_BTN = "findEmployeeBtn";

    public FindEmployeeDialog(WebDriver driver) {
        super(driver);
    }

    public void assertDialogIsPresent() {
        findElementById(FIND_EMPLOYEE_DIALOG);
    }

    public WebElement staffNumberInputDisplayed() {
        return findFormElementById(FORM, STAFF_NUMBER_INPUT_ID);
    }

    public WebElement staffNumberLabelDisplayed() {
        return findFormElementById(FORM, STAFF_NUMBER_LABEL_ID);
    }

    public void clickFindEmployeeBtn() {
        findFormElementById(FORM, FIND_EMPLOYEE_BTN).click();
    }

    public void setStaffNumber(String staffNumber) {
        setInputTextOnElement(staffNumberInputDisplayed(), staffNumber);
    }

}
