package pages;

import com.codeborne.selenide.Condition;
import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage {

    private static final String BUTTON_SAVE = "//button[text()='Save']";

    @Step("Create new Case {testCase.title} ")
    public TestCasePage createNewCase(TestCase testCase) {
        log.info("Create new test Case by name: " + testCase.getTitle());
        new Input("Title").writeTitle(testCase.getTitle());
        new Input("Pre-conditions").write(testCase.getPreConditions());
        new Input("Post-conditions").write(testCase.getPostConditions());
        new Input("Description").write(testCase.getDescription());
        new Select("Status").select(testCase.getStatus());
        new Select("Severity").select(testCase.getSeverity());
        new Select("Priority").select(testCase.getPriority());
        new Select("Type").select(testCase.getType());
        new Select("Behavior").select(testCase.getBehavior());
        new Select("Automation status").select(testCase.getAutomationStatus());
        return this;
    }

    @Step("Click the button 'Save'")
    public TestRepositoryPage saveCase() {
        log.info("Click the button 'Save' by locator: " + BUTTON_SAVE);
        $(By.xpath(BUTTON_SAVE)).waitUntil(Condition.appear, timeout).click();
        return new TestRepositoryPage();
    }

    @Step("Validate that 'Test Case' page was opened")
    public TestCasePage isPageOpened() {
        $(By.xpath(BUTTON_SAVE)).shouldBe(Condition.visible);
        return this;
    }
}