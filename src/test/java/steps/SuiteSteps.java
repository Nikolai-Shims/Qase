package steps;


import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.TestRepositoryPage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class SuiteSteps {

    TestRepositoryPage testRepositoryPage;

    public SuiteSteps() {
        testRepositoryPage = new TestRepositoryPage();
    }

    @Step("Create new Suite,fill the field name and description")
    public SuiteSteps createNewSuite(String name, String description) {
        testRepositoryPage
                .openTestRepositoryPage()
                .isPageOpened()
                .createSuite()
                .fillSuiteName(name)
                .fillDescription(description)
                .confirmSuite();
        return this;
    }

    @Step("Validate that 'Suite' was created")
    public SuiteSteps validateThatSuiteWasCreated(String suiteName) {
        log.info("Validate that 'Suite' with name: " + suiteName + ", was created");
        String nameOfSuite = testRepositoryPage.validateSuiteName(suiteName);
        assertEquals(nameOfSuite, suiteName);
        return this;
    }

    @Step("Delete existing suite")
    public SuiteSteps deleteSuite(String name) {
        testRepositoryPage
                .deleteSuite(name)
                .isPageOpened()
                .confirmDeleteSuite();
        return this;
    }

    @Step("Edit existing suite")
    public SuiteSteps editExistingSuite(String suiteName, String editName, String editDescription) {
        testRepositoryPage
                .editSuite(suiteName)
                .fillSuiteName(editName)
                .fillDescription(editDescription)
                .confirmSuite();
        return this;
    }

    @Step("Validate that 'Suite' was edited")
    public SuiteSteps validateThatSuiteWasEdited(String expectedName) {
        String actualEditedName = testRepositoryPage.validateSuiteName(expectedName);
        log.info("Actual Suite name: " + actualEditedName);
        assertEquals(actualEditedName, expectedName);
        return this;
    }

}