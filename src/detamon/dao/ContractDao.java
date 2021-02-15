package detamon.dao;

import java.util.HashMap;
import java.util.List;

import detamon.dto.CompanyDto;
import detamon.dto.ContractDto;
import detamon.dto.JobBoardDto;
import detamon.dto.MemberDto;
import detamon.dto.MemberScoreDto;

public interface ContractDao {
	
	
	String memberSql = " SELECT * FROM MEMBER WHERE M_ID = ? ";
	String jobBoardSql = " SELECT * FROM JOBBOARD WHERE NO = ? ";
	
	String userConSql = " SELECT * FROM CONTRACT WHERE PARTTIMER_ID = ? AND IS_CONTRACT IN('N','X','Z') ORDER BY IS_CONTRACT ";
	String comConSql = " SELECT * FROM CONTRACT WHERE COMPANY_ID = ? AND IS_CONTRACT IN('N','X','Z') ORDER BY IS_CONTRACT ";
	
	String userContracedSql = " SELECT * FROM CONTRACT WHERE PARTTIMER_ID = ? AND IS_CONTRACT = ? ";
	String comContracedSql = " SELECT * FROM CONTRACT WHERE COMPANY_ID = ? AND IS_CONTRACT = ? ";
	
	String companySql = " SELECT * FROM COMPANY WHERE M_ID = ? ";
	String acceptsql = " UPDATE CONTRACT SET IS_CONTRACT = ? WHERE PARTTIMER_ID = ? AND COMPANY_ID = ? AND BOARD_NO = ? ";
	
	//	String cancleConSql = " DELETE FROM CONTRACT WHERE PARTTIMER_ID = ? AND COMPANY_ID = ? AND BOARD_NO = ? ";
	//	계약여부 Y=수락, N=신청상태, X=거절, Z=계약종료 
	String cancleConSql = " DELETE CONTRACT WHERE PARTTIMER_ID = ? AND COMPANY_ID = ? AND BOARD_NO = ? ";

	// 점수 insert 알바생 > 업체
	String insertComScoreSql = " INSERT INTO MEMBERSCORE VALUES(MEMBERSCORESEQ.NEXTVAL,?,?) ";
	// 점수 insert 알바생 > 업체
	String insertAlbaScoreSql = " INSERT INTO MEMBERSCORE VALUES(MEMBERSCORESEQ.NEXTVAL,?,?) ";
	
	
	// 게시글 번호로 점수현황 체크
	String checkScoreSql = " SELECT COUNT(*) FROM MEMBERSCORE WHERE BOARD_NO = ? ";
  
	// 계약종료 (계약여부 Y=수락, N=신청상태, X=거절, Z=계약종료) 
	String endContractSql = " UPDATE CONTRACT SET IS_CONTRACT = 'Z' WHERE BOARD_NO = ? ";
	
	//게시글 번호로 contract 조회
	String conSql = " SELECT * FROM CONTRACT WHERE BOARD_NO = ? ";
	
	//알바생>업체 평가
	String comEstimateSql = " UPDATE CONTRACT SET COMESTIMATE = 'Y' WHERE BOARD_NO = ? ";
	
	//업체>알바생 평가
	String albaEstimateSql = " UPDATE CONTRACT SET ALBAESTIMATE = 'Y' WHERE BOARD_NO = ? ";

	//알바생 업체 평점 조회
	String avgScoreSql = " SELECT FLOOR(AVG(SCORE)) FROM MEMBERSCORE WHERE ID = ? ";
	
	//업체, 알바생 평점 갱신
	String updateAvgScoreSql = " UPDATE MEMBER SET M_SCORE = ? WHERE M_ID = ? ";

	//페이징 계약확인 가져오기
	String compageContract = " SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " +
									" FROM (SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " + 
										" FROM CONTRACT  WHERE COMPANY_ID = ?  AND IS_CONTRACT IN('X','Z','N')ORDER BY CON_NO) WHERE N >= ? AND N <= ? ";
	String albapageContract = " SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " +
									" FROM (SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " + 
											" FROM CONTRACT  WHERE PARTTIMER_ID = ? AND IS_CONTRACT IN('X','Z','N') ORDER BY CON_NO) WHERE N >= ? AND N <= ? ";
	//페이징 체결된계약 가져오기
	String compageContracted = " SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " +
			" FROM (SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " + 
				" FROM CONTRACT  WHERE COMPANY_ID = ? AND IS_CONTRACT = 'Y' ORDER BY CON_NO) WHERE N >= ? AND N <= ? ";
	String albapageContracted = " SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " +
			" FROM (SELECT ROWNUM N, CON_NO, BOARD_NO, PARTTIMER_ID, COMPANY_ID, IS_CONTRACT, ALBAESTIMATE, COMESTIMATE " + 
					" FROM CONTRACT  WHERE PARTTIMER_ID = ? AND IS_CONTRACT = 'Y' ORDER BY CON_NO) WHERE N >= ? AND N <= ? ";
	
	//회사카테고리정보
	String comCategorySql = " SELECT C_NAME FROM CATEGORY WHERE C_NO = ? ";
	
