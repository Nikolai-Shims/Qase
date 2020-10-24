package steps;

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
    public CaseSteps validateThatCaseWasCreated(String severity, String priority, String preCondition, String description, String behavior, String type, String status, String automation) {
        log.info(String.format("Validate data: %s,%s,%s,%s,%s,%s,%s,%s", severity, priority, preCondition, description, behavior, type, status, automation));
        testRepositoryPage
                .chooseCase();
        assertEquals(editCasePage.getTextBySelect("Severity"), severity);
        assertEquals(editCasePage.getTextBySelect("Priority"), priority);
        assertEquals(editCasePage.getTextByInput("Preconditions"), preCondition);
        assertEquals(editCasePage.getTextByInput("Description"), description);
        assertEquals(editCasePage.getTextBySelect("Behavior"), behavior);
        assertEquals(editCasePage.getTextBySelect("Type"), type);
        assertEquals(editCasePage.getTextBySelect("Status"), status);
        assertEquals(editCasePage.getTextBySelect("Automation"), automation);
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
    public CaseSteps validateThatCaseWasEdited(String editSeverity, String editPriority, String editPreCondition, String editDescription, String editBehavior, String editType, String editStatus, String editAutomation) {
        log.info(String.format("Validate data: %s,%s,%s,%s,%s,%s,%s,%s", editSeverity, editPriority, editPreCondition, editDescription, editBehavior, editType, editStatus, editAutomation));
        validateThatCaseWasCreated(editSeverity, editPriority, editPreCondition, editDescription, editBehavior, editType, editStatus, editAutomation);
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
