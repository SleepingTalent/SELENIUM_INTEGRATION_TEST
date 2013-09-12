package com.fs.domain.page.browse;

import com.fs.domain.common.page.PageObject;
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
}
