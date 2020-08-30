package tests.unit4.GitHub;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import main.Issue;
import org.junit.jupiter.api.Test;

import static main.Config.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiSteps {
    @Step ("Получаем задачу с GitHub")
    public void getIssue() {
        given()
                 .when()
                 .baseUri("https://github.com")
                 .header("Authorization", "token " + config().getToken())
                 .filter(new AllureRestAssured())
                 .log().uri()
                 .get("https://github.com/eroshenkoam/allure-example/issues/65/")
                 .then()
                 .statusCode(200)
                 .body("title", is(config().getTitle()));
    }
}
