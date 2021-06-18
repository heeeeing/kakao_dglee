package com.kakao_ex1.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kakao_ex1.demo.model.ConslidtMngAmountResult;
import com.kakao_ex1.demo.model.YearMngAmount;
import com.kakao_ex1.demo.repository.ManagementRepository;


@Service
@Transactional
public class ManagementService {
    
    @Autowired
    private ManagementRepository ManagementRepository;

    public String getYearMngAmountSumMax() {
		List<YearMngAmount> list = ManagementRepository.getYearMngAmountSumMax();
		JsonArray resultJson = new JsonArray();
		List<String> yearList = new ArrayList<String>();
		
		int i = 0;
		for(YearMngAmount mng : list) {
			if(i == 0) {
				yearList.add( mng.getTransDate());
			}else if(i > 0) {
				if(!mng.getTransDate().equals(list.get(i-1).getTransDate())) {
					yearList.add( mng.getTransDate());
				}
			}
			i++;
		}
		
		for(String year : yearList) {
			JsonObject yearDataJson = new JsonObject();
			JsonArray dataList = new JsonArray();
			yearDataJson.addProperty("year", year);
			for(YearMngAmount mng : list) {
				JsonObject mngJson = new JsonObject();
				if(year.equals(mng.getTransDate())) {
					mngJson.addProperty("brName", mng.getBankName());
					mngJson.addProperty("brCode", mng.getBranchCode());
					mngJson.addProperty("sumAmt", mng.getSumAmt()); 
					dataList.add(mngJson);
				}
			}
			yearDataJson.add("dataList", dataList);
			resultJson.add(yearDataJson);
		}
		
    	return resultJson.toString();
	}

	public String getConslidtMngAmountSum(String branchCode) {
		JsonObject result = new JsonObject();
		if(branchCode.equals("분당점")) {
			result.addProperty("http status", "404");
			result.addProperty("code", "404");
			result.addProperty("메세지", "br code not found error");
		}else {
			ConslidtMngAmountResult aa = ManagementRepository.getAccountByBranchCode(branchCode);
			result.addProperty("brName", aa.getBankName());
			result.addProperty("brCode", aa.getBranchCode());
			result.addProperty("sumAmt", aa.getSumAmt());
		}
        return result.toString();
	}
}
