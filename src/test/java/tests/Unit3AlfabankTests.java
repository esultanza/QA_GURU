package tests;

import org.junit.jupiter.api.*;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class Unit3AlfabankTests {

    @BeforeAll
    static void openMainPage() {
        open("https://alfabank.ru");
    }

    @Test
    @DisplayName("Депозиты")    
    void depositsSizeTest() {
        open("");
        
        $(byTitle("Вклады")).click();
        $(byTitle("Депозиты")).click();
        $(byText("Архивные депозиты")).click();
        
        // Должно отобразиться 3 архивных депозита
        $$(".product-cell__cell").shouldHave(size(3));
    }
    
    @Test
    @DisplayName("Страхование вкладов")
    void strahovanie_vkladovTest() {
        open("");
        
        $(byTitle("Вклады")).click();
        
        $(".navigation li").parent().shouldHave(text("Страхование вкладов"));
    }
}
