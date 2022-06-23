package quru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepAndAnnotatedStepsTest {

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final Integer ISSUE_NUMBER = 76;

    @Test
    void testLambdaSteps() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Ищем репозиторий по имени " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("В результатах поиска переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText(REPOSITORY)).click();
        });
        step("Открываем таб Issues", () -> {
            $(By.partialLinkText("Issues1")).click();
        });
        step("Проверяем, что существует Issue с номером " + ISSUE_NUMBER, () -> {
            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        });
    }


    @Test
    void testAnnotatedSteps(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebAnnotatedSteps webAnnotatedSteps = new WebAnnotatedSteps();

        webAnnotatedSteps.openMainPage();
        webAnnotatedSteps.searchForRepository(REPOSITORY);
        webAnnotatedSteps.openRepositoryLink(REPOSITORY);
        webAnnotatedSteps.openIssueTab();
        webAnnotatedSteps.shouldSeeIssueWithNumber(ISSUE_NUMBER);
    }
}
