package com.georgidinov.universitytestingtask.selenium;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class UniversityOfPlovdivPageModel {

    //== constants ==
    private static final String PU_ADDRESS = "https://uni-plovdiv.bg/";


    //== fields ==
    private final WebDriver driver;

    @Getter
    @FindBy(id = "search")
    WebElement searchFormInputField;

    @Getter
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

    public String getPageTittle() {
        return this.driver.getTitle();
    }

    public WebElement findElementById(String elementId) {
        return this.driver.findElement(By.id(elementId));
    }



}