package com.wiremock.workshop.tests;

import com.wiremock.workshop.model.Team;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.wiremock.workshop.utils.RestAssuredUtils.callGet;

@RunWith(JUnit4.class)
public class WireMockTest {

    private static final String CREATE_TEAM = "/api/v1/team";
    private static final String GET_TEAM = "/api/v1/teams/%s";
    private static final String DELETE_TEAM = "/api/v1/teams/1";

    @Test
    public void shouldGetTeam() {
        Response response = callGet(GET_TEAM, "1");
        response.then()
                .log().all()
                .statusCode(200);
    }

    private Team getTeam() {
        return Team.builder()
                .id(1)
                .name("Real Madrid")
                .coach("Zinedine Zidane")
                .location("Madrid")
                .stadium("Santiago Bernabeu")
                .build();
    }
}
