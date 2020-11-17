package tests.api;

import com.google.gson.Gson;
import models.TestSuite;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class SuiteApiTest extends BaseTest {

    Gson gson = new Gson();
    public String title = faker.name().username();
    public String description = faker.chuckNorris().fact();
    public String postCondition = faker.name().username();
    public String editTitle = faker.name().username();
    public String editDescription = faker.name().username();

    TestSuite suite = TestSuite.builder()
            .title(title)
            .description(description)
            .postCondition(postCondition)
            .build();

    TestSuite suite1 = TestSuite.builder()
            .title(editTitle)
            .description(editDescription)
            .postCondition(postCondition)
            .build();

    String suite2 = gson.toJson(suite);
    String suite3 = gson.toJson(suite1);

    @Test(description = "Create 'Suite' by API validate, update and delete")
    public void postGetPatchDeleteRequestForSuite() {
        int id = suiteAdapter.post(suite2, 200, "true");
        suiteAdapter.get(id,200);
        suiteAdapter.patch(suite3, id, 200, "true");
        suiteAdapter.delete(id, 200, "true");
    }

}

