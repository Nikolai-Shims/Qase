package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Log4j2
public class LoginPage extends BasePage{

    private static final String EMAIL_ADDRESS_FIELD = "input#inputEmail";
    private static final String PASSWORD_FIELD = "input#inputPassword";
    private static final String LOGIN_BUTTON = "btnLogin";
    private static final String URL = "https://app.qase.io/login";

    @Step("Open login page")
    public LoginPage openLoginPage(){
        log.info("Open 'Login page' by link: " + URL);
        open(URL);
        return this;
    }

    @Step("Validate that 'Login Page' was opened")
    public LoginPage isLoginPageOpened(){
        $(By.id(LOGIN_BUTTON)).shouldBe(Condition.visible);
        return this;
    }

    @Step("Fill the 'Login page' fields and login")
    public ProjectsPage login(String username,String password){
        log.info("Fill 'Email Address' field " + username + ", fill 'Password' field " + password);
        $(EMAIL_ADDRESS_FIELD).sendKeys(username);
        $(PASSWORD_FIELD).sendKeys(password);
        $(By.id(LOGIN_BUTTON)).waitUntil(Condition.appear,timeout).click();
        return new ProjectsPage();
    }

}
