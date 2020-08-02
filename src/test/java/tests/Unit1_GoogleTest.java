package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class Unit1_GoogleTest {
    @Test
    void selenideSearchTest() {
        // Открыть google
        open("https://google.com");

        // Ввести Sci-fi в поиск
        $(byName("q")).setValue("Sci-fi").pressEnter();

        // Проверить, что syfy.com появился в результатах поиска
        $("html").shouldHave(text("syfy.com"));
    }
}
