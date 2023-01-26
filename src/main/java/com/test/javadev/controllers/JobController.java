package com.test.javadev.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.javadev.responses.GroupJobResponse;
import com.test.javadev.responses.JobResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("api")
public class JobController {
    @Value("${dans.api.base_url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping(value = "/job", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getJobList() {

        try {
            String uri = baseUrl + "/recruitment/positions.json";
            String response = restTemplate.getForObject(uri, String.class);

            List<HashMap<String, Object>> responseList = objectMapper.readValue(response, List.class);

            ArrayList<JobResponse> jobResponses = new ArrayList<>();
            ArrayList<String> locationList = new ArrayList<>();
            for (HashMap<String, Object> jobResponse: responseList) {
                Object companyUrl = jobResponse.get("company_url");
                Object companyLogo = jobResponse.get("company_logo");

                String location = jobResponse.get("location").toString();
                if (!locationList.contains(location)) {
                    locationList.add(location);
                }

                jobResponses.add(
                        new JobResponse(
                                jobResponse.get("id").toString(),
                                jobResponse.get("type").toString(),
                                jobResponse.get("url").toString(),
                                jobResponse.get("company").toString(),
                                companyUrl == null ? "" : companyUrl.toString(),
                                jobResponse.get("location").toString(),
                                jobResponse.get("title").toString(),
                                jobResponse.get("description").toString(),
                                jobResponse.get("created_at").toString(),
                                jobResponse.get("how_to_apply").toString(),
                                companyLogo == null ? "" : companyLogo.toString()
                        )
                );
            }

            ArrayList<GroupJobResponse> groupedJobResponses = new ArrayList<>();
            for (String location: locationList) {
                ArrayList<JobResponse> newJobResponses = new ArrayList<>();

                for (JobResponse jobResponse: jobResponses) {
                    if (jobResponse.getLocation().equals(location)) {
                        newJobResponses.add(jobResponse);
                    }
                }

                groupedJobResponses.add(
                        new GroupJobResponse(location, newJobResponses)
                );
            }

            HashMap<String, Object> result = new HashMap<>();
            result.put("result", groupedJobResponses);

            HashMap<String, Object> returnedResponse = new HashMap<>();
            returnedResponse.put("data", result);

            return new ResponseEntity<>(returnedResponse, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();

            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/job/{id}", produces = APPLICATION_JSON_VALUE)
    ResponseEntity<?> getJobDetail(@PathVariable String id) {
        try {
            String uri = baseUrl + "/recruitment/positions/" + id;
            String response = restTemplate.getForObject(uri, String.class);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
