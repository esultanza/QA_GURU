package tests;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class Unit3AlfaTests {

    @BeforeEach
    void openMainPage() {
        open("https://alfabank.ru/");
    }

    @Test
        // Депозиты
    void depositTest() {
        $(byTitle("Вклады")).click();
        $(byTitle("Депозиты")).click();
        $(byText("Архивные депозиты")).click();
        // Должно отобразиться 3 архивных депозита
        $$(".product-cell__cell").shouldHave(CollectionCondition.size(3));
    }
    @Test
        // Страхование вкладов
    void strahovanie_vkladovTest() {
        $(byTitle("Вклады")).click();
        $(".navigation li").parent().shouldHave(text("Страхование вкладов"));
    }
}