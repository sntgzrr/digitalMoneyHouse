package com.sntgzrr.TestBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class TC_DASHB_001 {

    //P01
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void getTestDash01() {
        // Getting token from Keycloak
        Response responseToken = RestAssured.given()
                .param("grant_type", "password")
                .param("client_id", "gateway-client")
                .param("client_secret", "Hbo8UHJE4jO4uKaEQZIkACmcmh61zAR2")
                .param("username", "test@test.com")
                .param("password", "test")
                .post("http://localhost:8080/realms/digital-money-house/protocol/openid-connect/token");

        String responseBody = responseToken.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String accessToken = jsonPath.getString("access_token");

        // Start GET request with token access and userID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/accounts/1");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String userId = body.getString("user.id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + userId);
    }

    //P02
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void getTestDash02() {
        // Start GET request with userID and without Token
        Response restGet = RestAssured.get("http://localhost:8082/accounts/1");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 401);
        System.out.println("Status code: " + statusCode);
    }

    //P03
    @Tag("SMOKE")
    @Tag("REGRESSION")
    @Test
    public void getTestDash03() {
        // Getting token from Keycloak
        Response responseToken = RestAssured.given()
                .param("grant_type", "password")
                .param("client_id", "gateway-client")
                .param("client_secret", "Hbo8UHJE4jO4uKaEQZIkACmcmh61zAR2")
                .param("username", "test@test.com")
                .param("password", "test")
                .post("http://localhost:8080/realms/digital-money-house/protocol/openid-connect/token");

        String responseBody = responseToken.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String accessToken = jsonPath.getString("access_token");

        // Start GET request with token access and without userID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/accounts");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 404);
        System.out.println("Status code: " + statusCode);
    }

    //P04
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void getTestDash04() {
        // Getting token from Keycloak
        Response responseToken = RestAssured.given()
                .param("grant_type", "password")
                .param("client_id", "gateway-client")
                .param("client_secret", "Hbo8UHJE4jO4uKaEQZIkACmcmh61zAR2")
                .param("username", "test@test.com")
                .param("password", "test")
                .post("http://localhost:8080/realms/digital-money-house/protocol/openid-connect/token");

        String responseBody = responseToken.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String accessToken = jsonPath.getString("access_token");

        // Start GET request with token access an userID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/accounts/1/transactions");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String userId = body.getString("account.user.id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + userId);
    }
}
