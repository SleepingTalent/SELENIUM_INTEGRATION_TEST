package com.fs.humanResources.admin;

import com.fs.humanResources.common.BaseSeleniumTest;
import org.junit.Test;

public class AdminPageTest extends BaseSeleniumTest{

    @Test
    public void exampleTest() {
        humanResourcesTool.openHomePage();
        humanResourcesTool.assertMainPanelPresent();
    }
}
