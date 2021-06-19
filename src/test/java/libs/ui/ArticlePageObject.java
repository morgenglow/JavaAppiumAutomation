package libs.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject{
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void clickOnBookmarkButton() {
        this.waitForElementAndClick(
                By.id("org.wikipedia:id/article_menu_bookmark"),
                "Cannot find option to add article to reading list",
                8
        );
    }

    public MainPageObject clickOnExploreButton() {
        this.waitForElementAndClick(
                By.id("org.wikipedia:id/page_toolbar_button_show_overflow_menu"),
                "Cannot find explore button",
                8
        );
        this.waitForElementAndClick(
                By.id("org.wikipedia:id/overflow_feed"),
                "Cannot find explore button",
                8
        );
        return new MainPageObject(driver);
    }
}
