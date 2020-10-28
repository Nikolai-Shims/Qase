package pages.modals;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.TestRepositoryPage;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DeleteCaseModal extends BasePage {

    private static final String BUTTON_DELETE = ".btn-danger";
    private static final String BUTTON_CANCEL = ".btn-invisible-danger";

    @Override
    @Step("Validate that 'Delete tes case' modal window was opened")
    public DeleteCaseModal isPageOpened() {
        $(BUTTON_CANCEL).shouldBe(Condition.visible);
        return this;
    }

    @Step("Confirm that test case should delete")
    public TestRepositoryPage confirmDeletingCase() {
        log.info("Click button 'Delete' by locator: " + BUTTON_DELETE);
        $(BUTTON_DELETE).waitUntil(Condition.appear, timeout).click();
        return new TestRepositoryPage();
    }
}
