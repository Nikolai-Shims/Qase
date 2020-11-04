package tests.api;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;


public class ApiTest extends BaseApi {

    @Test(description = "Create 'Suite' by API validate, update and delete")
    public void createUpdateDeleteSuite() {
        Response response = postRequest(new File("src/test/resources/newSuite.json"), "suite/QASEPROJEC", 200);
        validateResponseByPath("status", "true");
        int id = response.jsonPath().getInt("result.id");
        getRequest("suite/QASEPROJEC/" + id, 200);
        patchRequest(new File("src/test/resources/editSuite.json"), "suite/QASEPROJEC/" + id, 200);
        validateResponseByPath("status", "true");
        deleteRequest("suite/QASEPROJEC/" + id, 200);
        validateResponseByPath("status", "true");
    }

}

