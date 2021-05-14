package extendedSearchPageTests;

import Pages.ExtendedSearchPage;
import Pages.SearchResultPage;
import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtendedSearchPageTests extends BaseTests {
    @Test
    //տիտլե, իսբն
    public void searchByISBN() {
        ExtendedSearchPage searchPage = homePage.clickExtendedSearch();
        searchPage.enterBookISBN("978-9939-66-279-4");
        SearchResultPage resultPage = searchPage.searchForValue();
        Assert.assertTrue(resultPage.getTitles().contains("Ռեքվիեմ Հուդայի համար"));

    }

    @Test
    public void searchByISBNonExisting() {
        ExtendedSearchPage searchPage = homePage.clickExtendedSearch();
        searchPage.enterBookISBN("111-1119-66-111");
        SearchResultPage resultPage = searchPage.searchForValue();
        Assert.assertEquals(resultPage.getHeader(), "Ապարդյուն որոնում");

    }

    @Test
    public void searchByISBNLetters() {
        ExtendedSearchPage searchPage = homePage.clickExtendedSearch();
        searchPage.enterBookISBN("eajheafkh");
        SearchResultPage resultPage = searchPage.searchForValue();
        Assert.assertEquals(resultPage.getHeader(), "Ապարդյուն որոնում");

    }

    @Test
    public void searchByISBNSigns() {
        ExtendedSearchPage searchPage = homePage.clickExtendedSearch();
        searchPage.enterBookISBN("?.'");
        SearchResultPage resultPage = searchPage.searchForValue();
        Assert.assertEquals(resultPage.getHeader(), "Ապարդյուն որոնում");

    }
}
