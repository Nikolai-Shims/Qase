package tests;

import models.TestCase;
import org.testng.annotations.Test;

public class CaseTest extends BaseTest {

    String title = faker.name().username();
    String description = faker.name().username();
    String preCondition = faker.name().username();
    String postCondition = faker.name().username();

    String editTitle = faker.name().username();
    String editDescription = faker.name().username();
    String editPreCondition = faker.name().username();
    String editPostCondition = faker.name().username();


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

    @Test
    public void createEditAndDeleteCase() {
        loginSteps
                .login(USERNAME, PASSWORD);
        caseSteps
                .createNewCase(testCase)
                .validateThatCaseWasCreated(testCase)
                .editCase(editCase)
                .validateThatCaseWasEdited(editCase)
                .deleteCase();
    }
}
