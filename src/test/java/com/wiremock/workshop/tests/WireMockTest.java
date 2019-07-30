package com.wiremock.workshop.tests;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.wiremock.workshop.model.Team;
import com.wiremock.workshop.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.wiremock.workshop.utils.RestAssuredUtils.callGet;

@RunWith(JUnit4.class)
public class WireMockTest {

    private static final String CREATE_TEAM = "/api/v1/team";
    private static final String GET_TEAM = "/api/v1/teams/%s";
    private static final String DELETE_TEAM = "/api/v1/teams/1";

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(WireMockConfiguration.options().port(8080));

    @Before
    public void setUp() {
        stubGetResponse();
    }

    private void stubGetResponse() {
        stubFor(get(urlPathEqualTo("/api/v1/teams/1"))
                .withCookie("Cookie", equalTo("example"))
                .withBasicAuth("John", "123")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBody(TestUtils.objectToString(getTeam()))
                        .withHeader("Content-Type", "application/json")));
    }

    @Test
    public void shouldGetTeam() {
        Response response = callGet(GET_TEAM, "1");
        response.then()
                .log().all()
                .statusCode(200);
        verify(exactly(1), getRequestedFor(urlPathEqualTo("/api/v1/teams/1")));
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
