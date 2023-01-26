package com.test.javadev.responses;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.List;

public class GroupJobResponse {
    private String location;
    private List<JobResponse> data;

    @JsonCreator
    public GroupJobResponse() {

    }

    @JsonCreator
    public GroupJobResponse(String location, List<JobResponse> data) {
        this.location = location;
        this.data = data;
    }

    public String getLocation() {
        return location;
    }

    public List<JobResponse> getData() {
        return data;
    }
}
