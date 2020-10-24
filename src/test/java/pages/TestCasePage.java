package pages;

import com.codeborne.selenide.Condition;
import elements.Input;
import elements.Select;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.TestCase;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class TestCasePage extends BasePage {

    private static final String BUTTON_SAVE = ".mr-3.save-button";

    @Step("Create new Case")
    public TestCasePage createNewCase(TestCase testCase) {
        new Input("Title").writeTitle(testCase.getTitle());
        new Select("Status").select(testCase.getStatus());
        new Input("Description").write(testCase.getDescription());
        new Select("Severity").select(testCase.getSeverity());
        new Select("Priority").select(testCase.getPriority());
        new Select("Type").select(testCase.getType());
        new Select("Behavior").select(testCase.getBehavior());
        new Select("Automation status").select(testCase.getAutomationStatus());
        new Input("Pre-conditions").write(testCase.getPreConditions());
        new Input("Post-conditions").write(testCase.getPostConditions());
        return this;
    }

    @Step("Click the button 'Save'")
    public TestRepositoryPage saveCase(){
        log.info("Click the button 'Save' by locator: " + BUTTON_SAVE);
            $(BUTTON_SAVE).waitUntil(Condition.appear,timeout).click();
        return new TestRepositoryPage();
    }

    @Step("Validate that 'Test Case' page was opened")
    public TestCasePage isPageOpened() {
        $(BUTTON_SAVE).shouldBe(Condition.visible);
        return this;
    }
}