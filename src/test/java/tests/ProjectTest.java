package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class ProjectTest extends BaseTest {

    String name = faker.name().title();
    String description = faker.address().city();

    @Test(retryAnalyzer = Retry.class, description = "Create new project,validate that project was created,then delete project and validate that project was deleted")
    public void createAndDeleteProject() {
        loginSteps
                .login(USERNAME, PASSWORD);
        projectSteps
                .createNewProject(name, description)
                .validateThatProjectWasCreated(name)
                .deleteProject(name);
    }
}