	//회사 카테고리 넘버
	String CategoryNumSql = " SELECT COM_CATEGORY FROM COMPANY WHERE M_ID = ? ";
	
	//알바생 어빌리티 1씩 쌓아주기
	String albaAbility = " UPDATE PARTTIMER_ABILITY SET A_CNT = A_CNT+1 WHERE M_ID = ? AND C_NO = ? ";
			

	String selectApplyCntSql = "SELECT COUNT(*) FROM CONTRACT WHERE BOARD_NO = ? ";
	String selectAlbaListByNoSql = "SELECT PARTTIMER_ID FROM CONTRACT WHERE BOARD_NO =? ";
	String albaApplyContractSql = "INSERT INTO CONTRACT VALUES(CONTRACTSEQ.NEXTVAL , ?, ?, ?, 'N','N','N')" ;//알바생 구인글 지원 쿼리
	String isExistContractSql = "SELECT * FROM CONTRACT WHERE BOARD_NO = ? AND PARTTIMER_ID =? AND COMPANY_ID=?";//존재하는 계약?? 있으면 -1 없으면 1
	String isSignContractSql = " SELECT * FROM CONTRACT WHERE BOARD_NO =? AND ( IS_CONTRACT = 'Y' OR IS_CONTRACT = 'X' OR IS_CONTRACT ='Z')";//진행된 계약인지??
	
	/*
	 * CREATE TABLE CONTRACT(
    CON_NO NUMBER PRIMARY KEY,      --계약 번호
    BOARD_NO NUMBER NOT NULL, -- 게시글 번호
    PARTTIMER_ID VARCHAR2(20) NOT NULL, --알바생 아이디
    COMPANY_ID VARCHAR2(20) NOT NULL, --업체아이디
   	--계약여부 Y=수락, N=신청상태, X=거절, Z=계약종료
    IS_CONTRACT VARCHAR2(2) DEFAULT 'N'  NOT NULL CHECK( IS_CONTRACT IN ('Y','N','Z','X') ), --계약여부
    --업체가 알바생평가 기본값은 N 평가 후  Y로
    ALBAESTIMATE VARCHAR2(2) DEFAULT 'N'  NOT NULL CHECK( ALBAESTIMATE IN ('N','Y') ),
    --알바생이 업체평가 기본값은 N 평가 후  Y로
    COMESTIMATE VARCHAR2(2) DEFAULT 'N'  NOT NULL CHECK( COMESTIMATE IN ('N','Y') )
);
	 */
	
	
	//id값으로 회원 전체 정보 받아오기
	public MemberDto selectMember(String id);
	
	//id값으로 계약 전체 정보 받아오기
	public List<ContractDto> selectContract(String id, int role);
	
	//id값과 계약여부로 계약 전체 정보 받아오기
	public List<ContractDto> selectContracted(String userId, String is_contract, int role);
	
	//board값으로 게시글 다가져오기
	public List<JobBoardDto> selectJobBoard_arr(int boardno);
	
	//id값으로 게시글 전체 정보 받아오기
	public JobBoardDto selectJobBoard(int no);
	
	//회사 id값으로 회사 전체 정보 받아오기
	public CompanyDto selectCompany(String id); 
	
	//회사 id값과 알바생id값, 게시글 번호로 is_contract 'Y'로 수정
	public int acceptContract(String is_contract, String companyId, String parttimerId, int board_no);
	
	//회사 id값과 알바생id값 게시글 번호로 계약파기
	public int cancleContract(String companyId, String parttimerId, int board_no);
	
	//점수테이블에 점수 넣기
	public int insertScore(String alba, int score, int role);
	
	//게시판 번호로 현재 평가현황 체크
	public int scoreCount(int boardno);
	
	//서로 평가 완료 했을때 평가종료
	public int endContract(int boardno);
	
	//게시글로 점수 여부 판단
	public ContractDto checkScore(int boardno);
	
	//점수 반영 x가 1이면 알바생이 업체( comestimate )2이면 업체가 알바생( albaestimate )
	public int updateContract(int boardno, int x);
	
	//알바생 아이디로 평균점수 구하기 
	public int avgScore(String id);
	
	//알바생, 업체 점수 갱신하기 
	public int updateAvgScore(String id, int avgScore);
	
	//계약페이지 페이징 내용
	public List<ContractDto> pageContract(int startRow, int endRow, String loginId, int role);

	//체결된 페이지 내용
	public List<ContractDto> pageContracted(int startRow, int endRow, String loginId, int role);
	
	//회사 카테고리 조회
	public String comCategory(int num);
		
	//회사 Id로 카테고리 넘버 조회
	public int checkCateNum(String comId);
	
	//알바생 어빌리티 
	public int albaAbility(String albaId, int categoryNum);

	//지원자수 받아오기
	public int selectApplyCnt(int no);

	//지원자 아이디 리스트
	public List<String> selectAlbaListByNo(int no);
	
	//알바생 구인글 지원
	public int albaApplyContract(ContractDto dto);

	//존재하는 계약?? 있으면-1 없으면 1
	public int isExistContrant(ContractDto dto);

	//진행된 계약인지???
	public ContractDto isSignContract(int no);
}

