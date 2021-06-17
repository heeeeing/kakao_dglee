package com.kakao_ex1.demo.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.kakao_ex1.demo.model.AccountResult;
import com.kakao_ex1.demo.model.YearAmountResult;
import com.kakao_ex1.demo.model.YearNoTransResult;
import com.kakao_ex1.demo.service.AccountService;
import com.kakao_ex1.demo.service.ManagementService;
import com.kakao_ex1.demo.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Sample")
@RestController
@RequestMapping("/test/")
public class SampleController {

	@Autowired
    private AccountService accountService;
    
	@Autowired
    private TransactionService transactionService;
    
	@Autowired
    private ManagementService managementService;

    @ApiOperation(value = "sample")
    @GetMapping(value = "/acount")
    public List<AccountResult> getAccountInfo(String branchCode) {
        return accountService.getAccountByBranchCode(branchCode);
    }

    @ApiOperation(value = "2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출")
    @GetMapping(value = "/YearAmount")
    public List<YearAmountResult> getYearAmountSumMax() {
    	return transactionService.getYearAmountSumMax();
    }

    @ApiOperation(value = "2018년 또는 2019년에 거래가 없는 고객을 추출")
    @GetMapping(value = "/YearNoTrans")
    public List<YearNoTransResult> getYearNoTrans() {
    	return transactionService.getYearNoTrans();
    }
    
    @ApiOperation(value = "연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력")
    @GetMapping(value = "/YearMngAmount")
    public String getYearMngAmountSumMax() {
    	return managementService.getYearMngAmountSumMax();
    }
}
