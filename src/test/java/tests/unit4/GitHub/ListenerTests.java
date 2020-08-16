package tests.unit4.GitHub;
import io.qameta.allure.*;
import io.qameta.allure.restassured.AllureRestAssured;
import main.ApiSteps;
import main.Issue;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static main.Config.config;
import static io.qameta.allure.Allure.link;
import static main.NamedBy.css;
import static main.NamedBy.named;
import static org.openqa.selenium.By.linkText;

@Owner("Taya")
@Feature("Тесты GitHub на чистом Selenide и Rest API")
public class ListenerTests {
    private static final String REPOSITORY = config().getRepository();
    @BeforeAll
    static void initLogger() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .savePageSource(true)
                .screenshots(true));
    }

    @DisplayName("Создание Issue без Steps с NamedBy")
    @Test
    public void creatingIssueTestWithNamedBy() {
        open(config().getLoginFormUrl());

        $(css("#login_field").as("Login field")).setValue(config().getLogin());
        $(css("#password").as("Password field")).setValue(config().getPassword());
        $(named(byName("commit")).as("Login button")).click();

        $(css(".header-search-input").as("Поисковая строка в заголовке")).setValue(REPOSITORY);
        $(css(".header-search-input").as("Поисковая строка в заголовке")).submit();

        $(named(linkText(REPOSITORY)).as("Ссылка на репозиторий")).click();

        $(css("a[href='/eroshenkoam/allure-example/issues']").as("Issues page")).click();

        $(css(".btn-primary > span").as("New issue button")).click();
        $("input[name='issue[title]']").setValue("Listeners NamedBy");
        $(named(withText("Submit new issue")).as("Submit new issue")).click();


        int issue = Integer.parseInt(($x("//span[contains(text(),'#')]").getText())
                .replace("#", ""));


        // Проверка, что issue создана
        given()
                .baseUri("https://api.github.com")
                .header("Authorization", "token " + config().getToken())
                .filter(new AllureRestAssured())
                .when()
                .get("/eroshenkoam/allure-example/issues/"+issue)
                .then()
                .log().body()
                .statusCode(200)
                .body("title", is(config().getTitle()));
        // @formatter:on
    }
}