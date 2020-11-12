package tests;

import models.TestCase;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class CaseTest extends BaseTest {

    TestCase testCase = TestCase.builder()
            .preConditions(preCondition)
            .description(description)
            .postConditions(postCondition)
            .title(title)
            .status("Actual")
            .severity("Blocker")
            .priority("High")
            .type("Other")
            .behavior("Not set")
            .automationStatus("Not automated")
            .build();

    TestCase editCase = TestCase.builder()
            .preConditions(editPreCondition)
            .description(editDescription)
            .postConditions(editPostCondition)
            .title(editTitle)
            .status("Deprecated")
            .severity("Trivial")
            .priority("Low")
            .type("Functional")
            .behavior("Destructive")
            .automationStatus("Automated")
            .build();

    @Test(retryAnalyzer = Retry.class, description = "Create new 'Case' and validate that case was created, edit existing 'Case',validate that case was edited, and delete 'Case'")
    public void createEditAndDeleteCase() {
        loginSteps
                .login(USERNAME, PASSWORD);
        caseSteps
                .createNewCase(testCase)
                .validateThatCaseWasCreated(testCase, title)
                .editCase(editCase, title)
                .validateThatCaseWasEdited(editCase, editTitle)
                .deleteCase(editTitle);
    }
}
