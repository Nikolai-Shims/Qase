package tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginSteps;
import tests.another.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {
    LoginSteps loginSteps;

    @BeforeMethod (description = "Initialize object and configuration settings")
    public void beforeTest(){
        loginSteps = new LoginSteps();
        Configuration.browser= "chrome";
        Configuration.timeout = 3000;
        Configuration.clickViaJs = true;
        Configuration.startMaximized = true;
    }

    @AfterMethod (alwaysRun = true, description = "Close browser")
    public void close(){
        if(null != getWebDriver())
            getWebDriver().quit();
    }

}
