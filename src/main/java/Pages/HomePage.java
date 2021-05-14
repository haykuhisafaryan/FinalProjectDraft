package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

    private WebDriver driver;
    private By myCartButton = By.xpath("//*[@id=\"top\"]/body/div[2]/div/div[1]/div[2]/div[4]/div/ul/li[4]/a/span[1]");
    private By searchBar = By.xpath("//*[@id=\"search\"]");
    private By extendedSearchButton = By.xpath("//*[@id=\"search_mini_form\"]/span/span[2]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement waitAndXpath(By xpath) {
        WebDriverWait wait = new WebDriverWait(driver, 1000);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(xpath)));
        return driver.findElement(xpath);
    }


    public MyCartPage clickMyCart() {
        waitAndXpath(myCartButton).click();
        return new MyCartPage(driver);
    }

    public SearchResultPage clickSearch(String searchValue) {
        waitAndXpath(searchBar).sendKeys(searchValue, Keys.ENTER);
        return new SearchResultPage(driver);
    }

    public ExtendedSearchPage clickExtendedSearch() {
        waitAndXpath(extendedSearchButton).click();
        return new ExtendedSearchPage(driver);
    }

}