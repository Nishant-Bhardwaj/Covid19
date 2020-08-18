package com.application.covid.controller;

import com.application.covid.Modles.District;
import com.application.covid.servies.CovidServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CovidController {

	@Autowired
	CovidServices covidServices;

	@Autowired
	District district;

	@RequestMapping(value = {"home/{user}", "/home"})
	public String home(@PathVariable(value="user",required = false) String userName){
		if(userName==null){
			userName="guest";
		}
		return "Welcome "+userName;
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

				String state = itr.next();
//				System.out.println(state+" : "+root.get(state).toString()+"\n");

				Iterator<String> distItr = root.get(state).get("districtData").fieldNames();

				while (distItr.hasNext()){
					District dist = new District();
					String distName = distItr.next();

					int activeCase = Integer.parseInt(root.get(state).get("districtData").get(distName).get("active").toString());
					int confirmedCase = Integer.parseInt(root.get(state).get("districtData").get(distName).get("confirmed").toString());
					int deceasedCase = Integer.parseInt(root.get(state).get("districtData").get(distName).get("deceased").toString());
					int recoveredCase = Integer.parseInt(root.get(state).get("districtData").get(distName).get("recovered").toString());

					dist.setState(state);
					dist.setDist(distName);
					dist.setActiveCase(activeCase);
					dist.setConfirmedCase(confirmedCase);
					dist.setDeceasedCase(deceasedCase);
					dist.setRecoveredCase(recoveredCase);

					distInfoList.add(dist);
				}
			}

		}catch (Exception e){
			e.printStackTrace();
		}
		return distInfoList;
	}

}
