package main;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BaseSteps {

    @Step("Авторизуемся как пользователь")
    public void loginAsUser(final String username, final String password) {
        open("/login");
        $("input[name='login']").sendKeys(username);
        $("input[name='password']").sendKeys(password);
        $("input[value='Sign in']").click();
    }

    @Step("Переходим в репозиторий")
    public void navigateToRepositoryIssues(final String repository) {
        open(String.format("/%s/issues", repository));
    }

    @Step("Создаем новую Issue с тайтлом")
    public void createNewIssueWithTitle(final String title) {
        $("a.btn-primary").click();
        $("input[name='issue[title]']").sendKeys(title);
        $("#new_issue").submit();
    }

    @Step("Проверяем что создали Issue с тайтлом")
    public void shouldSeeIssueWithTitle(final String title) {
        $("span.js-issue-title").should(Condition.text(title));
    }

}