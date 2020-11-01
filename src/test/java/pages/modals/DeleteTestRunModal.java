package pages.modals;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.BasePage;
import pages.TestRunPage;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class DeleteTestRunModal extends BasePage {

    private static final String DELETE_TEST_RUN = ".btn-cancel";

    @Step("Validate that 'Delete test run' modal window was opened")
    @Override
    public DeleteTestRunModal isPageOpened() {
        $(DELETE_TEST_RUN).shouldBe(Condition.visible);
        return this;
    }

    @Step("Click button 'Delete test Run'")
    public TestRunPage confirmDeletingTestRun() {
        log.info(String.format("Click button 'Delete test run' by locator: %s", DELETE_TEST_RUN));
        $(DELETE_TEST_RUN).waitUntil(Condition.appear, timeout).click();
        return new TestRunPage();
    }
}
