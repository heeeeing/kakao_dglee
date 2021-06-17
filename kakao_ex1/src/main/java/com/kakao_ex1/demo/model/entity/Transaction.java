package com.kakao_ex1.demo.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    
    private String transDate;

    @Id
    private String accountNo;

    private String transNo;
    
    private String amount;

    private String taxNo;

    private String cancelYn;
    
    private String accountName;
    
    private String sumAmt;

}
