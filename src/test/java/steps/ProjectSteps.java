package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.ProjectsPage;
import pages.TestRepositoryPage;

import static com.codeborne.selenide.Selenide.sleep;
import static org.testng.Assert.*;

@Log4j2
public class ProjectSteps {

    ProjectsPage projectsPage;
    TestRepositoryPage testRepositoryPage;

    public ProjectSteps() {
        projectsPage = new ProjectsPage();
        testRepositoryPage = new TestRepositoryPage();
    }

    @Step("Create new Project")
    public ProjectSteps createNewProject(String projectName, String description) {
        log.info("Project with name: " + projectName + ", and description: " + description + ", will be create");
        projectsPage
                .createNewProject()
                .isPageOpened()
                .createNewProject(projectName, description);
        return this;
    }

    @Step("Validate that project was created")
    public ProjectSteps validateThatProjectWasCreated(String nameOfProject) {
        log.info("Project with name: " + nameOfProject + ", must be create");
        projectsPage
                .openProjectPage()
                .isPageOpened();
        sleep(3000);
        assertTrue(projectsPage.getNamesOfProject(nameOfProject));
        return this;
    }

    @Step("Delete existing project and validate that project was deleted")
    public ProjectSteps deleteProject(String projectName) {
        log.info("Project with name: " + projectName + ", will delete");
        projectsPage
                .openProjectPage()
                .isPageOpened()
                .deleteProject(projectName)
                .isPageOpened()
                .openSettings()
                .deleteProject()
                .isPageOpened()
                .confirmDeletion()
                .isPageOpened();
        assertFalse(projectsPage.getNamesOfProject(projectName));
        return this;
    }
}
