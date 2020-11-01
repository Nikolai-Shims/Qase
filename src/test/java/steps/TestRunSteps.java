package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import pages.TestRunPage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class TestRunSteps {

    TestRunPage testRunPage;

    public TestRunSteps() {
        testRunPage = new TestRunPage();
    }

    @Step("Create new 'Test Run'")
    public TestRunSteps createNewTestRun(TestRun testRun) {
        log.info(String.format("Create new test run with data: %s", testRun));
        testRunPage
                .openPage()
                .isPageOpened()
                .createNewTestRun()
                .fillTestRun(testRun)
                .addCases()
                .isPageOpened()
                .chooseCase()
                .confirmThatTestCaseWasChosen();
        testRunPage
                .saveTestRun();
        return this;
    }

    @Step("Delete 'Test Run'")
    public TestRunSteps deleteTestRun(String testRunName) {
        log.info(String.format("Delete 'Test Run' by name: %s", testRunName));
        testRunPage
                .deleteTestRun(testRunName, "Delete")
                .isPageOpened()
                .confirmDeletingTestRun();
        return this;
    }

    @Step("Edit 'Test Run'")
    public TestRunSteps editTestRun(String testRunName, TestRun editTestRun) {
        log.info(String.format("Choose 'Test Run' by name %s, edit this 'Test run' with data %s", testRunName, editTestRun));
        testRunPage
                .editTestPlan(testRunName, "Edit run")
                .fillEditTestRun(editTestRun)
                .saveTestRun();
        return this;
    }

    @Step("Validate that 'Test Run' was created")
    public TestRunSteps validateThatTestRunWasCreated(String actualName, String expected) {
        log.info(String.format("Validate that 'Test Run' with name: %s was created", actualName));
        assertEquals(testRunPage.getTestRunName(actualName), expected);
        return this;
    }

    @Step("Validate that 'Test Run' was edited")
    public TestRunSteps validateThatTestRunWasEdited(String actualName, String expected) {
        log.info(String.format("Validate that 'Test Run' with name: %s was edited", actualName));
        assertEquals(testRunPage.getTestRunName(actualName), expected);
        return this;
    }


}
