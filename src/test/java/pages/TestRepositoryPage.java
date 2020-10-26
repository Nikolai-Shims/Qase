package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import pages.modals.DeleteSuiteModal;

import static com.codeborne.selenide.Selenide.*;

@Log4j2
public class TestRepositoryPage extends BasePage {

    public static final String SETTINGS = "//span[text()='Settings']";
    private static final String CREATE_SUITE = "//a[contains(text(),'uite')]";
    private static final String CREATE_CASE2 = "//a[contains(@class,'mr-2 btn-primary')]/child::i";
    private static final String CREATE_CASE = "//a[text()='Create new case']";
    private static final String PROJECT_URL = "https://app.qase.io/project/QASEPROJEC";
    private static final String DELETE_SUITE = "//span[text()='%s']/descendant::i[contains(@class,'fa-trash')]";
    private static final String EDIT_SUITE = "//span[text()='%s']/descendant::i[contains(@class,'fa-pencil-alt')]";
    private static final String SUITE_NAME = "//span[text()='%s']";
    private static final String DESCRIPTION_PROJECT = "//span[text()='%s']/following-sibling::p[contains(@class,'suite-description')]";
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
    public DeleteSuiteModal deleteSuite(String name) {
        log.info("Find element by locator: " + DELETE_SUITE + ", move to element and press");
        Selenide.actions().moveToElement($(By.xpath(String.format(DELETE_SUITE, name)))).click().build().perform();
        return new DeleteSuiteModal();
    }

    @Step("Choose option 'Edit' and press it")
    public SuitePage editSuite(String suite) {
        log.info("Find element by locator: " + EDIT_SUITE + ", move to element and press it");
        Selenide.actions().moveToElement($(By.xpath(String.format(EDIT_SUITE, suite)))).click().build().perform();
        return new SuitePage();
    }

    @Step("Get Suite name")
    public String validateSuiteName(String suite) {
        log.info(String.format("Validate that 'Suite' with name %s was created", suite));
        return $(By.xpath(String.format(SUITE_NAME, suite))).getText();
    }

    @Step("Get description")
    public String validateDescription(String text) {
        log.info(String.format("Get description data: %s", text));
        return $(By.xpath(String.format(DESCRIPTION_PROJECT, text))).getText();
    }


    @Step("Click button 'Create new case'")
    public TestCasePage createNewTestCase() {
        log.info("Click button 'Create new case' by locator: " + CREATE_CASE);
        int amount = $$(By.xpath(CREATE_CASE)).size();
        if (amount != 0) {
            $(By.xpath(CREATE_CASE)).waitUntil(Condition.appear, timeout).click();
        } else {
            $(By.xpath(CREATE_CASE2)).waitUntil(Condition.appear, timeout).click();
        }
        return new TestCasePage();
    }

    @Step("Choose created Case")
    public EditCasePage chooseCase() {
        log.info("Choose Case by locator: " + CASE_NAME);
        $(CASE_NAME).waitUntil(Condition.appear, timeout).click();
        return new EditCasePage();
    }

}
