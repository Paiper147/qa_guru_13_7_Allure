package quru.qa.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LabelsTest {

    @Test
    @Owner("vasilevskaya")
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Работа с лейблами")
    @Story("Над тестом можно проставить статические лейблы")
    @DisplayName("Самый прекрасный тест")
    @Description("Этот тест проверяет очень важную функциональность")
    @Link(name = "GitHub", url = "https://github.com")
    public void testStaticLabels(){

    }
    @Test
    public void testDynamicLabels(){
        Allure.label("owner", "vasilevskaya");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.feature("Работа с лейблами");
        Allure.story("Внутри теста можно выставлять динамические лейблы");
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setName("Не самый прекрасный тест"));
        Allure.getLifecycle().updateTestCase(testResult -> testResult.setDescription("Этот тест проверяет очень важную функциональность"));
        Allure.link("Github", "https://github.com");
    }
    @Test
    public void testParameters(){
        Allure.parameter("Город", "Москва");
        Allure.parameter("Область", "Московская");
    }
}
