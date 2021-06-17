package com.kakao_ex1.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kakao_ex1.demo.model.ManagementResult;
import com.kakao_ex1.demo.repository.ManagementRepository;


@Service
@Transactional
public class ManagementService {
    
    @Autowired
    private ManagementRepository ManagementRepository;

	public String getYearMngAmountSumMax() {
		List<ManagementResult> list = ManagementRepository.getYearMngAmountSumMax();
		
		JsonArray resultJson = new JsonArray();
		JsonObject yearDataJson = new JsonObject();
		JsonObject dataJson = new JsonObject();
		JsonObject dataJsonList = new JsonObject();
		
		for(int a=0; a<list.size(); a++) {
			ManagementResult mng = (ManagementResult) list.get(a);
			if(a==0) {
				yearDataJson.addProperty("year", mng.getTransDate());
				
				dataJsonList.addProperty("brName", mng.getBankName());
				dataJsonList.addProperty("brCode", mng.getBranchCode());
				dataJsonList.addProperty("sumAmt", mng.getSumAmt());
				dataJson.add("dataList", dataJsonList);
				
				
				resultJson.addAll(resultJson);
			}else {
				if(list.get(a).getTransDate().equals(list.get(a-1).getTransDate())) {
					
				}else {
					
				}
			}
			
		}
		
		System.out.println(resultJson);
    	return resultJson.toString();
	}
}
