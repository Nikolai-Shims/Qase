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
    public CaseSteps validateThatCaseWasCreated(TestCase testCase, String caseName) {
        log.info("Validate data: " + testCase);
        testRepositoryPage
                .chooseCase(caseName);
        assertEquals(Select.getTextBySelect("Severity"), testCase.getSeverity());
        assertEquals(Select.getTextBySelect("Priority"), testCase.getPriority());
        assertEquals(Input.getTextByInput("Preconditions"), testCase.getPreConditions());
        assertEquals(Input.getTextByInput("Postconditions"), testCase.getPostConditions());
        assertEquals(Input.getTextByInput("Description"), testCase.getDescription());
        assertEquals(Select.getTextBySelect("Behavior"), testCase.getBehavior());
        assertEquals(Select.getTextBySelect("Type"), testCase.getType());
        assertEquals(Select.getTextBySelect("Status"), testCase.getStatus());
        assertEquals(Select.getTextBySelect("Automation"), testCase.getAutomationStatus());
        return this;
    }

    @Step("Edit existing 'Case'")
    public CaseSteps editCase(TestCase editCase, String caseName) {
        log.info(String.format("Edit existing case with data: %s by case name %s", editCase, caseName));
        testRepositoryPage
                .chooseCase(caseName)
                .editCase()
                .createNewCase(editCase)
                .saveCase();
        return this;
    }

    @Step("Validate that existing case was edited")
    public CaseSteps validateThatCaseWasEdited(TestCase editTestCase, String caseName) {
        log.info(String.format("Validate data: %s by case name %s", editTestCase, caseName));
        validateThatCaseWasCreated(editTestCase, caseName);
        return this;
    }

    @Step("Delete Case")
    public CaseSteps deleteCase(String caseName) {
        log.info("Delete Case by name: " + caseName);
        testRepositoryPage
                .chooseCase(caseName)
                .clickDeleteCase()
                .isPageOpened()
                .confirmDeletingCase();
        return this;
    }

}
