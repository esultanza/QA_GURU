package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

class GoogleTests extends TestBase {
    @Test
    void selenideSearchTest() {
        addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        // Открыть google
        open("https://google.com");

        // Ввести Sci-fi в поиск
        $(byName("q")).setValue("Sci-fi").pressEnter();

        // Проверить, что сайт syfy.com появился в результатах поиска
        $("html").shouldHave(text("syfy.com"));
    }

    @Test
    void negativeSelenideSearchTest() {

        // Открыть google
        open("https://google.com");

        // Ввести Selenide в поиск
        $(byName("q")).setValue("Selenide").pressEnter(); // кнопка логина

        // Проверить, что Selenide появился в результатах поиска
        $(".search-results").shouldHave(text("selenide.org"));
    }

    @Test
    void Jenkins404Test() {
        open("https://jenkins.autotests.cloud/view/QA.GURU%20examples/job/jenkins_tests/ws/build/");
    }
}