package com.kakao_ex1.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kakao_ex1.demo.model.ConslidtMngAmountResult;
import com.kakao_ex1.demo.model.YearMngAmount;
import com.kakao_ex1.demo.model.entity.ManagementPoint;


public interface ManagementRepository extends JpaRepository<ManagementPoint, String> {
	//3.	연도별 관리점별 거래금액 합계를 구하고 합계금액이 큰 순서로 출력하는 API 개발.( 취소여부가 ‘Y’ 거래는 취소된 거래임)
	
	@Query(value = "SELECT\n"
			+ "        a.trans_date AS transDate\n"
			+ "         ,a.bank_name AS bankName\n"
			+ "         ,a.branch_code AS branchCode\n"
			+ "         ,sum(a.amt) AS sumAmt\n"
			+ "    FROM(\n"
			+ "    SELECT\n"
			+ "        SUBSTR(a.trans_date,0,4) AS trans_date\n"
			+ "        ,CAST(a.amount AS INTEGER)-CAST(a.tax_no AS INTEGER) AS amt\n"
			+ "        ,c.bank_name\n"
			+ "        ,c.branch_code\n"
			+ "    FROM TRANSACTION a \n"
			+ "    INNER JOIN ACCOUNT b\n"
			+ "    ON a.account_no = b.account_no\n"
			+ "    INNER JOIN MANAGEMENT_POINT c\n"
			+ "    ON b.branch_code = c.branch_code\n"
			+ "    where a.cancel_yn = 'N'\n"
			+ ") AS a \n"
			+ "GROUP BY a.trans_date, a.bank_name, a.branch_code\n"
			+ "ORDER BY a.trans_date ASC", nativeQuery = true)
	List<YearMngAmount> getYearMngAmountSumMax();

	@Query(value = "SELECT \n"
			+ "    c.bank_name AS bankName\n"
			+ "    ,c.branch_code AS branchCode\n"
			+ "    ,Sum(CAST(a.amount AS INTEGER)-CAST(a.tax_no AS INTEGER)) as sumAmt\n"
			+ "FROM TRANSACTION a\n"
			+ "INNER JOIN ACCOUNT b\n"
			+ "ON a.account_no = b.account_no\n"
			+ "INNER JOIN MANAGEMENT_POINT c\n"
			+ "ON b.branch_code = c.branch_code\n"
			+ "WHERE a.cancel_yn = 'N'\n"
			+ "AND c.bank_name = :branchCode\n"
			+ "GROUP BY c.bank_name,c.branch_code", nativeQuery = true)
	ConslidtMngAmountResult getAccountByBranchCode(@Param("branchCode") String branchCode);
}
//bankName=A, branchCode=판교점
