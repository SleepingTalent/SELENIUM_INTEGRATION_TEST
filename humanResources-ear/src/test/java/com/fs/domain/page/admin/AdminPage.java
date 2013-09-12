package com.fs.domain.page.admin;


import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.HumanResourcesHome;
import com.fs.domain.page.admin.dialog.AddEmployeeDialog;
import com.fs.domain.page.admin.dialog.FindEmployeeDialog;
import com.fs.domain.page.browse.BrowseEmployeesPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

    public WebElement assertSearchBtnDisplayed() {
        return findFormElementById(FORM,"searchBtn");
    }

    private WebElement assertMenuDisplayed(String labelText) {
        return findLinkByText(labelText);
    }

    public WebElement assertLogoutBtnDisplayed() {
        return findFormElementById(FORM,"logoutBtn");
    }

    public AddEmployeeDialog clickAddEmployeeMenuItem() {
        assertAddEmployeeMenuItemDisplayed().click();
        return new AddEmployeeDialog(getDriver());
    }

    public FindEmployeeDialog clickEditEmployeeMenuItem() {
        moveToEditEmployeeMenuItem();
        assertEditEmployeeMenuItemDisplayed().click();
        return new FindEmployeeDialog(getDriver());
    }

    public BrowseEmployeesPage clickBrowseEmployeesMenuItem() {
        assertBrowseEmployeeMenuItemDisplayed().click();
        return new BrowseEmployeesPage(getDriver());
    }

    private void moveToEditEmployeeMenuItem() {
        new Actions(getDriver()).moveToElement(assertEditEmployeeMenuItemDisplayed()).perform();
    }

    public HumanResourcesHome clickLogoutBtn() {
        assertLogoutBtnDisplayed().click();
        return new HumanResourcesHome(getDriver());
    }

}
