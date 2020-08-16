package tests.unit4.GitHub;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import main.BaseSteps;
import org.junit.jupiter.api.Test;

@Owner("klyukina")
@Feature("Работа с задачами")

public class BaseStepTests {
    private final String USERNAME = System.getProperty("qaguru.username");
    private final String PASSWORD = System.getProperty("qaguru.password");

    private final String REPOSITORY = "eroshenkoam/allure-example";
    private final String ISSUE_TITLE = "с днем археолога!";

    BaseSteps steps = new BaseSteps();

    @Test
    public void testIssueCreate() {
        Configuration.baseUrl = "https://github.com";
        steps.loginAsUser(USERNAME, PASSWORD);
        steps.navigateToRepositoryIssues(REPOSITORY);
        steps.createNewIssueWithTitle(ISSUE_TITLE);
        steps.shouldSeeIssueWithTitle(ISSUE_TITLE);
    }
}
