package com.fs.humanResources.admin;

import com.fs.humanResources.common.BaseSeleniumTest;
import com.fs.humanResources.common.SeleniumTestCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(SeleniumTestCategory.class)
public class AdminPageTest extends BaseSeleniumTest{

    @Test
    public void exampleTest() {
        humanResourcesTool.openHomePage();
    }
}
