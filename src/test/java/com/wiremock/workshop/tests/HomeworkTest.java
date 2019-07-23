package com.wiremock.workshop.tests;

import com.wiremock.workshop.model.Homework;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.wiremock.workshop.utils.RestAssuredUtils.createHomework;
import static com.wiremock.workshop.utils.TestUtils.objectToString;

@RunWith(JUnit4.class)
public class HomeworkTest {

    private static final String CREATE_HOMEWORK = "/api/v1/homework";

    @Before
    public void stubsInit() {
        mockCreateHomework();
    }

    @Test
    public void shouldPostHomework() {
        Response response = createHomework(CREATE_HOMEWORK, objectToString(getHomework()));
        response.then()
                .log().all()
                .statusCode(201);

        Homework actualHomework = response.as(Homework.class);

        verify(exactly(1), postRequestedFor(urlPathEqualTo(CREATE_HOMEWORK)));
        Assert.assertEquals(getHomework().getId(), actualHomework.getId());
        Assert.assertEquals(getHomework().getLessonId(), actualHomework.getLessonId());
        Assert.assertEquals(getHomework().getTopic(), actualHomework.getTopic());
        Assert.assertEquals(getHomework().getNotes(), actualHomework.getNotes());
    }

    private void mockCreateHomework() {

    }

    private Homework getHomework() {
        return Homework.builder()
                .id(12)
                .lessonId("july_25_19_wiremock")
                .topic("Wiremock")
                .notes("Wiremock is cool and I should use it")
                .build();
    }
}
