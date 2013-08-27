package com.fs.domain.page.admin;

import com.fs.domain.common.page.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddEmployeePage extends PageObject {

    private static final String FORM = "addEmployee";
    private static final String CONTENT_PANEL_ID = "addEmployeePanel";
    private static final String FIRSTNAME_INPUT_ID = "firstName";
    private static final String LASTNAME_INPUT_ID = "lastName";
    private static final String FIRSTNAME_LABEL_ID = "firstNamelbl";
    private static final String LASTNAME_LABEL_ID = "lastNamelbl";

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void assertPageIsPresent() {
        findFormElementById(FORM, CONTENT_PANEL_ID);
    }

    public WebElement firstNameInputDisplayed() {
        return findFormElementById(FORM, FIRSTNAME_INPUT_ID);
    }

    public WebElement lastNameInputDisplayed() {
        return findFormElementById(FORM, LASTNAME_INPUT_ID);
    }

    public WebElement firstNameLabelDisplayed() {
        return findFormElementById(FORM, FIRSTNAME_LABEL_ID);
    }

    public WebElement lastNameLabelDisplayed() {
        return findFormElementById(FORM, LASTNAME_LABEL_ID);
    }
}
