package com.sntgzrr.TestBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class TC_TRANSFE_001 {

    //P01
    @Test
    @Tag("REGRESSION")
    public void getTestAct01() {
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

        // Start GET request with token
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/1/activity");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 200);
        System.out.println("Status code: " + statusCode);
    }

    //P02
    @Test
    @Tag("REGRESSION")
    public void getTestAct02() {
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

        // Start GET request with token
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/1/activity/1");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String actId = body.getString("id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(actId, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + actId);
    }

    //P03
    @Test
    @Tag("REGRESSION")
    @Tag("SMOKE")
    public void postTestAct03() {
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

        // Start GET request with token
        String requestBody = "{\n" +
                "  \"details\": \"Test Transfer 4\",\n" +
                "  \"moneyAmount\": 100,\n" +
                "  \"card\": {\n" +
                "    \"id\": 1\n" +
                "  }\n" +
                "}";
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .when()
                .post("http://localhost:8082/accounts/1/transferences");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 200);
        System.out.println("Status code: " + statusCode);
    }
}
