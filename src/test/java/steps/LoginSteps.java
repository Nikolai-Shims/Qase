package steps;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;

@Log4j2
public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    @Step("Open page,validate that 'Login page' was opened and login")
    public LoginSteps login(String username, String password) {
        log.info("Fill the 'login page' with username: " + username + ", and password: " + password);
        loginPage
                .openLoginPage()
                .isLoginPageOpened()
                .login(username, password);
        return this;
    }

    @Step("Fill 'Login page' with invalid data")
    public LoginSteps invalidLogin(String username, String password) {
        log.info("Fill the 'login page' with username: " + username + ", and password: " + password);
        loginPage
                .openLoginPage()
                .isLoginPageOpened()
                .invalidLogin(username, password);
        return this;
    }

    @Step("Validate error message")
    public LoginSteps validateErrorMessage(String errorMessage) {
        log.info("Validate error message: " + errorMessage);
        assertEquals(loginPage.getErrorMessage(), errorMessage);
        return this;
    }
}
