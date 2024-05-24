package com.sntgzrr.TestBack;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

public class TC_CARDS_001 {

    //P01
    @Test
    @Tag("REGRESSION")
    public void getTestCards01() {
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
                .get("http://localhost:8082/cards");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String userId = body.getString("[0].id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + userId);
    }

    //P02
    @Test
    @Tag("REGRESSION")
    public void getTestCards02() {
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

        // Start GET request with token and userID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/accounts/1/cards");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String userId = body.getString("account.user.id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + userId);
    }

    //P03
    @Test
    @Tag("REGRESSION")
    public void getTestCards03() {
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

        // Start GET request with token, userID and cardID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .get("http://localhost:8082/accounts/1/cards/1");
        int statusCode = restGet.statusCode();
        JsonPath body = restGet.jsonPath();
        String userId = body.getString("account.user.id");
        String cardID = body.getString("id");

        Assert.assertEquals(statusCode, 200);
        Assert.assertEquals(userId, "1");
        Assert.assertEquals(cardID, "1");
        System.out.println("Status code: " + statusCode);
        System.out.println("User ID info: " + userId);
        System.out.println("Card ID info: " + cardID);
    }


    //P04
    @Test
    @Tag("REGRESSION")
    public void postTestCards04() {
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

        // Start POST request with token
        String requestBody = "{ \"type\": \"debit\", \"number\": \"81211261211\", \"cvv\": \"9812\", \"account\": { \"cvu\": \"5526962010998706922014\" } }";
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken)
                .body(requestBody)
                .when()
                .post("http://localhost:8082/cards");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 200);
        System.out.println("Status code: " + statusCode);
    }

    //P05
    @Test
    @Tag("REGRESSION")
    public void deleteTestCards05() {
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

        // Start DELETE request with token, userID and cardID
        Response restGet = RestAssured.given()
                .header("Authorization", "Bearer " + accessToken).when()
                .delete("http://localhost:8082/accounts/1/cards/2");
        int statusCode = restGet.statusCode();

        Assert.assertEquals(statusCode, 200);
        System.out.println("Status code: " + statusCode);
    }
}
