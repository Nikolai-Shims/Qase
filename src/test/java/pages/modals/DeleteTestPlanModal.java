package pages.modals;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.TestPlanPage;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DeleteTestPlanModal extends BasePage {

    private static final String DELETE_BUTTON = ".btn-cancel";

    @Step("Confirm Deleting test plan")
    public TestPlanPage deleteTestPlan() {
        log.info("Confirm deleting test plan by locator: " + DELETE_BUTTON);
        $(DELETE_BUTTON).waitUntil(Condition.appear, timeout).click();
        return new TestPlanPage();
    }

    @Step("Validate that 'Delete test plan' modal window was opened")
    public DeleteTestPlanModal isPageOpened() {
        $(DELETE_BUTTON).shouldBe(Condition.visible);
        return this;
    }

}
