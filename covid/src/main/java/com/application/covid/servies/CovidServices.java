package com.application.covid.servies;

import com.application.covid.Modles.District;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CovidServices {

    public ResponseEntity getDistrictList(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://api.covid19india.org/state_district_wise.json";
        ResponseEntity responseEntity = restTemplate.exchange(url,HttpMethod.GET,null,JsonNode.class, (Object) null);
        return responseEntity;
    }
}
