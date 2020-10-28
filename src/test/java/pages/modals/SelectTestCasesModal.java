package pages.modals;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.TestPlanPage;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class SelectTestCasesModal extends BasePage {

    private static final String BUTTON_DONE = "//button[text()='Done']";
    private static final String CHECK_BOX = ".suite-title";
    private static final String SELECT_ALL = "//button[text()='Select all']";

    @Step("Validate that 'Select test case' modal was opened")
    public SelectTestCasesModal isPageOpened() {
        $(By.xpath(BUTTON_DONE)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Choose all test cases")
    public SelectTestCasesModal chooseCase() {
        log.info("Choose all cases by locator: " + SELECT_ALL);
        $(CHECK_BOX).waitUntil(Condition.appear, timeout).click();
        $(By.xpath(SELECT_ALL)).waitUntil(Condition.appear, timeout).click();
        return this;
    }

    @Step("Click button 'Done'")
    public TestPlanPage confirmThatTestCaseWasChosen() {
        log.info("Click button 'Done' by locator: " + BUTTON_DONE);
        $(By.xpath(BUTTON_DONE)).waitUntil(Condition.appear, timeout).click();
        return new TestPlanPage();
    }
}
