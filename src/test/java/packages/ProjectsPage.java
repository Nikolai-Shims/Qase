package packages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class ProjectsPage extends BasePage{

    private static final String CREATE_NEW_PROJECT_BUTTON = "#createButton";
    private static final String SEARCH_FIELD = ".form-control";

    @Step("Click button 'Create new project'")
    public NewProjectPage createNewProject(){
        log.info("Click button 'Create new project' by locator: " + CREATE_NEW_PROJECT_BUTTON);
        $(CREATE_NEW_PROJECT_BUTTON).waitUntil(Condition.appear,timeout).click();
        return new NewProjectPage();
    }

    @Step("Validate that 'Project page' was open")
    public ProjectsPage isProjectPageOpened(){
        $(CREATE_NEW_PROJECT_BUTTON).shouldBe(Condition.visible);
        return this;
    }
}
