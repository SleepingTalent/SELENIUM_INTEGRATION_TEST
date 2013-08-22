package com.fs.domain.helper;

import org.openqa.selenium.By;

public class FindByHelper {

    public static By findById(String id) {
        return By.id(id);
    }

    public static By findByClassAndText(String cssClass, String text) {
        return By.xpath("//*[contains(@class,'"+cssClass+"')][normalize-space(text())='"+text+"']");
    }
}
