package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import elements.Input;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestPlan;
import org.openqa.selenium.By;
import pages.modals.DeleteTestPlanModal;
import pages.modals.SelectTestCasesModal;


import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TestPlanPage extends BasePage {

    private static final String BUTTON_CREATE_NEW_TEST_PLAN = "//a[text()='Create test plan']";
    private static final String ADD_CASES = "//button[contains(@class,'btn-invisible')]/child::i";
    private static final String CONFIRM_SAVE = ".save-button";
    private static final String DROPDOWN = "//a[text()='%s']/following::i[contains(@class,'fa-ellipsis-h')]";
    private static final String OPTION = "//a[text()='%s']";
    private static final String TEST_PLAN_NAME = ".defect-title";
    private static final String URL = "https://app.qase.io/plan/QASEPROJEC";

    @Step("Open 'Test Plans' page")
    public TestPlanPage openPage() {
        log.info("Open 'Test Plans' page by URL: " + URL);
        open(URL);
        return this;
    }

    @Step("Create new 'Test plan' {testPlan.title}")
    public TestPlanPage fillTestPlan(TestPlan testPlan) {
        log.info("Create new test plan with data: " + testPlan);
        new Input("Title").writeTitle(testPlan.getTitle());
        new Input("Description").write(testPlan.getDescription());
        return this;
    }

    @Step("Click button 'Create new test plan'")
    public TestPlanPage createTestPlan() {
        log.info("Click button 'Create new test plan' by locator: " + BUTTON_CREATE_NEW_TEST_PLAN);
        $(By.xpath(BUTTON_CREATE_NEW_TEST_PLAN)).waitUntil(Condition.appear, timeout).click();
        return this;
    }

    @Step("Validate that 'Test Plan' page was opened")
    public TestPlanPage isPageOpened() {
        $(By.xpath(BUTTON_CREATE_NEW_TEST_PLAN)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Add cases for 'Test plan'")
    public SelectTestCasesModal addCases() {
        log.info("Add cases for 'Test plan' by locator: " + ADD_CASES);
        $(By.xpath(ADD_CASES)).waitUntil(Condition.appear, timeout).click();
        return new SelectTestCasesModal();
    }

    @Step("Click button 'Save'")
    public TestPlanPage confirmTestPlan() {
        log.info("Click button 'Save' by locator: " + CONFIRM_SAVE);
        $(CONFIRM_SAVE).waitUntil(Condition.appear, timeout).click();
        sleep(2000);
        return this;
    }

    @Step("Choose 'Test Plan' option")
    public TestPlanPage editTestPlan(String testPlanName, String option) {
        log.info(String.format("Choose 'Test plan'by name: %s click dropdown menu and choose option %s", testPlanName, option));
        $(By.xpath(String.format(DROPDOWN, testPlanName))).waitUntil(Condition.appear, timeout).click();
        $(By.xpath(String.format(OPTION, option))).waitUntil(Condition.appear, timeout).click();
        return this;
    }

    @Step("Delete 'Test Plan'")
    public DeleteTestPlanModal deleteTestPlan(String testPlanName, String option) {
        log.info("Delete 'Test Plan' with name " + testPlanName);
        editTestPlan(testPlanName, option);
        return new DeleteTestPlanModal();
    }

    @Step("Validate that 'Test Plan' was created")
    public boolean getTestPlanName(String testPlanName) {
        log.info("Validate that 'Test plan' with name " + testPlanName + ", was created");
        boolean value = false;
        ElementsCollection list = $$(TEST_PLAN_NAME);
        for (int a = 0; a < list.size(); a++) {
            if (testPlanName.equals(list.get(a).getText())) {
                value = true;
            }
        }
        return value;
    }

}
