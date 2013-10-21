package com.fs.domain.page;


import com.fs.domain.common.page.PageObject;
import com.fs.domain.page.admin.AdminPage;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HumanResourcesHome extends PageObject {

    Logger log = Logger.getLogger(HumanResourcesHome.class);

    private static final String FORM = "login";
    private static final String CONTENT_PANEL_ID = "contentPanel";
    private static final String LOGIN_BTN_ID = "loginBtn";

    public HumanResourcesHome(WebDriver driver) {
        super(driver);
    }

    public void openHomePage() {
        openHomePage("http://localhost:8080/humanResources/");
        getDriver().manage().window().maximize();
        assertHomePageDisplayed();
    }

    private void assertHomePageDisplayed() {
        Assert.assertEquals("Human Resources Home", getDriver().getTitle());
    }

    public void assertPageIsPresent() {
        findFormElementById(FORM, CONTENT_PANEL_ID);
    }

    public WebElement assertLoginButtonDisplayed() {
        return findFormElementById(FORM, LOGIN_BTN_ID);
    }

    public AdminPage clickLoginBtn() {
        assertLoginButtonDisplayed().click();
        return new AdminPage(getDriver());
    }

}
