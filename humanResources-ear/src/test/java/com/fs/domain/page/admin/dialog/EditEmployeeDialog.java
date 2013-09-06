package com.fs.domain.page.admin.dialog;

import com.fs.domain.common.page.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditEmployeeDialog extends PageObject {

    private static final String FORM = "editEmployee";
    private static final String EDIT_EMPLOYEE_DIALOG = "editEmployeeDialog";

    private static final String FIRSTNAME_INPUT_ID = "firstName";
    private static final String LASTNAME_INPUT_ID = "lastName";
    private static final String DOB_INPUT_ID = "dateOfBirth";
    private static final String HOUSE_INPUT_ID = "houseNumber";
    private static final String ADDRESS_FIRST_LINE_INPUT_ID = "addressFirstLine";
    private static final String ADDRESS_SECOND_LINE_INPUT_ID = "addressSecondLine";
    private static final String TOWN_CITY_INPUT_ID = "townCity";
    private static final String POSTCODE_INPUT_ID = "postCode";

    private static final String FIRSTNAME_LABEL_ID = "firstNamelbl";
    private static final String LASTNAME_LABEL_ID = "lastNamelbl";
    private static final String DOB_LABEL_ID = "dateOfBirthlbl";
    private static final String HOUSE_NO_LABEL_ID = "houseNumberlbl";
    private static final String ADDRESS_FIRST_LINE_LABEL_ID = "addressFirstLinelbl";
    private static final String ADDRESS_SECOND_LINE_LABEL_ID = "addressSecondLinelbl";
    private static final String TOWN_CITY_LABEL_ID = "townCitylbl";
    private static final String POSTCODE_LABEL_ID = "postCodelbl";

    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE_ID_LABEL = "employeeIdlbl";


    private static final String EDIT_EMPLOYEE_BTN = "editEmployeeBtn";

    public EditEmployeeDialog(WebDriver driver) {
        super(driver);
    }

    public void assertDialogIsPresent() {
        findElementById(EDIT_EMPLOYEE_DIALOG);
    }

    public WebElement employeeIdInputDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_ID);
    }

    public WebElement employeeIdLabelDisplayed() {
        return findFormElementById(FORM, EMPLOYEE_ID_LABEL);
    }

    public WebElement firstNameInputDisplayed() {
        return findFormElementById(FORM, FIRSTNAME_INPUT_ID);
    }

    public WebElement lastNameInputDisplayed() {
        return findFormElementById(FORM, LASTNAME_INPUT_ID);
    }

    public WebElement dateOfBirthInputDisplayed() {
        return findFormElementById(FORM, DOB_INPUT_ID);
    }

    public WebElement houseNumberInputDisplayed() {
        return findFormElementById(FORM, HOUSE_INPUT_ID);
    }

    public WebElement addressFirstLineInputDisplayed() {
        return findFormElementById(FORM, ADDRESS_FIRST_LINE_INPUT_ID);
    }

    public WebElement addressSecondLineInputDisplayed() {
        return findFormElementById(FORM, ADDRESS_SECOND_LINE_INPUT_ID);
    }

    public WebElement townCityInputDisplayed() {
        return findFormElementById(FORM, TOWN_CITY_INPUT_ID);
    }

    public WebElement postCodeInputDisplayed() {
        return findFormElementById(FORM, POSTCODE_INPUT_ID);
    }

    public WebElement firstNameLabelDisplayed() {
        return findFormElementById(FORM, FIRSTNAME_LABEL_ID);
    }

    public WebElement lastNameLabelDisplayed() {
        return findFormElementById(FORM, LASTNAME_LABEL_ID);
    }

    public WebElement dateOfBirthLabelDisplayed() {
        return findFormElementById(FORM, DOB_LABEL_ID);
    }

    public WebElement houseNumberLabelDisplayed() {
        return findFormElementById(FORM, HOUSE_NO_LABEL_ID);
    }

    public WebElement addressFirstLineLabelDisplayed() {
        return findFormElementById(FORM, ADDRESS_FIRST_LINE_LABEL_ID);
    }

    public WebElement addressSecondLineLabelDisplayed() {
        return findFormElementById(FORM, ADDRESS_SECOND_LINE_LABEL_ID);
    }

    public WebElement townCityLabelDisplayed() {
        return findFormElementById(FORM, TOWN_CITY_LABEL_ID);
    }

    public WebElement postCodeLabelDisplayed() {
        return findFormElementById(FORM, POSTCODE_LABEL_ID);
    }

    public void clickEditEmployeeBtn() {
        findFormElementById(FORM, EDIT_EMPLOYEE_BTN).click();
    }

    public void setDateOfBirth(String dateOfBirth) {
        setInputTextOnElement(dateOfBirthInputDisplayed(), dateOfBirth);
    }

    public void setFirstName(String fistName) {
        setInputTextOnElement(firstNameInputDisplayed(), fistName);
    }

    public void setLastName(String lastName) {
        setInputTextOnElement(lastNameInputDisplayed(), lastName);
    }

    public void setHouseNumber(String houseNumber) {
        setInputTextOnElement(houseNumberInputDisplayed(), houseNumber);
    }

    public void setAddressFirstLine(String addressFirstLine) {
        setInputTextOnElement(addressFirstLineInputDisplayed(), addressFirstLine);
    }

    public void setAddressSecondLine(String addressSecondLine) {
        setInputTextOnElement(addressSecondLineInputDisplayed(),addressSecondLine);
    }

    public void setTownCity(String townCity) {
        setInputTextOnElement(townCityInputDisplayed(),townCity);
    }

    public void setPostcode(String postcode) {
        setInputTextOnElement(postCodeInputDisplayed(),postcode);
    }
}
