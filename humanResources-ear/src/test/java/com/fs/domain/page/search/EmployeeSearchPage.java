package com.fs.domain.page.search;

import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.dialog.DeleteEmployeeDialog;
import com.fs.domain.page.admin.dialog.EditEmployeeDialog;
import org.openqa.selenium.WebDriver;

public class EmployeeSearchPage extends PageObject{

    private static String FORM = "searchResults";

    public EmployeeSearchPage(WebDriver driver) {
        super(driver);
    }


    public void assertDialogIsPresent() {
        findFormElementById(FORM,"searchResultsTable");
    }

    public void assertFirstNamePresent(String firstName) {
        findTableDataWithText(firstName);
    }

    public void clickNextPageBtn() {
        findElementByClass("ui-paginator-next").click();
    }

    public DeleteEmployeeDialog deleteRowWithText(String text) {
        rightClickOnElement(findTableRowWithText(text));
        findFormElementById(FORM,"searchDeleteMenuItem").click();
        return new DeleteEmployeeDialog(getDriver());
    }

    public EditEmployeeDialog editRowWithText(String text) {
        rightClickOnElement(findTableRowWithText(text));
        findFormElementById(FORM,"searchEditMenuItem").click();
        return new EditEmployeeDialog(getDriver());
    }
}
