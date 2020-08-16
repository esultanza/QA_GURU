package tests.unit4.GitHub;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;


public class LambdaStepTests {

    private final String USERNAME = System.getProperty("qaguru.username");
    private final String PASSWORD = System.getProperty("qaguru.password");

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final String ISSUE_TITLE = "новая Issue";


    @Test
    public void testIssueCreate() {
        Configuration.baseUrl = "https://github.com";
        step("Авторизуемся как пользователь " + USERNAME, () -> {
            open("/login");
            $("input[name='login']").sendKeys(USERNAME);
            $("input[name='password']").sendKeys(PASSWORD);
            $("input[value='Sign in']").click();
        });

//        step("Авторизуемся как пользователь " + USERNAME, () -> {
//            open("/login");
//            $(byName("login")).setValue(USERNAME);
//            $(byName("password")).setValue(PASSWORD).pressEnter();
//        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            open(String.format("/%s/issues", REPOSITORY));
//            open("/" + REPOSITORY + "/issues");
        });

        step("Создаем новую Issue с тайтлом " + ISSUE_TITLE, () -> {
            $("a.btn-primary").click();
            $("input[name='issue[title]']").sendKeys(ISSUE_TITLE);
            $("#new_issue").submit();
        });

        step("Проверяем что создали Issue с тайтлом " + ISSUE_TITLE, () -> {
            $("span.js-issue-title").shouldHave(text(ISSUE_TITLE));

        });

    }

}