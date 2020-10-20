package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

@Log4j2
public class NewProjectPage extends BasePage{

    private static final String PROJECT_NAME = "input#inputTitle";
    private static final String PROJECT_CODE = "input#inputCode";
    private static final String DESCRIPTION = "textarea#inputDescription";
    private static final String BUTTON_CREATE_NEW_PROJECT = "//button[text()='Create project']";
    private static final String SETTINGS = "//span[text()='Settings']";

    @Step("Validate that 'New Project' page was opened")
    public NewProjectPage isNewProjectPageOpened(){
        $(By.xpath(BUTTON_CREATE_NEW_PROJECT)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Click button 'Create new project'")
    public TestRepositoryPage createNewProject(String projectName, String projectCode, String description){
        $(PROJECT_NAME).sendKeys(projectName);
        $(PROJECT_CODE).sendKeys(projectCode);
        $(DESCRIPTION).sendKeys(description);
        $(By.xpath(BUTTON_CREATE_NEW_PROJECT)).waitUntil(Condition.appear,timeout).click();
        return new TestRepositoryPage();
    }
}
