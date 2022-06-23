package quru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ListenerSelenideTest {

    @Test
    void testGithubIssueSearch(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");

        $(".header-search-input").click();
        $(".header-search-input").sendKeys("eroshenkoam/allure-example");
        $(".header-search-input").submit();

        $(By.linkText("eroshenkoam/allure-example")).click();
        $(By.partialLinkText("Issues1")).click();
        $(withText("#76")).should(Condition.exist);


    }
}
