package com.kakao_ex1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.kakao_ex1.demo.model.YearMngAmountResult;
import com.kakao_ex1.demo.model.YearNoTransResult;
import com.kakao_ex1.demo.repository.TransactionRepository;


@Service
@Transactional
public class TransactionService {

    @Autowired
    private TransactionRepository yearAmountRepository;
    
    public List<YearMngAmountResult> getYearAmountSumMax(){
        List<YearMngAmountResult> list = yearAmountRepository.getYearAmountSumMax();
        return list;
    }
    
    public List<YearNoTransResult> getYearNoTrans(){
    	List<YearNoTransResult> list = yearAmountRepository.getYearNoTrans();
    	return list;
    }
}
