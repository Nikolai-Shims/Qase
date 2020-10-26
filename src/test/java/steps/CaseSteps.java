package steps;

import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import pages.EditCasePage;
import pages.TestRepositoryPage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CaseSteps {
    TestRepositoryPage testRepositoryPage;
    EditCasePage editCasePage;


    public CaseSteps() {
        testRepositoryPage = new TestRepositoryPage();
        editCasePage = new EditCasePage();
    }

    @Step("Create new Case")
    public CaseSteps createNewCase(TestCase testcase) {
        log.info("Create new Case with data: " + testcase);
        testRepositoryPage
                .openTestRepositoryPage()
                .isPageOpened()
                .createNewTestCase()
                .isPageOpened()
                .createNewCase(testcase)
                .saveCase();
        return this;
    }

    @Step("Validate that 'Case' was created")
    public CaseSteps validateThatCaseWasCreated(TestCase testCase) {
        log.info("Validate data: " + testCase);
        testRepositoryPage
                .chooseCase();
        assertEquals(Select.getTextBySelect("Severity"), testCase.getSeverity());
        assertEquals(Select.getTextBySelect("Priority"), testCase.getPriority());
        assertEquals(Input.getTextByInput("Preconditions"), testCase.getPreConditions());
        assertEquals(Input.getTextByInput("Description"), testCase.getDescription());
        assertEquals(Select.getTextBySelect("Behavior"), testCase.getBehavior());
        assertEquals(Select.getTextBySelect("Type"), testCase.getType());
        assertEquals(Select.getTextBySelect("Status"), testCase.getStatus());
        assertEquals(Select.getTextBySelect("Automation"), testCase.getAutomationStatus());
        return this;
    }

    @Step("Edit existing 'Case'")
    public CaseSteps editCase(TestCase editCase) {
        log.info("Edit existing case with data: " + editCase);
        testRepositoryPage
                .chooseCase()
                .editCase()
                .createNewCase(editCase)
                .saveCase();
        return this;
    }

    @Step("Validate that existing case was edited")
    public CaseSteps validateThatCaseWasEdited(TestCase editTestCase) {
        log.info("Validate data: " + editTestCase);
        validateThatCaseWasCreated(editTestCase);
        return this;
    }

    @Step("Delete Case")
    public CaseSteps deleteCase() {
        testRepositoryPage
                .chooseCase()
                .clickDeleteCase()
                .isPageOpened()
                .confirmDeletingCase();
        return this;
    }

}
