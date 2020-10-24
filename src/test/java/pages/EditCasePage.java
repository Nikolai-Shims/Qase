package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import pages.modals.DeleteCaseModal;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class EditCasePage extends TestRepositoryPage {

    private static final String TEXT_BY_SELECT = "//span[text()='%s']/following-sibling::span[contains(@class,'preview-quick-edit')]";
    private static final String TEXT_BY_INPUT = "//*[text()='%s']/parent::div//p";
    private static final String BUTTON_DELETE_CASE = "//button[@title='Delete case']";
    private static final String BUTTON_EDIT_CASE = "a.btn-control";

    @Step("Get text from the 'Select'")
    public String getTextBySelect(String select) {
        log.info("Get text from select " + select);
        return $(By.xpath(String.format(TEXT_BY_SELECT, select))).getText();
    }

    @Step("Get text from the 'Input'")
    public String getTextByInput(String input) {
        log.info("Get text from 'input' " + input);
        return $(By.xpath(String.format(TEXT_BY_INPUT, input))).getText();
    }

    @Step("Click button 'Delete'")
    public DeleteCaseModal clickDeleteCase() {
        log.info("Click button 'Delete' by locator: " + BUTTON_DELETE_CASE);
        $(By.xpath(BUTTON_DELETE_CASE)).waitUntil(Condition.appear, timeout).click();
        return new DeleteCaseModal();
    }

    @Step("Click button 'Edit' case")
    public TestCasePage editCase() {
        $(BUTTON_EDIT_CASE).waitUntil(Condition.appear, timeout).click();
        return new TestCasePage();
    }

}
