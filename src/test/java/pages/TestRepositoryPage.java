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
    private static final String SUITE_NAME = "//div[@class='suite-block-header']/child::span[contains(text(),'%s')]";
    private static final String DESCRIPTION_PROJECT = "//span[text()='%s']/following-sibling::p[contains(@class,'suite-description')]";
    private static final String CASE_NAME = "//div[text()='%s']";

    @Step("Open settings")
    public NewProjectPage openSettings() {
        log.info(String.format("Open 'Settings' by locator: %s", SETTINGS));
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
        log.info(String.format("Open 'Test Repository' by link: %s", PROJECT_URL));
        open(PROJECT_URL);
        return this;
    }

    @Step("Click button 'Create new suite'")
    public SuitePage createSuite() {
        log.info(String.format("Click button 'Create new suite' by locator: %s ", CREATE_SUITE));
        $(By.xpath(CREATE_SUITE)).waitUntil(Condition.appear, timeout).click();
        return new SuitePage();
    }

    @Step("Choose option 'Delete' and press it")
    public DeleteSuiteModal deleteSuite(String name) {
        log.info(String.format("Find element by locator: %s, move to element and press", DELETE_SUITE));
        Selenide.actions().moveToElement($(By.xpath(String.format(DELETE_SUITE, name)))).click().build().perform();
        return new DeleteSuiteModal();
    }

    @Step("Choose option 'Edit' and press it")
    public SuitePage editSuite(String suite) {
        log.info(String.format("Find element by locator: %s, move to element and press it", EDIT_SUITE));
        Selenide.actions().moveToElement($(By.xpath(String.format(EDIT_SUITE, suite)))).click().build().perform();
        return new SuitePage();
    }

    @Step("Get Suite name")
    public String validateSuiteName(String suite) {
        log.info(String.format("Validate that 'Suite' with name %s was created", suite));
        return $(By.xpath(String.format(SUITE_NAME, suite))).getText();
    }

    @Step("Click button 'Create new case'")
    public TestCasePage createNewTestCase() {
        log.info(String.format("Click button 'Create new case' by locator: %s", CREATE_CASE));
        int amount = $$(By.xpath(CREATE_CASE)).size();
        if (amount != 0) {
            $(By.xpath(CREATE_CASE)).waitUntil(Condition.appear, timeout).click();
        } else {
            $(By.xpath(CREATE_CASE2)).waitUntil(Condition.appear, timeout).click();
        }
        return new TestCasePage();
    }

    @Step("Choose created Case")
    public EditCasePage chooseCase(String caseName) {
        log.info(String.format("Choose Case by name: %s", caseName));
        $(By.xpath(String.format(CASE_NAME, caseName))).waitUntil(Condition.appear, timeout).click();
        return new EditCasePage();
    }

}
