package com.wiremock.workshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team {

    private int id;
    private String name;
    private String coach;
    private String location;
    private String stadium;
}
