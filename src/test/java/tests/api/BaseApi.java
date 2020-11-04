package tests.api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.apache.http.protocol.HTTP;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@Log4j2
public abstract class BaseApi {
    Response response;

    private static final String URL = "https://api.qase.io/v1/";
    public static final String TOKEN = System.getenv("token");

    @Step("Delete Suite")
    public Response deleteRequest(String uri, int statusCode) {
        log.info(String.format("Delete suite with %s", uri));
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

    @Step("Create new Suite")
    public Response postRequest(File file, String uri, int statusCode) {
        log.info(String.format("Create new 'Suite' by API via file: %s and uri: %s", file, uri));
        response = given()
                .header(HTTP.CONTENT_TYPE, "application/json")
                .header("Token", TOKEN)
                .body(file)
                .when()
                .log().body()
                .post(URL + uri)
                .then()
                .log().body()
                .statusCode(statusCode)
                .extract().response();
        System.out.println(response.asString());
        return response;
    }

    @Step("Get existing suite")
    public Response getRequest(String uri, int statusCode) {
        log.info(String.format("Get existing suite by uri: %s", uri));
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

    @Step("Update existing Suite ")
    public Response patchRequest(File file, String uri, int statusCode) {
        log.info(String.format("Update existing 'Suite' by API via file: %s and uri: %s", file, uri));
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
