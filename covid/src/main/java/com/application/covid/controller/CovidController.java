package com.application.covid.controller;

import com.application.covid.Configurations.GreatingConfiguration;
import com.application.covid.Modles.District;
import com.application.covid.servies.CovidServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CovidController {

	@Autowired
	CovidServices covidServices;

	@Autowired
	District district;

	@Autowired
	GreatingConfiguration greatingConfiguration;

	Logger logger = LoggerFactory.getLogger(CovidController.class);

	@RequestMapping(value = {"home/{user}", "/home"})
	public String home(@PathVariable(value="user",required = false) String userName){
		if(userName==null){
			userName="guest";
		}
		return greatingConfiguration.getGreat()+userName;
	}

	@GetMapping("/district")
	public List<District> getDistrictDetails(){
		List<District> distInfoList = new ArrayList<District>();

		ResponseEntity responseEntity = covidServices.getDistrictList();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
//			System.out.println(responseEntity.getBody());
			JsonNode root = objectMapper.readTree(responseEntity.getBody().toString());

			Iterator<String> itr = root.fieldNames();
			while (itr.hasNext()){

				District dist = new District();

				String state = itr.next();
//				System.out.println(state+" : "+root.get(state).toString()+"\n");

				int confirmedCase = Integer.parseInt(root.get(state).get("total").get("confirmed").toString());
				int deceasedCase = Integer.parseInt(root.get(state).get("total").get("deceased").toString());
				int recoveredCase = Integer.parseInt(root.get(state).get("total").get("recovered").toString());
				int activeCase = confirmedCase - (deceasedCase + recoveredCase);

				dist.setState(state);
				dist.setDist("Complete State");
				dist.setActiveCase(activeCase);
				dist.setConfirmedCase(confirmedCase);
				dist.setDeceasedCase(deceasedCase);
				dist.setRecoveredCase(recoveredCase);

				distInfoList.add(dist);
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return distInfoList;
	}

	@RequestMapping("/wait")
	public void waiter(){
		System.out.println("wait");
		Long startTime = System.currentTimeMillis();
		IntStream.range(1,20).mapToDouble(i->i).forEach(i -> logger.info("Normal " + i));
		Long endTime = System.currentTimeMillis();

		logger.info("Stream time -----" +  (endTime - startTime));


		Long startTime1 = System.currentTimeMillis();
		IntStream.range(1,20).parallel().mapToDouble(i->i).forEach(i -> logger.info("Parallel " + i));
		Long endTime1 = System.currentTimeMillis();

		logger.info("Parallel Stream time -----" +  (endTime1 - startTime1));
	}

}
