package com.wiremock.workshop.utils;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RestAssuredUtils {

    private static final String BASE_URL = "http://localhost:8080";

    public static Response callGet(String path, String id) {
        return given()
                .baseUri(BASE_URL)
                .basePath(String.format(path, id))
                .header("Content-Type", "application/json")
                .get();
    }

    public static Response callDelete(String path) {
        return given()
                .baseUri(BASE_URL)
                .delete(path);
    }

    public static Response callPost(String path, String body) {
        return given()
                .baseUri(BASE_URL)
                .body(body)
                .post(path);
    }

    public static Response createHomework(String path, String body) {
        return given()
                .auth().preemptive().basic("homework", "home123")
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(body)
                .post(path);
    }
}
