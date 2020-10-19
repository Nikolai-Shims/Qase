package steps;

import io.qameta.allure.Step;
import packages.LoginPage;

public class LoginSteps {

    LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

    @Step("Open page,validate that 'Login page' was open and login")
    public LoginSteps login(String username, String password){
        loginPage
                .openLoginPage()
                .isLoginPageOpened()
                .login(username,password);
        return this;
    }
}
