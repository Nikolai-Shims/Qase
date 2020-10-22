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
                .isTestRepositoryOpened()
                .createSuite()
                .fillSuiteName(name)
                .fillDescription(description)
                .confirmSuite();
        return this;
    }

    @Step("Validate that 'Suite' was created")
    public SuiteSteps validateThatSuiteWasCreated(String projectName) {
        log.info("Validate that 'Suite' with name: " + projectName + ", was created");
        String nameOfSuite = testRepositoryPage.validateSuiteName();
        assertEquals(nameOfSuite, projectName);
        return this;
    }

    @Step("Delete existing suite")
    public SuiteSteps deleteSuite() {
        testRepositoryPage
                .deleteSuite()
                .isDeleteSuiteModalOpened()
                .confirmDeleteSuite();
        return this;
    }

    @Step("Edit existing suite")
    public SuiteSteps editExistingSuite(String editName, String editDescription) {
        testRepositoryPage
                .editSuite()
                .fillSuiteName(editName)
                .fillDescription(editDescription)
                .confirmSuite();
        return this;
    }

    @Step("Validate that 'Suite' was edited")
    public SuiteSteps validateThatSuiteWasEdited(String expectedName, String expectedDescription) {
        String actualEditedName = testRepositoryPage.validateSuiteName();
        String actualEditedDescription = testRepositoryPage.validateDescription();
        log.info("Actual Suite name: " + actualEditedName + ", actual suite description: " + actualEditedDescription);
        assertEquals(actualEditedName, expectedName);
        assertEquals(actualEditedDescription, expectedDescription);
        return this;
    }

}