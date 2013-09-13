package com.fs.domain.page.browse;

import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.dialog.DeleteEmployeeDialog;
import com.fs.domain.page.admin.dialog.EditEmployeeDialog;
import org.openqa.selenium.WebDriver;

public class BrowseEmployeesPage extends PageObject{

    private static String BROWSE_FORM = "browseEmployees";

    public BrowseEmployeesPage(WebDriver driver) {
        super(driver);
    }

    public void assertDialogIsPresent() {
       findFormElementById(BROWSE_FORM,"browseTable");
    }

    public void assertFirstNamePresent(String firstName) {
        findTableDataWithText(firstName);
    }

    public void clickNextPageBtn() {
        findElementByClass("ui-paginator-next").click();
    }

    public DeleteEmployeeDialog deleteRowWithText(String text) {
        rightClickOnElement(findTableRowWithText(text));
        findFormElementById(BROWSE_FORM,"deleteMenuItem").click();
        return new DeleteEmployeeDialog(getDriver());
    }

    public EditEmployeeDialog editRowWithText(String text) {
        rightClickOnElement(findTableRowWithText(text));
        findFormElementById(BROWSE_FORM,"editMenuItem").click();
        return new EditEmployeeDialog(getDriver());
    }
}
