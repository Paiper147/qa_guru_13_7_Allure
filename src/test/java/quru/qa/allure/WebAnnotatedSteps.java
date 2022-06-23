package quru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebAnnotatedSteps {

    @Step("Открываем главную страницу")
    public void openMainPage(){
        open("https://github.com/");
    }

    @Step("Ищем репозиторий по имени {repository}")
    public  void searchForRepository(String repository){
        $(".header-search-input").click();
        $(".header-search-input").sendKeys(repository);
        $(".header-search-input").submit();
    }

    @Step("В результатах поиска переходим по ссылке репозитория {repository}")
    public void openRepositoryLink(String repository) {
        $(By.linkText(repository)).click();
    }

    @Step("Открываем таб Issues")
    public void openIssueTab() {
        $(By.partialLinkText("Issues")).click();
    }

    @Step("Проверяем, что существует Issue с номером {issueNumber}")
    public void shouldSeeIssueWithNumber(Integer issueNumber) {
        $(withText("#" + issueNumber)).should(Condition.exist);
    }
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
