package com.fs.humanResources.common;

import com.fs.domain.page.HumanResourcesHome;
import com.fs.helper.EntityManagerHelper;
import com.fs.helper.PersitenceHelper;
import org.apache.log4j.Logger;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseSeleniumTest {

    protected static final String DATE_FORMATE_STR = "dd/MM/yyyy";

    private WebDriver driver;

    protected HumanResourcesHome humanResourcesHome;

    protected PersitenceHelper persitenceHelper;

    protected SimpleDateFormat simpleDateFormat;

    private static EntityManager entityManager = EntityManagerHelper.getEntityManagerFactory().createEntityManager();

    protected BaseSeleniumTest() {
        driver = initialiseDriver();
        humanResourcesHome = new HumanResourcesHome(driver);
        persitenceHelper = new PersitenceHelper(entityManager);
        simpleDateFormat = new SimpleDateFormat(DATE_FORMATE_STR);
    }

    @After
    public void tearDown() {
        driver.close();
    }

    private WebDriver initialiseDriver() {
        return new FirefoxDriver();
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String formateDate(Date date) {
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(DATE_FORMATE_STR);
        return simpleDateFormat.format(date);
    }

}
