package elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Input {

    String locator = "//*[text()='%s']/parent::div//p";
    String titleLocator = "//*[contains(text(),'%s')]/following-sibling::input";
    String label;

    public Input(String label) {
        this.label = label;
    }

    @Step("Fill the 'Title'")
    public Input writeTitle(String text) {
        log.info("Fill the field " + label + ", with data: " + text + ",by locator: " + titleLocator);
        $(By.xpath(String.format(titleLocator, label))).clear();
        $(By.xpath(String.format(titleLocator, label))).sendKeys(text);
        return this;
    }

    @Step("Fill the Input")
    public Input write(String text) {
        log.info("Fill the field " + label + " with data: " + text + ", by locator: " + locator);
        $(By.xpath(String.format(locator, label))).shouldBe(Condition.visible).clear();
        $(By.xpath(String.format(locator, label))).shouldBe(Condition.visible).sendKeys(text);
        return this;
    }
}
