package api.endpoints;


//UserEndpoints.java
//Created to perform Create, Read, Update, Delete requests to the User services

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routs.post_url);

        return response;

    }

    public static Response getUser(String username){
        Response response = given()
                .pathParams("username", username)
        .when()
                .get(Routs.get_url);

        return response;
    }

    public static Response updateUser(String username, User payload){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username", username)
                .body(payload)
        .when()
                .put(Routs.update_url);
        return response;
    }

    public static Response deleteUser(String username){
        Response response = given()
                .pathParams("username", username)
        .when()
                .delete(Routs.delete_url);
        return response;
    }

}
