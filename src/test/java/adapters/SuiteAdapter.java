package adapters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SuiteAdapter extends BaseAdapter {

    private static final String URI = "/v1/suite/QASEPROJEC/";
    private static final String PATH = "status";


    @Step("Create new Suite")
    public int post(String json, int statusCode, String result) {
        log.info(String.format("Post request %s, with status Code: %s, and result: %s", json, statusCode, result));
        Response response = super.postRequest(json, URI, statusCode);
        int id = response.jsonPath().getInt("result.id");
        super.validateResponseByPath(PATH, result);
        return id;
    }

    @Step("Delete existing Suite")
    public void delete(int id, int statusCode, String result) {
        log.info(String.format("Delete request with id: %s, with status code %s", id, statusCode));
        super.deleteRequest(URI + id, statusCode);
        super.validateResponseByPath(PATH, result);
    }

    @Step("Edit existing Suite")
    public void patch(String json, int id, int statusCode, String result) {
        log.info(String.format("Patch request %s, with status Code: %s, and result: %s", json, statusCode, result));
        super.patchRequest(json, URI + id, statusCode);
        super.validateResponseByPath(PATH, result);
    }

    @Step("Get specific 'Suite'")
    public void get(int id, int statusCode) {
        log.info(String.format("Get request for specific 'Suite' by id: %s", id));
        super.getRequest(URI + id, statusCode);
    }
}
