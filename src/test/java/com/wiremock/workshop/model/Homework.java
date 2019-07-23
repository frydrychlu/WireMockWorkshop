package com.wiremock.workshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Homework {

    private int id;
    private String lessonId;
    private String topic;
    private String notes;
}
