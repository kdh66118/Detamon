package detamon.biz;

import java.util.HashMap;
import java.util.List;

import detamon.dao.ContractDao;
import detamon.dao.ContractDaoImpl;
import detamon.dto.CompanyDto;
import detamon.dto.ContractDto;
import detamon.dto.JobBoardDto;
import detamon.dto.MemberDto;
import detamon.dto.MemberScoreDto;

public class ContractBizImpl implements ContractBiz{
	ContractDao dao = new ContractDaoImpl();
	@Override
	public MemberDto selectMember(String id) {
		return dao.selectMember(id);
	}

	@Override
	public List<ContractDto> selectContract(String id, int role) {
		return dao.selectContract(id, role);
	}

	@Override
	public List<ContractDto> selectContracted(String userId, String is_contract, int role) {
		return dao.selectContracted(userId, is_contract, role);
	}
	@Override
	public JobBoardDto selectJobBoard(int no) {
		return dao.selectJobBoard(no);
	}

	@Override
	public CompanyDto selectCompany(String id) {
		return dao.selectCompany(id);
	}

	@Override
	public int acceptContract(String is_contract, String companyId, String parttimerId, int board_no) {
		return dao.acceptContract(is_contract, companyId, parttimerId, board_no);
	}

	@Override
	public int cancleContract(String companyId, String parttimerId, int board_no) {
		return dao.cancleContract(companyId, parttimerId, board_no);
	}

	@Override
	public List<JobBoardDto> selectJobBoard_arr(int boardno) {
		return dao.selectJobBoard_arr(boardno);
	}

	@Override
	public int insertScore(String alba, int score, int role) {
		return dao.insertScore(alba, score, role);
	}

	@Override
	public int scoreCount(int boardno) {
		return dao.scoreCount(boardno);
	}

	@Override
	public int endContract(int boardno) {
		return dao.endContract(boardno);
	}

	@Override
	public ContractDto checkScore(int boardno) {
		return dao.checkScore(boardno);
	}

	@Override
	public int updateContract(int boardno, int x) {
		return dao.updateContract(boardno, x);
	}	
	
	@Override
	public int avgScore(String id) {
		return dao.avgScore(id);
	}	

	@Override
	public int updateAvgScore(String id, int avgScore) {		
		return dao.updateAvgScore(id, avgScore);
	}

	@Override
	public List<ContractDto> pageContract(int startRow, int endRow, String loginId, int role) {
		return dao.pageContract(startRow, endRow, loginId, role);
	}

	@Override
	public List<ContractDto> pageContracted(int startRow, int endRow, String loginId, int role) {
		return dao.pageContracted(startRow, endRow, loginId, role);
	}
	
	@Override
	public String comCategory(int num) {
		return dao.comCategory(num);
	}
	
	@Override
	public int checkCateNum(String comId) {
		return dao.checkCateNum(comId);
	}
	
	@Override
	public int albaAbility(String albaId, int categoryNum) {
		return dao.albaAbility(albaId, categoryNum);
	}

	//지원 자수 
	@Override
	public int selectApplyCnt(int no) {
		return dao.selectApplyCnt(no);
	}

	//알바생 구인글 지원
	@Override
	public int albaApplyContract(ContractDto dto) {
		
		//존재하는 계약??
		 int isExist = dao.isExistContrant(dto);
		 System.out.println("존재?"+isExist);
		 if(isExist == -1) {//존재하면
			 return -2;
		 }else {
			 return dao.albaApplyContract(dto);
		 }
	}

	//계약이 진행된 계약인지?
	@Override
	public ContractDto isSignContract(int no) {
		return dao.isSignContract(no);
	}










}
