package com.application.covid.controller;

import com.application.covid.Modles.District;
import com.application.covid.servies.CovidServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@RequestMapping("home")
	public String home(){
		return "Welcome";
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

//					System.out.println(distName+" : "+root.get(state).get("districtData").get(distName).toString()+"\n");

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
