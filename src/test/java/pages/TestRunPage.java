package pages;

import com.codeborne.selenide.Condition;
import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestRun;
import org.openqa.selenium.By;
import pages.modals.DeleteTestRunModal;
import pages.modals.SelectTestCasesModal;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TestRunPage extends BasePage {

    private static final String CREATE_NEW_TEST_RUN = "//a[text()='Start new test run']/parent::div";
    private static final String URL = "https://app.qase.io/run/QASEPROJEC";
    private static final String ADD_CASES = "//button[contains(@class,'btn-invisible')]/child::i";
    private static final String SAVE_TEST_RUN = ".save-button";
    private static final String DROPDOWN = "//a[text()='%s']/following::i[contains(@class,'fa-ellipsis-h')]";
    private static final String OPTION = "//a[text()='%s']";
    private static final String TEST_RUN_NAME = "//a[text()='%s']";

    @Step("Open 'Test run' page")
    public TestRunPage openPage() {
        log.info(String.format("Open 'Test Run' page by link: %s", URL));
        open(URL);
        return this;
    }

    @Step("Validate that 'Test run' page was opened")
    @Override
    public TestRunPage isPageOpened() {
        $(By.xpath(CREATE_NEW_TEST_RUN)).shouldBe(visible);
        return this;
    }

    @Step("Click button 'Create new test run'")
    public TestRunPage createNewTestRun() {
        log.info(String.format("Click button 'Create new Test Run' by locator: %s", CREATE_NEW_TEST_RUN));
        $(By.xpath(CREATE_NEW_TEST_RUN)).waitUntil(Condition.appear, timeout).click();
        return this;
    }

    @Step("Fill new 'Test Run' {testRun.title}")
    public TestRunPage fillTestRun(TestRun testRun) {
        log.info(String.format("Fill 'Test run' with data: %s", testRun));
        fillEditTestRun(testRun);
        new Select("Default assignee").selectDefaultAssignee();
        return this;
    }

    @Step("Fill edit 'Test Run' {editTestRun.title}")
    public TestRunPage fillEditTestRun(TestRun editTestRun) {
        log.info(String.format("Fill 'Test run' with data: %s", editTestRun));
        new Input("Run title").writeTitle(editTestRun.getTitle());
        new Input("Description").write(editTestRun.getDescription());
        return this;
    }

    @Step("Save 'Test Run'")
    public TestRunPage saveTestRun() {
        log.info(String.format("Click button 'Start run' by locator: %s", SAVE_TEST_RUN));
        $(SAVE_TEST_RUN).waitUntil(Condition.appear, timeout).click();
        return this;
    }

    @Step("Add cases")
    public SelectTestCasesModal addCases() {
        log.info(String.format("Add cases by locator: %s", ADD_CASES));
        $(By.xpath(ADD_CASES)).waitUntil(Condition.appear, timeout).click();
        return new SelectTestCasesModal();
    }

    @Step("Choose 'Test Run' option")
    public TestRunPage editTestPlan(String testRunName, String option) {
        log.info(String.format("Choose 'Test Run' by name: %s click dropdown menu and choose option %s", testRunName, option));
        $(By.xpath(String.format(DROPDOWN, testRunName))).waitUntil(Condition.appear, timeout).click();
        $(By.xpath(String.format(OPTION, option))).waitUntil(Condition.appear, timeout).click();
        return this;
    }

    @Step("Delete 'Test Run' ")
    public DeleteTestRunModal deleteTestRun(String testRunName, String option) {
        log.info(String.format("Delete 'Test Run' by name: %s", testRunName));
        editTestPlan(testRunName, option);
        return new DeleteTestRunModal();
    }

    @Step("Validate that 'Test Plan' was created")
    public String getTestRunName(String testPlanName) {
        log.info(String.format("Validate that 'Test Run' with %s was created", testPlanName));
        return $(By.xpath(String.format(TEST_RUN_NAME, testPlanName))).shouldBe(visible).getText();
    }

}
