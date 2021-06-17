package com.kakao_ex1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kakao_ex1.demo.model.AccountResult;
import com.kakao_ex1.demo.repository.AccountRepository;


@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountResult> getAccountByBranchCode(String branchCode){
        List<AccountResult> aa = accountRepository.getAccountByBranchCode(branchCode);
        return aa;
    }
}
