package com.fs.domain.page;


import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.AddEmployeeDialog;
import com.fs.domain.page.admin.FindEmployeeDialog;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HumanResourcesTool extends PageObject {

    Logger log = Logger.getLogger(HumanResourcesTool.class);

    private static final String FORM = "adminTool";
    private static final String CONTENT_PANEL_ID = "contentPanel";
    private static final String LATEST_NEWS_PANEL_ID = "newsPanel";

    //public static final String MENU_LABEL_CLASS = "ui-menuitem-text";
    //public static final String MENU_LABEL_CLASS = "ui-panelmenu-panel";

    public HumanResourcesTool(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        openHomePage("http://localhost:8181/humanResources/");
        getDriver().manage().window().maximize();
        assertHomePageDisplayed();
    }

    private void assertHomePageDisplayed() {
        Assert.assertEquals("Human Resources Home", getDriver().getTitle());
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
        //return findElementByClassWithText(MENU_LABEL_CLASS, labelText);
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
