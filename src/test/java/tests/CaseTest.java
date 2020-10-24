package tests;

import models.TestCase;
import org.testng.annotations.Test;

public class CaseTest extends BaseTest{

    String title = faker.name().username();
    String description = faker.name().username();
    String preCondition = faker.name().username();
    String postCondition = faker.name().username();

    String editTitle = faker.name().username();
    String editDescription = faker.name().username();
    String editPreCondition = faker.name().username();
    String editPostCondition = faker.name().username();



    TestCase testCase = TestCase.builder()
            .preConditions(preCondition)
            .description(description)
            .postConditions(postCondition)
            .title(title)
            .status("Draft")
            .severity("Major")
            .priority("Low")
            .type("Security")
            .behavior("Positive")
            .automationStatus("Automated")
            .build();

    TestCase editCase = TestCase.builder()
            .preConditions(editPreCondition)
            .description(editDescription)
            .postConditions(editPostCondition)
            .title(editTitle)
            .status("Actual")
            .severity("Minor")
            .priority("High")
            .type("Usability")
            .behavior("Negative")
            .automationStatus("Automated")
            .build();

    @Test
    public void createEditAndDeleteCase(){
        loginSteps
                .login(USERNAME,PASSWORD);
        caseSteps
                .createNewCase(testCase)
                .validateThatCaseWasCreated("Major","Low",preCondition,description,"Positive","Security","Draft","Automated")
                .editCase(editCase)
                .validateThatCaseWasEdited("Minor","High",editPreCondition,editDescription,"Negative","Usability","Actual","Automated")
                .deleteCase();
    }
}
