package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class SuitePage extends BasePage {

    private static final String SUITE_NAME = "input#inputTitle";
    private static final String DESCRIPTION = ".CodeMirror-lines";
    private static final String BUTTON_CREATE_SUITE = "button#saveButton";


    @Step("Fill the fields 'Suite name'")
    public SuitePage fillSuiteName(String suiteName) {
        log.info("Fill the fields 'Suite name' with data: " + suiteName);
        $(SUITE_NAME).clear();
        $(SUITE_NAME).sendKeys(suiteName);
        return this;
    }

    @Step("Fill 'Description area' ")
    public SuitePage fillDescription(String description) {
        log.info("Fill 'Description area' with data: " + description);
        Selenide.actions().moveToElement($(DESCRIPTION)).doubleClick().sendKeys(description).build().perform();
        return this;
    }

    @Step("Click button 'Create suite' ")
    public TestRepositoryPage confirmSuite() {
        log.info("Click button 'Create suite' by locator: " + BUTTON_CREATE_SUITE);
        $(BUTTON_CREATE_SUITE).waitUntil(Condition.appear, timeout).click();
        return new TestRepositoryPage();
    }

    @Override
    @Deprecated
    public SuitePage isPageOpened() {
        return this;
    }

}
