package com.wiremock.workshop.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {

    public static String objectToString(Object object) {
        String stringFromObject;
        try {
            stringFromObject = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            stringFromObject = "Failed to parse object to string - " + e.getMessage();

        }
        return stringFromObject;
    }
}
