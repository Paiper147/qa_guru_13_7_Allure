package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;


@Owner("vasilevskaya")
@Feature("Страница Issues")
public class IssueTest {

    private final String URL_REPOSITORY = "https://github.com/eroshenkoam/allure-example";
    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final String ISSUE_HEADER = "С новым годом";
    private final Integer ISSUE_NUMBER = 76;

    SelenideElement issues = $("#issues-tab");
    SelenideElement issueNumberSelector = $("#issue_" + ISSUE_NUMBER);


    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        Allure.getLifecycle().addAttachment("Screenshot", "image/png", "png",
                ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES)
        );
    }

    @Test
    @Story("Проверка название конкретного Issue")
    @DisplayName("Selenide test")
    @Description("Чистый Selenide")
    @Severity(SeverityLevel.CRITICAL)
    void selenideTest() {
        Selenide.open(URL_REPOSITORY);
        issues.shouldBe(Condition.visible).click();
        issueNumberSelector.shouldBe(Condition.visible).shouldHave(Condition.text(ISSUE_HEADER));
    }


    @Test
    @Story("Проверка название конкретного Issue")
    @DisplayName("Lambda test")
    @Description("Разметка сценария. Использование \"лямбда степов\"")
    @Severity(SeverityLevel.MINOR)
    void lambdaTest() {
        step("Открываем страницу репозитория " + REPOSITORY, () -> {
            Selenide.open(URL_REPOSITORY);
        });
        step("Открываем страницу Issues", () -> {
            issues.shouldBe(Condition.visible).click();
        });
        step("Проверяем, что Issue с номером " + ISSUE_NUMBER + " имеет название \"" + ISSUE_HEADER + "\"", () -> {
            issueNumberSelector.shouldBe(Condition.visible).shouldHave(Condition.text(ISSUE_HEADER));
        });
    }

    @Test
    @Story("Проверка название конкретного Issue")
    @DisplayName("Step test")
    @Description("Разметка сценария. Использование \"степов с аннотациями\"")
    @Severity(SeverityLevel.TRIVIAL)
    void stepTest() {
        Steps steps = new Steps();

        steps.openRepositoryPage(URL_REPOSITORY);
        steps.openIssuePage(issues);
        steps.checkIssueHeader(issueNumberSelector, ISSUE_NUMBER, ISSUE_HEADER);
    }
}
