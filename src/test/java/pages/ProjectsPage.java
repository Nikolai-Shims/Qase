package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class ProjectsPage extends BasePage {

    private static final String URL = "https://app.qase.io/projects";
    private static final String CREATE_NEW_PROJECT_BUTTON = "#createButton";
    private static final String NAME_PROJECT = ".defect-title";
    private static final String CHOOSE_PROJECT_BY_NAME = "//a[contains(text(),'%s')]";

    @Step("Click button 'Create new project'")
    public NewProjectPage createNewProject() {
        log.info("Click button 'Create new project' by locator: " + CREATE_NEW_PROJECT_BUTTON);
        $(CREATE_NEW_PROJECT_BUTTON).waitUntil(Condition.appear, timeout).click();
        return new NewProjectPage();
    }

    @Override
    @Step("Validate that 'Project page' was opened")
    public ProjectsPage isPageOpened() {
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
        return this;
    }

    @Step("Open 'Project Page'")
    public ProjectsPage openProjectPage() {
        log.info("Open project page by URL: " + URL);
        open(URL);
        return this;
    }

    @Step("Gey list all projects")
    public boolean getNamesOfProject(String projectName) {
        boolean name = false;
        ElementsCollection allProjects = $$(NAME_PROJECT);
        log.info("Get list of project " + allProjects.size());
        for (int index = 0; index < allProjects.size(); index++) {
            allProjects.get(index).getText().contains(projectName);
            name = true;
        }
        return name;
    }

    @Step("Choose project and click")
    public TestRepositoryPage deleteProject(String projectName) {
        log.info("Project with name: " + projectName + ", will be delete");
        $(By.xpath(String.format(CHOOSE_PROJECT_BY_NAME, projectName))).waitUntil(Condition.appear, timeout).click();
        return new TestRepositoryPage();
    }
}


