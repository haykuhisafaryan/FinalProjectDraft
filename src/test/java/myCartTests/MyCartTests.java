package myCartTests;

import Pages.BookPage;
import Pages.ExtendedSearchPage;
import Pages.MyCartPage;
import Pages.SearchResultPage;
import base.BaseTests;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyCartTests extends BaseTests {


    @Test
    public void addToCart() {
        SearchResultPage searchResultPage = homePage.clickSearch("Կախվածություն");
        BookPage book = searchResultPage.selectABookByTitle("Կախվածություն");
        Assert.assertEquals(book.addToMyCart(), "Կախվածություն Ավելացվել է զամբյուղում");
    }

    @Test
    public void incrementBookCountTest() {
        int sum = 0;
        SearchResultPage searchResultPage = homePage.clickSearch("Harry Potter");
        BookPage bookOne = searchResultPage.selectABookByTitle("Harry Potter Half Blood Prince");
        bookOne.addToMyCart();
        sum += bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Harry Potter and the Deathly Hallows");
        bookTwo.addToMyCart();
        sum += bookTwo.getBookPrice();
        BookPage bookThree = bookTwo.backToSearchResults().selectABookByTitle("Harry Potter and the Prisoner of Azkaban");
        bookThree.addToMyCart();
        sum += bookThree.getBookPrice();
        MyCartPage myCartPage = bookThree.goToMyCart();
        myCartPage.incrementBookCount("Harry Potter Half Blood Prince");
        sum += bookTwo.getBookPrice();
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }

    @Test
    public void decrementBookCount() {
        int sum = 0;
        SearchResultPage searchResultPage = homePage.clickSearch("Harry Potter");
        BookPage bookOne = searchResultPage.selectABookByTitle("Harry Potter Half Blood Prince");
        bookOne.addToMyCart();
        sum += bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Harry Potter and the Deathly Hallows");
        bookTwo.addToMyCart();
        sum += bookTwo.getBookPrice();
        MyCartPage myCartPage = bookTwo.goToMyCart();
        myCartPage.incrementBookCount("Harry Potter Half Blood Prince");
    }


    @Test
    public void removeFromCart() {
        int sum = 0;
        SearchResultPage searchResultPage = homePage.clickSearch("Sherlock");
        BookPage bookOne = searchResultPage.selectABookByTitle("Adventures Of Sherlock Holmes, T");
        bookOne.addToMyCart();
        sum = sum + bookOne.getBookPrice();
        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Sherlock Holmes. Gods of War");
        bookTwo.addToMyCart();
        sum = sum + bookTwo.getBookPrice();
        MyCartPage myCartPage = bookTwo.goToMyCart();
        sum = sum - bookOne.getBookPrice();
        myCartPage.deleteByTitle("Sherlock Holmes. Gods of War");
        Assert.assertEquals(myCartPage.getTotalPrice(), sum);

    }

//    @Test
//    public void test() throws InterruptedException {
//        int sum = 0;
//        SearchResultPage searchResultPage = homePage.clickSearch("Ռեմարկ");
//        BookPage bookOne = searchResultPage.selectABookByTitle("Ապրելու ժամանակը և մեռնելու ժամանակը");
//        bookOne.addToMyCart();
//        BookPage bookTwo = bookOne.backToSearchResults().selectABookByTitle("Սև կոթողը");
//        bookTwo.addToMyCart();
//        MyCartPage myCartPage = bookTwo.goToMyCart();
//        myCartPage.incrementBookCount("Սև կոթողը");
////        myCartPage.driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/form/div[1]/div[2]/div[1]/div/a[2]")).click();
////        myCartPage.driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/form/div[1]/div[2]/div[2]/button")).click();
////        myCartPage.driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/form/div[2]/div[2]/div[1]/div/a[2]")).click();
////        myCartPage.driver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/form/div[2]/div[2]/div[2]/button")).click();
//       // myCartPage.incrementBookCount("Սև կոթողը");
//       // myCartPage.refreshAll();
//       // Assert.assertEquals(myCartPage.getTotalPrice(), sum);
//    }

}
