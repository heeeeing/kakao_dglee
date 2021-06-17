package com.kakao_ex1.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kakao_ex1.demo.model.YearAmountResult;
import com.kakao_ex1.demo.model.YearNoTransResult;
import com.kakao_ex1.demo.model.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, String> {
	//transDate=20180203, accountNo=11111111, transNo=1, amount=500000, taxNo=1000
	//1.2018년, 2019년 각 연도별 합계 금액이 가장 많은 고객을 추출하는 API 개발.(단, 취소여부가 ‘Y’ 거래는 취소된 거래임, 합계 금액은 거래금액에서 수수료를 차감한 금액임)
	@Query(value = "SELECT\n"
    		+ "     a.trans_date AS transDate\n"
    		+ "     ,a.account_no AS accountNo\n"
    		+ "     ,c.account_name AS accountName\n"
    		+ "     ,a.sum_amt AS sumAmt\n"
    		+ " FROM (\n"
    		+ "     SELECT\n"
    		+ "         SUBSTR(a.trans_date,0,4) as trans_date\n"
    		+ "        ,account_no\n"
    		+ "        ,SUM(CAST(amount AS INTEGER)-CAST(tax_no AS INTEGER)) as sum_amt\n"
    		+ "     FROM Transaction AS a\n"
    		+ "     WHERE SUBSTR(trans_date,0,4) IN ('2018','2019')\n"
    		+ "     AND cancel_yn = 'N'\n"
    		+ "     GROUP BY SUBSTR(trans_date,0,4),account_no\n"
    		+ " ) AS a INNER JOIN (\n"
    		+ "     SELECT\n"
    		+ "             max(sum_amt) as sum_amt\n"
    		+ "         FROM (\n"
    		+ "             SELECT\n"
    		+ "                 SUBSTR(trans_date,0,4) as trans_date\n"
    		+ "                 ,account_no\n"
    		+ "                 ,Sum(CAST(amount AS INTEGER)-CAST(tax_no AS INTEGER)) as sum_amt\n"
    		+ "             FROM Transaction\n"
    		+ "                 WHERE SUBSTR(trans_date,0,4) in ('2018','2019')\n"
    		+ "                 AND cancel_yn = 'N'\n"
    		+ "             GROUP BY account_no, SUBSTR(trans_date,0,4)\n"
    		+ "         )GROUP BY trans_date\n"
    		+ " ) AS b\n"
    		+ " ON a.sum_amt = b.sum_amt\n"
    		+ " LEFT OUTER JOIN ACCOUNT c\n"
    		+ " ON a.account_no = c.account_no\n"
    		+ " ORDER BY a.trans_date ASC\n", nativeQuery = true)
	List<YearAmountResult> getYearAmountSumMax();

	
	@Query(value = "SELECT \n"
			+ "    SUBSTR(a.trans_date,0,4) as transDate    \n"
			+ "    ,b.account_Name AS accountName\n"
			+ "    ,a.account_no AS accountNo\n"
			+ "FROM  Transaction AS a\n"
			+ "LEFT OUTER JOIN ACCOUNT AS b\n"
			+ "ON a.account_no = b.account_no\n"
			+ "WHERE a.cancel_yn = 'Y'\n"
			+ "AND SUBSTR(a.trans_date,0,4) = '2018' or SUBSTR(a.trans_date,0,4) = '2019'\n"
			+ "group by SUBSTR(a.trans_date,0,4), b.account_name, a.account_no", nativeQuery = true)
	List<YearNoTransResult> getYearNoTrans();
    
    
    //2.2018년 또는 2019년에 거래가 없는 고객을 추출하는 API 개발.(취소여부가 ‘Y’ 거래는 취소된 거래임)


}
