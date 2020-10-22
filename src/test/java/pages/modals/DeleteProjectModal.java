package pages.modals;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.ProjectsPage;

import static com.codeborne.selenide.Selenide.$;
@Log4j2
public class DeleteProjectModal extends BasePage {

    private static final String BUTTON_CANCEL = ".btn-back";
    private static final String BUTTON_DELETE_PROJECT = ".btn-cancel";

    @Step("Validate that 'Delete Project' modal window was opened")
    public DeleteProjectModal isDeleteProjectModalOpened() {
        $(BUTTON_CANCEL).shouldBe(Condition.visible);
        return this;
    }

    @Step("Click button 'Delete project'")
    public ProjectsPage confirmDeletion() {
        log.info("Click button 'Delete project' by locator: " + BUTTON_DELETE_PROJECT);
        $(BUTTON_DELETE_PROJECT).click();
        return new ProjectsPage();
    }
}
