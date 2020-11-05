package elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

@Log4j2
public class Input {

    private static final String TEXT_BY_INPUT = "//*[text()='%s']/parent::div//p";
    String locator = "//*[text()='%s']/parent::div//p";
    String titleLocator = "//*[contains(text(),'%s')]/following-sibling::input";
    String label;

    public Input(String label) {
        this.label = label;
    }

    @Step("Fill the 'Title' with data: {text}")
    public Input writeTitle(String text) {
        log.info(String.format("Fill the field %s, with data: %s,by locator: %s", label, text, titleLocator));
        $(By.xpath(String.format(titleLocator, label))).clear();
        $(By.xpath(String.format(titleLocator, label))).sendKeys(text);
        return this;
    }

    @Step("Fill the Input with data: {text} ")
    public Input write(String text) {
        log.info(String.format("Fill the field %s with data: %s, by locator: %s", label, text, locator));
        $(By.xpath(String.format(locator, label))).shouldBe(Condition.visible).clear();
        $(By.xpath(String.format(locator, label))).shouldBe(Condition.visible).sendKeys(text);
        return this;
    }


    @Step("Get text from the 'Input'")
    public static String getTextByInput(String input) {
        log.info("Get text from 'input' " + input);
        return $(By.xpath(String.format(TEXT_BY_INPUT, input))).getText();
    }
}
