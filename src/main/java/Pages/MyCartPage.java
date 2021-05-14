package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class MyCartPage {

    public WebDriver driver;
    private By removeButton = By.xpath("//*[@id=\"shopping-cart-table\"]/form/div[2]/div[2]/a");
    private By totalPrice = By.xpath("//*[@id=\"shopping-cart-totals-table\"]/tfoot/tr/td[2]/strong/span");
    public List<WebElement> plusButtons;
    public List<WebElement> title;
    public List<WebElement> minusButtons;
    public List<WebElement> refreshButtons;
    public String[] stringTitles;


    public MyCartPage(WebDriver driver) {
        this.driver = driver;
        plusButtons = driver.findElements(By.className("inc_button"));
        title = driver.findElements(By.className("choose_book_name"));
        minusButtons = driver.findElements(By.className("dec_button"));
        refreshButtons = driver.findElements(By.name("update_cart_action"));
        stringTitles = new String[title.size()];
        for (int i = 0; i < stringTitles.length; i++) {
            stringTitles[i] = title.get(i).getText();
        }
    }

    public int getTotalPrice() {
        String letterPrice = driver.findElement(totalPrice).getText();
        letterPrice = letterPrice.substring(0, letterPrice.length() - 2);
        int price = Integer.parseInt(letterPrice);
        return price;
    }


    public void decrementBookCount(String title) {
        for (int t = 0; t < stringTitles.length; t++) {
            if (stringTitles[t].equals(title)) {
                WebDriverWait wait = new WebDriverWait(driver, 100);
                wait.until(ExpectedConditions.elementToBeClickable(minusButtons.get(t)));
                minusButtons.get(t).click();
            }
        }
        refreshAll();
    }

    public void incrementBookCount(String title) {
        for (int t = 0; t < stringTitles.length; t++) {
            if (stringTitles[t].equals(title)) {
                WebDriverWait wait = new WebDriverWait(driver, 100);
                wait.until(ExpectedConditions.elementToBeClickable(plusButtons.get(t)));
                plusButtons.get(t).click();

            }
        }
        refreshAll();
    }

    public void deleteByTitle(String title) {
        for (int t = 0; t < stringTitles.length; t++) {
            if (stringTitles[t].equals(title)) {
                int index = t + 1;
                String xpath = "//*[@id=\"shopping-cart-table\"]/form/div[" + index + "]/div[2]/div[2]/a";
                driver.findElement(By.xpath(xpath)).click();
            }
        }
    }

    public void refreshAll() {

        for (WebElement element : refreshButtons) {
            element.click();
        }
    }
}

