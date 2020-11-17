package tests;

import models.TestRun;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class TestRunTest extends BaseTest {

    public String title = faker.name().username();
    public String description = faker.chuckNorris().fact();
    public String editTitle = faker.name().username();
    public String editDescription = faker.name().username();

    TestRun testRun = TestRun.builder()
            .title(title)
            .description(description)
            .build();

    TestRun editTestRun = TestRun.builder()
            .title(editTitle)
            .description(editDescription)
            .build();

    @Test(retryAnalyzer = Retry.class, description = "Create 'test run'and validate that 'test run' was created, edit existing 'test run' and validate that 'test run' was edited then delete")
    public void createEditDeleteTestRun() {
        loginSteps
                .login(USERNAME, PASSWORD);
        testRunSteps
                .createNewTestRun(testRun)
                .validateThatTestRunWasCreated(title, title)
                .editTestRun(title, editTestRun)
                .validateThatTestRunWasEdited(editTitle, editTitle)
                .deleteTestRun(editTitle);

    }
}
