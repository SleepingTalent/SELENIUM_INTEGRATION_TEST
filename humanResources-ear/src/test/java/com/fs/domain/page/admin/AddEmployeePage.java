package com.fs.domain.page.admin;

import com.fs.domain.common.page.PageObject;
import org.openqa.selenium.WebDriver;

public class AddEmployeePage extends PageObject {

    private static final String FORM = "addEmployee";
    private static final String CONTENT_PANEL_ID = "addEmployeePanel";

    public AddEmployeePage(WebDriver driver) {
        super(driver);
    }

    public void assertPageIsPresent() {
        findFormElementById(FORM, CONTENT_PANEL_ID);
    }
}
