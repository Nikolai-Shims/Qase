package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import pages.modals.DeleteCaseModal;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class EditCasePage extends TestRepositoryPage {

    private static final String BUTTON_DELETE_CASE = "//button[@title='Delete case']";
    private static final String BUTTON_EDIT_CASE = "a.btn-control";

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
