package main;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static main.Config.config;
import static io.restassured.RestAssured.given;

public class ApiSteps {
    @Step("Создаем задачу с заданным заголовком")
    public Issue createIssue(String title) {
        final Issue toCreate = new Issue();
        toCreate.setTitle(title);

        return given()
                .filter(new AllureRestAssured())
                .baseUri("https://api.github.com")
                .header("Authorization", "token " + config().getToken())
                .body(title)
                .when()
                .post("/repos/eroshenkoam/allure-example/issues")
                .then()
                .statusCode(201)
                .extract()
                .as(Issue.class);
    }

    @Step("Получаем задачу с GitHub")
    public Issue getIssue(int number) {
        return given()
                .filter(new AllureRestAssured())
                .baseUri("https://api.github.com")
                .header("Authorization", "token " + config().getToken())
                .when()
                .get(config().getRepository() + number)
                .then()
                .statusCode(200)
                .extract()
                .as(Issue.class);
    }
}
