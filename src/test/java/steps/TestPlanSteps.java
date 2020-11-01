package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import pages.TestPlanPage;

import static org.testng.Assert.assertTrue;

@Log4j2
public class TestPlanSteps {

    TestPlanPage testPlanPage;

    public TestPlanSteps() {
        testPlanPage = new TestPlanPage();
    }

    @Step("Create new test plan")
    public TestPlanSteps createTestPlan(TestPlan testPlan) {
        log.info(String.format("Create new 'Test Plan' with data: %s", testPlan));
        testPlanPage
                .openPage()
                .isPageOpened()
                .createTestPlan()
                .fillTestPlan(testPlan)
                .addCases()
                .isPageOpened()
                .chooseCase()
                .confirmThatTestCaseWasChosen()
                .confirmTestPlan();
        return this;
    }

    @Step("Edit 'Test Plan'")
    public TestPlanSteps editTestPlan(String title, String option, TestPlan editTestPlan) {
        log.info(String.format("Edit existing 'Test Plan' with name %s ,and data: %s", title, editTestPlan));
        testPlanPage
                .editTestPlan(title, option)
                .fillTestPlan(editTestPlan)
                .confirmTestPlan();
        return this;
    }

    @Step("Delete 'Test Plan'")
    public TestPlanSteps deleteTestPlan(String editTitle, String option) {
        log.info(String.format("Delete 'Test Plan' by name: %s", editTitle));
        testPlanPage
                .deleteTestPlan(editTitle, option)
                .isPageOpened()
                .deleteTestPlan();
        return this;
    }

    @Step("Validate that 'Test plan' was created")
    public TestPlanSteps validateThatTestPlanWasCreated(String testPlanName) {
        log.info(String.format("Validate that 'Test Plan' with name: %s was created", testPlanName));
        assertTrue(testPlanPage.getTestPlanName(testPlanName));
        return this;
    }

    @Step("Validate that 'Test Plan' was edited")
    public TestPlanSteps validateThatTestPlanWasEdited(String editTestPlanName) {
        log.info(String.format("Validate that 'Test Plan' with name: %s was edited", editTestPlanName));
        validateThatTestPlanWasCreated(editTestPlanName);
        return this;
    }


}
