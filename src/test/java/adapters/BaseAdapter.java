package adapters;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.protocol.HTTP;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Log4j2
abstract class BaseAdapter {

    Response response;

    private static final String URL = "https://api.qase.io";
    public static final String TOKEN = System.getenv("token");

    @Step("Delete request")
    protected Response deleteRequest(String uri, int statusCode) {
        log.info(String.format("Delete request with %s", uri));
        response = given()
                .header("Token", TOKEN)
                .when()
                .delete(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        return response;

    }

    @Step("Post Request")
    protected Response postRequest(String file, String uri, int statusCode) {
        log.info(String.format("Create new 'Suite' by API via file: %s and uri: %s", file, uri));
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", TOKEN)
                .body(file)
                .when()
                .log().body()
                .post(String.format("%s%s", URL, uri))
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        System.out.println(response.asString());
        return response;
    }

    @Step("Get request")
    public Response getRequest(String uri, int statusCode) {
        log.info(String.format("Get request by uri: %s", uri));
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", TOKEN)
                .when()
                .get(URL + uri)
                .then()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Update request")
    public Response patchRequest(String file, String uri, int statusCode) {
        log.info(String.format("Update request by API via file: %s and uri: %s", file, uri));
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", TOKEN)
                .body(file)
                .when()
                .log().body()
                .patch(URL + uri)
                .then()
                .statusCode(statusCode)
                .extract().response();
        return response;
    }

    @Step("Validate that operation was done")
    public void validateResponseByPath(String path, String expectedResult) {
        log.info(String.format("Validate result by expected Result: %s", expectedResult));
        assertEquals(response.jsonPath().getString(path), expectedResult);
    }
}


