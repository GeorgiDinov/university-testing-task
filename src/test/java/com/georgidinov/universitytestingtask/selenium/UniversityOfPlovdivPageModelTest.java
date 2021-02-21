package com.georgidinov.universitytestingtask.selenium;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Slf4j
class UniversityOfPlovdivPageModelTest {

    //== constants ==
    private static final String EXPECTED_MAIN_PAGE_TITTLE = "Пловдивски университет \"Паисий Хилендарски\"";
    private static final String EXPECTED_SEARCH_PAGE_TITTLE = "Търсене | Пловдивски Университет \"Паисий Хилендарски\"";
    public static final String EXPECTED_FMI_PAGE_TITLE = "Факултет по математика и информатика | Пловдивски Университет \"Паисий Хилендарски\"";

    private static final String EXPECTED_PU_URL_AFTER_SEARCH_OPERATION = "https://uni-plovdiv.bg/search/basic/";

    private static final String DIV_WITH_SEARCH_LINKS_ID = "newsFor-page";
    private static final String FMI_LINK_VALUE = "Факултет по математика и информатика";
    private static final String FMI_INFO_PAGE_URL = "https://uni-plovdiv.bg/pages/index/38/";

    //== fields ==
    WebDriver driver;

    UniversityOfPlovdivPageModel puPageModel;

    @BeforeAll
    static void setUpDriver() {
        System.setProperty("webdriver.gecko.driver", "geckodriver/geckodriver.exe");
    }

    @BeforeEach
    void setUp() {
        this.driver = new FirefoxDriver();
        this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        this.puPageModel = new UniversityOfPlovdivPageModel(this.driver);
    }

    @Test
    void checkMainPageTitleNameMatches() {
        this.puPageModel.navigateToPuPage();
        assertEquals(EXPECTED_MAIN_PAGE_TITTLE, this.puPageModel.getPageTittle());
    }

    @Test
    void performFMISearch() {
        this.searchFor("fmi");
        assertEquals(EXPECTED_PU_URL_AFTER_SEARCH_OPERATION, this.driver.getCurrentUrl());
    }

    @Test
    void checkSearchPageTittleNameMatches() {
        this.searchFor("something");
        assertEquals(EXPECTED_SEARCH_PAGE_TITTLE, this.puPageModel.getPageTittle());
    }

    @Test
    void validateMainSearchContainerExists() {
        this.searchFor("fmi");
        WebElement hyperLinksContainer = this.puPageModel.findElementById(DIV_WITH_SEARCH_LINKS_ID);
        assertNotNull(hyperLinksContainer);
    }

    @Test
    void goToFMIInfoPage() {
        this.searchFor("fmi");
        WebElement hyperLinksContainer = this.puPageModel.findElementById(DIV_WITH_SEARCH_LINKS_ID);
        String href = hyperLinksContainer.findElement(By.linkText(FMI_LINK_VALUE)).getAttribute("href");
        this.driver.get(href);
        assertEquals(FMI_INFO_PAGE_URL, this.driver.getCurrentUrl());
        assertEquals(EXPECTED_FMI_PAGE_TITLE, this.puPageModel.getPageTittle());
    }


    @AfterEach
    void tearDown() {
        if (this.driver != null) {
            this.driver.close();
        }
    }

    //== private methods ==
    private void searchFor(String searchValue) {
        this.puPageModel.navigateToPuPage();
        this.puPageModel.setSearchValue(searchValue);
        this.puPageModel.submitSearchForm();
    }
}