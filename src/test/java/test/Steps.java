package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Steps {

    @Step("Открываем страницу репозитория {urlRepository}")
    public void openRepositoryPage(String urlRepository) {
        Selenide.open(urlRepository);
    }

    @Step("Открываем страницу Issues")
    public void openIssuePage(SelenideElement issuesSelector) {
        issuesSelector.shouldBe(Condition.visible).click();
        takeScreenshot();
    }

    @Step("Проверяем, что Issue с номером {issueNumber} имеет название \"{issueHeader}\"")
    public void checkIssueHeader(SelenideElement issueNumberSelector, Integer issueNumber, String issueHeader) {
        issueNumberSelector.shouldBe(Condition.visible).shouldHave(Condition.text(issueHeader));
    }

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }


}
