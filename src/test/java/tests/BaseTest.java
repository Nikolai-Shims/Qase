package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.LoginSteps;
import steps.ProjectSteps;
import steps.SuiteSteps;
import tests.another.TestListener;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Listeners(TestListener.class)
public class BaseTest {

    public static final String USERNAME = System.getenv("username");
    public static final String PASSWORD = System.getenv("password");

    Faker faker = new Faker();
    LoginSteps loginSteps;
    ProjectSteps projectSteps;
    SuiteSteps suiteSteps;

    @BeforeMethod(description = "Initialize object and configuration settings")
    public void beforeTest() {
        loginSteps = new LoginSteps();
        projectSteps = new ProjectSteps();
        suiteSteps = new SuiteSteps();
        Configuration.browser = "chrome";
        Configuration.timeout = 3000;
        Configuration.clickViaJs = false;
        Configuration.headless = false;
        Configuration.startMaximized = true;
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void close() {
        if (null != getWebDriver())
            getWebDriver().quit();
    }

}
