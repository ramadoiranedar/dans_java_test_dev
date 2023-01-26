package com.test.javadev.responses;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobResponse {
    private String id, type, url, company, location, title, description;
    @JsonProperty(value = "company_url")
    private String companyUrl;
    @JsonProperty(value = "created_at")
    private String createdAt;
    @JsonProperty(value = "how_to_apply")
    private String howToApply;
    @JsonProperty(value = "company_logo")
    private String companyLogo;

    @JsonCreator
    public JobResponse() {

    }

    @JsonCreator
    public JobResponse(String id, String type, String url, String company, String companyUrl, String location, String title, String description, String createdAt, String howToApply, String companyLogo) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.company = company;
        this.companyUrl = companyUrl;
        this.location = location;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.howToApply = howToApply;
        this.companyLogo = companyLogo;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }
}
