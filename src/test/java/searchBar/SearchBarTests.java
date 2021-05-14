package searchBar;
import Pages.SearchResultPage;
import base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

public class SearchBarTests extends BaseTests {

//    @Parameters
//    public static Collection<Object[]> data() {
//        return Arrays.asList(new Object[][] {
//                { "sherlock"}, {"harry potter"} });
//    }
//
//    @Test
//    public void searchFromHomePageHappyPath(String searchValue){
//        SearchResultPage searchResultPage = homePage.clickSearch(searchValue);
//        Assert.assertTrue(searchResultPage.getTitles().contains(searchValue));
//    }
//

    @Test
    public void searchFromHomePage() {
        SearchResultPage searchResultPage = homePage.clickSearch("Անկումը");
        Assert.assertTrue(searchResultPage.getTitles().contains("Անկումը"));
    }

    @Test
    public void searchFromHomePageNonExisting() {
        SearchResultPage searchResultPage = homePage.clickSearch("aasdfghjk");
        Assert.assertEquals(searchResultPage.getHeader(), "Ապարդյուն որոնում");
    }

    @Test
    public void searchFromHomePageSigns() {
        SearchResultPage searchResultPage = homePage.clickSearch("///");
        Assert.assertEquals(searchResultPage.getHeader(), "Ապարդյուն որոնում");
    }

    @Test
    public void searchFromHomePageFailing() {
        SearchResultPage searchResultPage = homePage.clickSearch("' OR 1=1");
        Assert.assertEquals(searchResultPage.getHeader(), "Ապարդյուն որոնում");
    }

}
