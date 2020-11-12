package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.base.Retry;

public class LoginTest extends BaseTest {

    String invalidUsername = faker.bool().toString();
    String invalidPassword = faker.name().lastName();

    @Test(description = "Validate error message with invalid data", retryAnalyzer = Retry.class)
    public void checkInvalidLogin() {
        loginSteps
                .invalidLogin(invalidUsername, invalidPassword)
                .validateErrorMessage("These credentials do not match our records.");
    }
}
