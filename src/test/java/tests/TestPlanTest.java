package tests;

import models.TestPlan;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class TestPlanTest extends BaseTest {

    String title = faker.name().username();
    String description = faker.name().username();
    String editTitle = faker.name().username();
    String editDescription = faker.name().username();

    TestPlan testPlan = TestPlan.builder()
            .title(title)
            .description(description)
            .build();

    TestPlan editTestPlan = TestPlan.builder()
            .title(editTitle)
            .description(editDescription)
            .build();


    @Test(retryAnalyzer = Retry.class, description = "Create 'Test plan' and validate that it was created, edit existing 'Test Plan' then validate it, and delete 'Test Plan'  ")
    public void createEditDeleteTestPlan() {
        loginSteps
                .login(USERNAME, PASSWORD);
        testPlanSteps
                .createTestPlan(testPlan)
                .validateThatTestPlanWasCreated(title);
        testPlanSteps
                .editTestPlan(title, "Edit", editTestPlan)
                .validateThatTestPlanWasEdited(editTitle);
        testPlanSteps
                .deleteTestPlan(editTitle, "Delete");
    }
}
