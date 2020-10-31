package elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Select {

    public static final String DEFAULT_ASSIGNEE = "//*[@id='react-select-5-option-0']";
    private static final String TEXT_BY_SELECT = "//span[text()='%s']/following-sibling::span[contains(@class,'preview-quick-edit')]";
    String labelLocator = "//*[text()='%s']/parent::div//div[contains(@class, 'container')]";
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String label;

    public Select(String label) {
        this.label = label;
    }

    @Step("Click on the select {option}")
    public void select(String option) {
        log.info(String.format("Choose select: %s, and option: %s", label, option));
        $(By.xpath(String.format(labelLocator, label))).shouldBe(Condition.visible).click();
        $(By.xpath(String.format(optionLocator, option))).shouldBe(Condition.visible).click();

    }

    @Step("Choose default assignee")
    public void selectDefaultAssignee() {
        log.info(String.format("Choose select: %s,", label));
        $(By.xpath(String.format(labelLocator, label))).shouldBe(Condition.visible).click();
        $(By.xpath(DEFAULT_ASSIGNEE)).shouldBe(Condition.visible).click();

    }

    @Step("Get text from the 'Select'")
    public static String getTextBySelect(String select) {
        log.info("Get text from select " + select);
        return $(By.xpath(String.format(TEXT_BY_SELECT, select))).getText();
    }
}