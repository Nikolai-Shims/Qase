package pages.modals;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.TestRepositoryPage;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DeleteSuiteModal extends BasePage {

    private static final String BUTTON_DELETE_SUITE = ".btn-danger";
    private static final String BUTTON_CANCEL = ".btn-invisible-danger";

    @Step("Click the button 'Delete suite'")
    public TestRepositoryPage confirmDeleteSuite() {
        log.info("Click button 'Delete suite' by locator: " + BUTTON_DELETE_SUITE);
        $(BUTTON_DELETE_SUITE).click();
        return new TestRepositoryPage();
    }

    @Step("Validate that 'Delete suite' modal window was opened")
    public DeleteSuiteModal isPageOpened() {
        $(BUTTON_CANCEL).shouldBe(Condition.visible);
        return this;
    }
}
