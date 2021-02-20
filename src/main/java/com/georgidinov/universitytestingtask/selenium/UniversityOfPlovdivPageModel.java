package com.georgidinov.universitytestingtask.selenium;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UniversityOfPlovdivPageModel {

    //== constants ==
    private static final String PU_ADDRESS = "https://uni-plovdiv.bg/";

    //== fields ==
    private WebDriver driver;

    @FindBy(id = "search")
    WebElement searchFormInputField;

    @FindBy(name = "submit")
    WebElement searchFormSubmitElement;

    //== constructors ==
    public UniversityOfPlovdivPageModel(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //== public methods ==
    public void navigateToPuPage() {
        this.driver.get(PU_ADDRESS);
    }

    public void setSearchValue(String searchValue) {
        this.searchFormInputField.clear();
        this.searchFormInputField.sendKeys(searchValue);
    }

    public void submitSearchForm() {
        this.searchFormSubmitElement.click();
    }

}