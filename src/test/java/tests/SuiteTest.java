package tests;

import org.testng.annotations.Test;
import tests.another.Retry;

public class SuiteTest extends BaseTest {

    String projectName = faker.name().username();
    String description = faker.name().lastName();
    String editProjectName = faker.name().username();
    String editDescription = faker.name().lastName();

    @Test(retryAnalyzer = Retry.class, description = "Create new suite,validate that suite was created. Edit existing suite, and validate that Suite was edited. Delete Suite and validate that suite was deleted")
    public void editedAndDeleteSuite() {
        loginSteps
                .login(USERNAME, PASSWORD);
        suiteSteps
                .createNewSuite(projectName, description)
                .validateThatSuiteWasCreated(projectName)
                .editExistingSuite(editProjectName, editDescription)
                .validateThatSuiteWasEdited(editProjectName, editDescription)
                .deleteSuite();
    }
}
