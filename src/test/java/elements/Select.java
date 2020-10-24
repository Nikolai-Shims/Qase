package elements;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class Select {

    String labelLocator = "//*[text()='%s']/parent::div//div[contains(@class, 'container')]";
    String optionLocator = "//*[contains(@id, 'react-select') and contains(text(),'%s')]";
    String label;

    public Select(String label) {
        this.label = label;
    }

    @Step("Choose select, click on the select and choose option")
    public void select(String option) {
        log.info("Choose select: " + label + ", and option: " + option);
            $(By.xpath(String.format(labelLocator, label))).shouldBe(Condition.visible).click();
            $(By.xpath(String.format(optionLocator, option))).shouldBe(Condition.visible).click();

    }
}