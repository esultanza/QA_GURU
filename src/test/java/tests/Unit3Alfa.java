package tests;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class Unit3AlfaTests {

    @Test
        // Депозиты
    void depositTest() {
        open("https://alfabank.ru/make-money/savings-account/");
        $(byTitle("Депозиты")).click();
        $(byLinkText("Архивные депозиты")).click();

        // Должно отобразиться 3 архивных депозита
        $$(".product-cell__cell").shouldHave(CollectionCondition.size(3));
    }
    @Test
        // Страхование вкладов
    void strahovanie_vkladovTest() {
        open("https://alfabank.ru/make-money/savings-account/");
        $(".navigation li").parent().shouldHave(text("Страхование вкладов"));
    }
}