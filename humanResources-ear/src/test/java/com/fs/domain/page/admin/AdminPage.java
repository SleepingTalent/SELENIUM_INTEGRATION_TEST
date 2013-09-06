package com.fs.domain.page.admin;


import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.dialog.AddEmployeeDialog;
import com.fs.domain.page.admin.dialog.FindEmployeeDialog;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminPage extends PageObject {

    Logger log = Logger.getLogger(AdminPage.class);

    private static final String FORM = "adminTool";
    private static final String CONTENT_PANEL_ID = "contentPanel";
    private static final String LATEST_NEWS_PANEL_ID = "newsPanel";

    public AdminPage(WebDriver driver) {
        super(driver);
    }

    public void assertPageIsPresent() {
        findFormElementById(FORM, CONTENT_PANEL_ID);
    }

    public WebElement assertEmployeeAdminMenuDisplayed() {
        return assertMenuDisplayed("Employee Admin");
    }

    public WebElement assertLatestNewsPanelDisplayed() {
        return findFormElementById(FORM, LATEST_NEWS_PANEL_ID);
    }

    public WebElement assertBrowseMenuDisplayed() {
        return assertMenuDisplayed("Browse");
    }

    public WebElement assertSearchMenuDisplayed() {
        return assertMenuDisplayed("Search");
    }

    public WebElement assertEditEmployeeMenuItemDisplayed() {
        return findFormElementById(FORM,"editEmployee");
    }

    public WebElement assertAddEmployeeMenuItemDisplayed() {
        return findFormElementById(FORM,"addEmployee");
    }

    public WebElement assertDeleteEmployeeMenuItemDisplayed() {
        return findFormElementById(FORM,"deleteEmployee");
    }

    public WebElement assertBrowseEmployeeMenuItemDisplayed() {
        return findFormElementById(FORM,"browseEmployees");
    }

    public WebElement assertSearchForEmployeeMenuItemDisplayed() {
        return findFormElementById(FORM,"searchEmployees");
    }

    private WebElement assertMenuDisplayed(String labelText) {
        return findLinkByText(labelText);
    }

    public AddEmployeeDialog clickAddEmployeeMenuItem() {
        assertAddEmployeeMenuItemDisplayed().click();
        return new AddEmployeeDialog(getDriver());
    }

    public FindEmployeeDialog clickEditEmployeeMenuItem() {
        assertEditEmployeeMenuItemDisplayed().click();
        return new FindEmployeeDialog(getDriver());
    }
}
