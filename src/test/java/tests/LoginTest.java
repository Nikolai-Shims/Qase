package tests;

import org.testng.annotations.Test;
import tests.another.Retry;

public class LoginTest extends BaseTest {

    @Test(description = "Fill 'Login page' fields with correct data", retryAnalyzer = Retry.class)
    public void correctLogin(){
        System.out.println(System.getenv("username"));
        loginSteps
                .login(System.getenv("username"),System.getenv("password"));
    }
}
