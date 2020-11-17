package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class SuiteTest extends BaseTest {

    public String title = faker.name().name();
    public String description = faker.chuckNorris().fact();
    public String editDescription = faker.name().username();
    public String suiteName = faker.name().username();
    public String editSuiteName = faker.name().username();

    @Test(retryAnalyzer = Retry.class, description = "Create new suite,validate that suite was created. Edit existing suite, and validate that Suite was edited. Delete Suite and validate that suite was deleted")
    public void editedAndDeleteSuite() {
        loginSteps
                .login(USERNAME, PASSWORD);
        suiteSteps
                .createNewSuite(suiteName, description)
                .validateThatSuiteWasCreated(suiteName)
                .editExistingSuite(suiteName, editSuiteName, editDescription)
                .validateThatSuiteWasEdited(editSuiteName)
                .deleteSuite(editSuiteName);
    }
}
