package detamon.biz;

import java.util.HashMap;
import java.util.List;

import detamon.dto.CompanyDto;
import detamon.dto.ContractDto;
import detamon.dto.JobBoardDto;
import detamon.dto.MemberDto;
import detamon.dto.MemberScoreDto;

public interface ContractBiz {
	//id값으로 회원 전체 정보 받아오기
	public MemberDto selectMember(String id);
	
	//id값으로 계약 전체 정보 받아오기
	public List<ContractDto> selectContract(String id, int role);
	
	//id값과 계약여부로 계약 전체 정보 받아오기
	public List<ContractDto> selectContracted(String userId, String is_contract, int role);
	
	//id값으로 게시글 전체 정보 받아오기
	public JobBoardDto selectJobBoard(int no);
	
	//board값으로 게시글 다가져오기
	public List<JobBoardDto> selectJobBoard_arr(int boardno);
	
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
	
	//알바생,업체 아이디로 평균점수 구하기 
	public int avgScore(String id);
	
	//알바생, 업체 점수 갱신하기 
	public int updateAvgScore(String id, int avgScore);
	
	//계약페이지 페이징 내용
	public List<ContractDto> pageContract(int startRow, int endRow, String loginId, int role);
	
	//체결된 페이지 내용
	public List<ContractDto> pageContracted(int startRow, int endRow, String loginId, int role);
	
	//회사 카테고리 조회
	public String comCategory(int num);
	
	//알바생 어빌리티 
	public int albaAbility(String albaId, int categoryNum);
	
	//회사 Id로 카테고리 넘버 조회
	public int checkCateNum(String comId);

	//지원자수 게시글번호로 받기
	public int selectApplyCnt(int no);

	//알바생 구인글 지원
	public int albaApplyContract(ContractDto dto);

	
	//계약이 진행된 계약인지?
	public ContractDto isSignContract(int no);

		
}
