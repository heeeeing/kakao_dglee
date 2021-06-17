package com.kakao_ex1.demo.infra;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.kakao_ex1.demo.model.entity.Account;
import com.kakao_ex1.demo.model.entity.ManagementPoint;
import com.kakao_ex1.demo.model.entity.Transaction;
import com.kakao_ex1.demo.repository.AccountRepository;
import com.kakao_ex1.demo.repository.ManagementRepository;
import com.kakao_ex1.demo.repository.TransactionRepository;

@Component
public class InitData {

    @Autowired
    AccountRepository accountRepository;
    
    @Autowired
    TransactionRepository transactionRepository;
    
    @Autowired
    ManagementRepository managementRepository;

    @PostConstruct
    private void initAccount() throws IOException {
        if (accountRepository.count() == 0) {
            Resource resource = new ClassPathResource("과제1_데이터_계좌정보.csv");
            List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                .build();
                    }).collect(Collectors.toList());
            accountRepository.saveAll(accountList);
        }
    }

    @PostConstruct
    private void initTransaction() throws IOException {
    	if (transactionRepository.count() == 0) {
    		Resource resource = new ClassPathResource("과제1_데이터_거래내역.csv");
    		List<Transaction> transactionList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
    				.stream().skip(1).map(line -> {
    					String[] split = line.split(",");
    					return Transaction.builder().transDate(split[0]).accountNo(split[1]).transNo(split[2]).amount(split[3]).taxNo(split[4]).cancelYn(split[5])
    							.build();
    				}).collect(Collectors.toList());
    		transactionRepository.saveAll(transactionList);
    	}
    }
    
    @PostConstruct
    private void initManagementPoint() throws IOException {
    	if (managementRepository.count() == 0) {
    		Resource resource = new ClassPathResource("과제1_데이터_관리점정보.csv");
    		List<ManagementPoint> managementPointList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
    				.stream().skip(1).map(line -> {
    					String[] split = line.split(",");
    					return ManagementPoint.builder().branchCode(split[0]).bankName(split[1])
    							.build();
    				}).collect(Collectors.toList());
    		managementRepository.saveAll(managementPointList);
    	}
    }
}
