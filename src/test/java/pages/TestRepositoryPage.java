package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import pages.modals.DeleteSuiteModal;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class TestRepositoryPage extends BasePage {

    public static final String SETTINGS = "//span[text()='Settings']";
    private static final String CREATE_SUITE = "//a[text()='Create new suite']";
    private static final String CREATE_CASE = "//a[text()='Create new case']";
    private static final String PROJECT_URL = "https://app.qase.io/project/QASEPROJEC";
    private static final String DELETE_SUITE = ".fa.fa-trash";
    private static final String EDIT_SUITE = ".fa-pencil-alt";
    private static final String NAME_PROJECT = ".suite-header";
    private static final String DESCRIPTION_PROJECT = ".suite-description";
    private static final String CASE_NAME = ".case-row";

    @Step("Open settings")
    public NewProjectPage openSettings() {
        log.info("Open 'Settings' by locator: " + SETTINGS);
        $(By.xpath(SETTINGS)).waitUntil(Condition.appear, timeout).click();
        return new NewProjectPage();
    }

    @Step("Validate that 'Test Repository Page' was opened")
    public TestRepositoryPage isPageOpened() {
        $(By.xpath(CREATE_SUITE)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Open working project by link: " + PROJECT_URL)
    public TestRepositoryPage openTestRepositoryPage() {
        log.info("Open 'Test Repository' by link: " + PROJECT_URL);
        open(PROJECT_URL);
        return this;
    }

    @Step("Click button 'Create new suite'")
    public SuitePage createSuite() {
        log.info("Click button 'Create new suite' by locator: " + CREATE_SUITE);
        $(By.xpath(CREATE_SUITE)).waitUntil(Condition.appear, timeout).click();
        return new SuitePage();
    }

    @Step("Choose option 'Delete' and press it")
    public DeleteSuiteModal deleteSuite() {
        log.info("Find element by locator: " + DELETE_SUITE + ", move to element and press");
        Selenide.actions().moveToElement($(DELETE_SUITE)).click().build().perform();
        return new DeleteSuiteModal();
    }

    @Step("Choose option 'Edit' and press it")
    public SuitePage editSuite() {
        log.info("Find element by locator: " + EDIT_SUITE + ", move to element and press it");
        Selenide.actions().moveToElement($(EDIT_SUITE)).click().build().perform();
        return new SuitePage();
    }

    @Step("Get Suite name")
    public String validateSuiteName() {
        return $(NAME_PROJECT).getText();
    }

    @Step("Get description")
    public String validateDescription() {
        return $(DESCRIPTION_PROJECT).getText();
    }


    @Step("Click button 'Create new case'")
    public TestCasePage createNewTestCase() {
        log.info("Click button 'Create new case' by locator: " + CREATE_CASE);
        $(By.xpath(CREATE_CASE)).waitUntil(Condition.appear, timeout).click();
        return new TestCasePage();
    }

    @Step("Choose created Case")
    public EditCasePage chooseCase() {
        log.info("Choose Case by locator: " + CASE_NAME);
        $(CASE_NAME).waitUntil(Condition.appear, timeout).click();
        return new EditCasePage();
    }


}
