package com.fs.domain.page;


import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.AddEmployeePage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HumanResourcesTool extends PageObject {

    Logger log = Logger.getLogger(HumanResourcesTool.class);

    private static final String FORM = "adminTool";
    private static final String CONTENT_PANEL_ID = "contentPanel";
    private static final String LATEST_NEWS_PANEL_ID = "newsPanel";

    private static final String EMPLOYEE_ADMIN_MENU_ID = "employeeAdmin";
    private static final String BROWSE_MENU_ID = "browse";
    private static final String SEARCH_MENU_ID = "search";

    public static final String MENU_ITEM_LABEL_CLASS = "rf-pm-itm-lbl";

    public HumanResourcesTool(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        openHomePage("http://localhost:8181/humanResources/");
        assertHomePageDisplayed();
    }

    private void assertHomePageDisplayed() {
        Assert.assertEquals("Human Resources Home", getDriver().getTitle());
    }

    public void assertPageIsPresent() {
        findFormElementById(FORM, CONTENT_PANEL_ID);
    }

    public WebElement assertEmployeeAdminMenuDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_ADMIN_MENU_ID);
    }

    public WebElement assertLatestNewsPanelDisplayed() {
        return findFormElementById(FORM, LATEST_NEWS_PANEL_ID);
    }

    public WebElement assertBrowseMenuDisplayed() {
        return findFormElementById(FORM, BROWSE_MENU_ID);
    }

    public WebElement assertSearchMenuDisplayed() {
        return findFormElementById(FORM, SEARCH_MENU_ID);
    }

    public WebElement assertEditEmployeeMenuItemDisplayed() {
        return assertMenuItemDisplayed("Edit Employee");
    }

    public WebElement assertAddEmployeeMenuItemDisplayed() {
        return assertMenuItemDisplayed("Add Employee");
    }

    public WebElement assertDeleteEmployeeMenuItemDisplayed() {
        return assertMenuItemDisplayed("Delete Employee");
    }

    public WebElement assertBrowseEmployeeMenuItemDisplayed() {
        return assertMenuItemDisplayed("Browse Employees");
    }

    public WebElement assertSearchForEmployeeMenuItemDisplayed() {
        return assertMenuItemDisplayed("Search For Employee");
    }

    private WebElement assertMenuItemDisplayed(String labelText) {
        return findElementByClassWithText(MENU_ITEM_LABEL_CLASS, labelText);
    }

    public AddEmployeePage clickAddEmployeeMenuItem() {
        assertAddEmployeeMenuItemDisplayed().click();
        return new AddEmployeePage(getDriver());
    }
}
