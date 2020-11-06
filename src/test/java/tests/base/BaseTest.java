package tests.base;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.*;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
@Listeners(TestListener.class)
public class BaseTest {

    public static final String USERNAME = System.getenv("username");
    public static final String PASSWORD = System.getenv("password");

    protected Faker faker = new Faker();
    protected LoginSteps loginSteps;
    protected ProjectSteps projectSteps;
    protected SuiteSteps suiteSteps;
    protected CaseSteps caseSteps;
    protected TestPlanSteps testPlanSteps;
    protected TestRunSteps testRunSteps;

    @BeforeMethod(description = "Initialize object and configuration settings")
    public void beforeTest() {
        loginSteps = new LoginSteps();
        projectSteps = new ProjectSteps();
        suiteSteps = new SuiteSteps();
        caseSteps = new CaseSteps();
        testPlanSteps = new TestPlanSteps();
        testRunSteps = new TestRunSteps();
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
        Configuration.clickViaJs = false;
        Configuration.headless = false;
        Configuration.startMaximized = true;
    }

    @AfterMethod(alwaysRun = true, description = "Close browser")
    public void close() {
        try {
            getWebDriver().quit();
        } catch (IllegalStateException ex) {
            log.warn("WebDriver is not opened on attempt to close it");
            log.warn(ex.getLocalizedMessage());
        }
    }

}
