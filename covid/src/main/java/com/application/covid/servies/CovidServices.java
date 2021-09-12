package com.application.covid.servies;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CovidServices {

    public ResponseEntity getDistrictList(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://data.covid19india.org/v4/min/data.min.json";
        ResponseEntity responseEntity = restTemplate.exchange(url,HttpMethod.GET,null,JsonNode.class, (Object) null);
        return responseEntity;
    }
}
