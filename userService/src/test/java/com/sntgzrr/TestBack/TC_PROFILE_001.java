package com.sntgzrr.TestBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.Assert;


public class TC_PROFILE_001 {

    //P01
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void getTestProfile01() {
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
                .get("http://localhost:8082/users/1");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String userId = body.getString("id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + userId);
    }

    //P02
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void getTestProfile02() {
        // Start GET request without token access and userID
        Response restGet = RestAssured.get("http://localhost:8082/users/1");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 401);
        System.out.println("Status code: " + statusCode);
    }

    //P03
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void getTestProfile03() {
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
                .get("http://localhost:8082/users/");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 404);
        System.out.println("Status code: " + statusCode);
    }

    //P04
    @Test
    @Tag("SMOKE")
    @Tag("REGRESSION")
    public void patchTestProfile04() {
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

        //Body JSON
        String requestBody = "{\"id\":\"1\",\"firstName\":\"test\",\"lastName\":\"test\",\"dni\":\"111\",\"email\":\"test@test.com\",\"phone\":\"12121212\",\"cvu\":\"6820356311324401712290\",\"alias\":\"digital.please.hot\",\"password\":\"null\"}";

        // Start PATCH request with token access and without userID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .when()
                .patch("http://localhost:8082/users");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 200);
        System.out.println("Status code: " + statusCode);
    }
}
