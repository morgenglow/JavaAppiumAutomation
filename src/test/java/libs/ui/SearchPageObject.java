package libs.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_CONTAINER = "org.wikipedia:id/search_container",
            SEARCH_INPUT = "org.wikipedia:id/search_src_text",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@text='{SUBSTRING}']",
            SEARCH_EMPTY_RESULT_ELEMENT =  "//*[@text='No results found']",
            SEARCH_RESULT_BY_ID = "org.wikipedia:id/page_list_item_title";
//          SEARCH_BY_TITLE_AND_DESCRIPTION = "//*[contains(@class,'android.widget.LinearLayout')]//*[contains(@text,'{TITLE_OR_DESCRIPTION})']";
    public void initSearch(){
        this.waitForElementAndClick(
                By.id(SEARCH_CONTAINER),
                "Cannot find element by id",
                10);
        this.waitForElement(
                By.id(SEARCH_INPUT),
                "Can't find search input line 1st time");
    }

    public void typeSearchLine (String search_line){
        this.waitForElementAndSendKeys(
                By.id(SEARCH_INPUT),
                search_line,
                "Can't type into search input",
                6);
    }

    public void closeSearchButton (){
        this.waitForElementAndClick(
                       By.id("org.wikipedia:id/search_close_btn"),
                       "Can't find close button",
                       5
               );
    }

    public void confirmCloseSearchButton (){
        this.waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Close button is still here, can't close",
                5
        );
    }

    public void clickSearchResult (String substring){
        this.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Can't find close button",
                5
        );
    }


    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void waitForSearchResult (String substring){
        String search_result=getResultSearchElement(substring);
        this.waitForElement(
                By.xpath(search_result),
                "Can't find search result");
    }

    public void clickOnSearchResult (String substring){
        String search_result=getResultSearchElement(substring);
        this.waitForElementAndClick(
                By.xpath(search_result),
                "Can't click on explore button",
                5);
    }
}
