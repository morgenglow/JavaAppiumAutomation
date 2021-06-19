import libs.BaseTest;
import libs.ui.ArticlePageObject;
import libs.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class MainActivityTest extends BaseTest {
    @Test
    public void testBasicSearch(){
        mainPageObject.skipScreen();
        SearchPageObject searchPage = new SearchPageObject(driver);
        searchPage.initSearch();
        searchPage.typeSearchLine("Java");
        searchPage.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testSearchExit(){
        mainPageObject.skipScreen();
        SearchPageObject searchPage = new SearchPageObject(driver);
        searchPage.initSearch();
        searchPage.typeSearchLine("Java");
        searchPage.closeSearchButton();
        searchPage.confirmCloseSearchButton();
    }


    @Test
    public void testEx5()
    {
        mainPageObject.skipScreen();
        SearchPageObject searchPage = new SearchPageObject(driver);
        searchPage.initSearch();
        searchPage.typeSearchLine("Java");
        searchPage.clickOnSearchResult("Object-oriented programming language");
        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.clickOnBookmarkButton();
        articlePageObject.clickOnExploreButton();
        mainPageObject.clickOnBookmarks();


        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Cannot saved bookmarks list",
                5
        );

        mainPageObject.swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        mainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='Java (programming language)']"),
                "Java is not deleted",
                5
        );
    }

//    Написать тест, который открывает статью и убеждается, что у нее есть элемент title.
//        Важно: тест не должен дожидаться появления title, проверка должна производиться сразу.
//        Если title не найден - тест падает с ошибкой. Метод можно назвать assertElementPresent.
    @Test
    public void testFindTitle() {

        String article_name = "Java (programming language)";
        mainPageObject.skipScreen();
        SearchPageObject searchPage = new SearchPageObject(driver);
        searchPage.initSearch();
        searchPage.typeSearchLine("Java");
        searchPage.clickSearchResult("Java (programming language)");
        mainPageObject.assertElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_title_text']")
        );
    }

    @Test
    public void testArticleToBackground() {
        String article_name = "Java (programming language)";

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find onboarding skip button",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+article_name+"']"),
                "Cannot find search result",
                5
        );

        mainPageObject.waitForElement(
                By.xpath("//*[@text='"+article_name+"']"),
                "Cannot find title"
        );

        driver.runAppInBackground(3);

        mainPageObject.waitForElement(
                By.xpath("//*[@text='"+article_name+"']"),
                "Cannot find title after return from background"
        );
    }

    @Test
    public void testArticleRotate() {
        String article_name = "Java (programming language)";

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_onboarding_skip_button"),
                "Cannot find onboarding skip button",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        mainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        mainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+article_name+"']"),
                "Cannot find search result",
                5
        );

        mainPageObject.waitForElement(
                By.xpath("//*[@text='"+article_name+"']"),
                "Cannot find title"
        );

        mainPageObject.rotateScreenLandscape();

        mainPageObject.waitForElement(
                By.xpath("//*[@text='"+article_name+"']"),
                "Cannot find title after return from background"
        );

    }
}
