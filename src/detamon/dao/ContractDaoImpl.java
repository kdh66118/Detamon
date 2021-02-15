package detamon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



import detamon.dto.CompanyDto;
import detamon.dto.ContractDto;
import detamon.dto.JobBoardDto;
import detamon.dto.MemberDto;
import detamon.dto.MemberScoreDto;

import static common.JDBCTemplate.*;
public class ContractDaoImpl implements ContractDao {

	@Override
	public MemberDto selectMember(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto res = new MemberDto();
		
		try {
			pstm = con.prepareStatement(memberSql);
			pstm.setString(1, id);
			System.out.println("query 준비 "+ memberSql);
			
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
			while(rs.next()) {
				res.setM_no(rs.getInt(1));
				res.setM_id(rs.getString(2));
				res.setM_pw(rs.getString(3));
				res.setM_name(rs.getString(4));
				res.setM_rno(rs.getString(5));
				res.setM_phone(rs.getString(6));
				res.setM_addr(rs.getString(7));
				res.setM_email(rs.getString(8));
				res.setM_gender(rs.getString(9));
				res.setM_role(rs.getInt(10));
				res.setM_score(rs.getDouble(11));
				res.setM_enabled(rs.getString(12));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	//사용자의 계약중인 내용 가져오기
	@Override
	public List<ContractDto> selectContract(String id, int role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ContractDto> res = new ArrayList<ContractDto>();
		String sql = (role<2)?userConSql:comConSql;
		System.out.println("등급"+role);
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			System.out.println("query 준비"+sql);
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
			
			while(rs.next()) {
				ContractDto tmp = new ContractDto();
				tmp.setCon_no(rs.getInt(1));
				tmp.setBoard_no(rs.getInt(2));
				tmp.setParttimer_id(rs.getString(3));
				tmp.setCompany_id(rs.getString(4));
				tmp.setIs_contract(rs.getString(5));
				tmp.setAlbaestimate(rs.getString(6));
				tmp.setComestimate(rs.getString(7));
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("query준비/실행 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	//사용자의 체결된 계약 가져오기
	@Override
	public List<ContractDto> selectContracted(String userId, String is_contract, int role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ContractDto> res = new ArrayList<ContractDto>();
		String sql = (role<2)?userContracedSql:comContracedSql;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userId);
			pstm.setString(2, is_contract);
			System.out.println("query 준비"+sql);
			
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
		
			while(rs.next()) {
				ContractDto tmp = new ContractDto();
				tmp.setCon_no(rs.getInt(1));
				tmp.setBoard_no(rs.getInt(2));
				tmp.setParttimer_id(rs.getString(3));
				tmp.setCompany_id(rs.getString(4));
				tmp.setIs_contract(rs.getString(5));
				tmp.setAlbaestimate(rs.getString(6));
				tmp.setComestimate(rs.getString(7));
					
				res.add(tmp);
			}			
		} catch (SQLException e) {
			System.out.println("query준비/실행 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	
	//사용자의 게시글 가져오기
	@Override
	public JobBoardDto selectJobBoard(int no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		JobBoardDto res = new JobBoardDto();
		
		try {
			pstm = con.prepareStatement(jobBoardSql);
			pstm.setInt(1, no);
			System.out.println("query 준비 "+ jobBoardSql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				res.setNo(rs.getInt(1));
				res.setWriter(rs.getString(2));
				res.setTitle(rs.getString(3));
				res.setContent(rs.getString(4));
				res.setStart_date(rs.getDate(5));
				res.setEnd_date(rs.getDate(6));
				res.setStart_time(rs.getTimestamp(7));
				res.setEnd_time(rs.getTimestamp(8));
				res.setMoney(rs.getInt(9));
				res.setLoc(rs.getInt(10));
				res.setCategory(rs.getInt(11));
				res.setRegDate(rs.getDate(12));
				res.setCnt(rs.getInt(13));
				res.setType_no(rs.getInt(14));
			}
			System.out.println("query 실행 및 리턴 ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
		return res;
	}
	@Override
	public CompanyDto selectCompany(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CompanyDto res = new CompanyDto();
		
		try {
			pstm = con.prepareStatement(companySql);
			pstm.setString(1, id);
			System.out.println("query 준비 : "+ companySql +"id값:"+id);
			
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
			while(rs.next()) {
				res.setM_id(rs.getString(1));
				res.setCom_name(rs.getString(2));
				res.setCom_location(rs.getInt(3));
				res.setCom_addr(rs.getString(4));
				res.setCom_category(rs.getInt(5));
				res.setCom_phone(rs.getString(6));
				res.setCom_score(rs.getDouble(7));
			}
			
		} catch (SQLException e) {
			System.out.println("query 준비 및 실행 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	@Override
	public int acceptContract(String is_contract, String companyId, String parttimerId, int board_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		System.out.println("is_contract:"+is_contract+", companyId"+companyId+", parttimerId : "+parttimerId+", boardno:"+board_no);
		try {
			pstm = con.prepareStatement(acceptsql);
			pstm.setString(1, "Y");
			pstm.setString(2, parttimerId);
			pstm.setString(3, companyId);
			pstm.setInt(4, board_no);
			System.out.println("query 준비 "+ acceptsql);
			
			res = pstm.executeUpdate();
			System.out.println("query 실행및 리턴");
			if(res>0) {
				commit(con);
			}
			System.out.println("res:"+res);
		} catch (SQLException e) {

			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
		return res;
	}
	@Override
	public int cancleContract(String companyId, String parttimerId, int board_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(cancleConSql);
			pstm.setString(1, parttimerId);
			pstm.setString(2, companyId);
			pstm.setInt(3, board_no);
			System.out.println("query 준비"+cancleConSql);
			
			res = pstm.executeUpdate();
			System.out.println("query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
		return res;
	}
	@Override
	public List<JobBoardDto> selectJobBoard_arr(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<JobBoardDto> res = new ArrayList<JobBoardDto>();
		
		try {
			pstm = con.prepareStatement(jobBoardSql);
			pstm.setInt(1, boardno);
			System.out.println("query 준비 "+ jobBoardSql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				JobBoardDto tmp = new JobBoardDto();
				tmp.setNo(rs.getInt(1));
				tmp.setWriter(rs.getString(2));
				tmp.setTitle(rs.getString(3));
				tmp.setContent(rs.getString(4));
				tmp.setStart_date(rs.getDate(5));
				tmp.setEnd_date(rs.getDate(6));
				tmp.setStart_time(rs.getDate(7));
				tmp.setEnd_time(rs.getDate(8));
				tmp.setMoney(rs.getInt(9));
				tmp.setLoc(rs.getInt(10));
				tmp.setCategory(rs.getInt(11));
				tmp.setRegDate(rs.getDate(12));
				tmp.setCnt(rs.getInt(13));
				tmp.setType_no(rs.getInt(14));
				
				res.add(tmp);
			}
			System.out.println("query 실행 및 리턴 ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
		return res;
	
	}
	@Override
	public int insertScore(String alba, int score, int role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		//role가 2이하이면 알바생 아니면 업체 
		String sql = (role<2)?insertComScoreSql:insertAlbaScoreSql;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, alba);
			pstm.setInt(2, score);
			System.out.println("query 준비 "+sql);
			
			res = pstm.executeUpdate();
			System.out.println("query실행");
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("db 종료");
		}
		return res;
	}
	@Override
	public int scoreCount(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(checkScoreSql);
			pstm.setInt(1, boardno);
			System.out.println("query 준비"+checkScoreSql);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
			System.out.println(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
	
		return res;
	}
	@Override
	public int endContract(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(endContractSql);
			pstm.setInt(1, boardno);
			System.out.println("query 준비"+ endContractSql);
			
			res = pstm.executeUpdate();
			if(res>0) {
				commit(con);
			}
			System.out.println("query 실행");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("db 종료");
		}
		return res;
	}
	@Override
	public ContractDto checkScore(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		ContractDto res = new ContractDto();
		
		try {
			pstm = con.prepareStatement(conSql);
			pstm.setInt(1, boardno);
			System.out.println("query 준비 "+conSql);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
			res.setCon_no(rs.getInt(1));
			res.setBoard_no(rs.getInt(2));
			res.setParttimer_id(rs.getString(3));
			res.setCompany_id(rs.getString(4));
			res.setIs_contract(rs.getString(5));
			res.setAlbaestimate(rs.getString(6));
			res.setComestimate(rs.getString(7));
				
			System.out.println("query 실행");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstm);
			close(con);
			System.out.println("db 종료");
		}
		return res;
	}
	
	@Override
	public int updateContract(int boardno, int x) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = (x==2)?albaEstimateSql:comEstimateSql;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			System.out.println("query 준비"+sql);
			
			res = pstm.executeUpdate();
			System.out.println("query 실행");
			if(res>0) {
				commit(con);
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}	
		return res;
	}
	
	@Override
	public int avgScore(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;

		int res = 0;
		
		try {
			pstm = con.prepareStatement(avgScoreSql);
			pstm.setString(1, id);
			System.out.println("query 준비"+avgScoreSql);
			
			rs = pstm.executeQuery();
			while(rs.next()) {
				res = rs.getInt(1);
			}
			System.out.println(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}						
		return res;
	}
	@Override
	public int updateAvgScore(String id, int avgScore) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updateAvgScoreSql);
			pstm.setInt(1, avgScore);
			pstm.setString(2, id);
			System.out.println("query 준비 "+updateAvgScoreSql );
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}
			System.out.println("query 실행");
					
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}

	@Override
	public List<ContractDto> pageContract(int startRow, int endRow, String loginId, int role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ContractDto> res = new ArrayList<ContractDto>();
		String sql = (role<2)?albapageContract:compageContract;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, loginId);
			pstm.setInt(2, startRow);
			pstm.setInt(3, endRow);
			
			System.out.println("query 준비"+sql);
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
			
			while(rs.next()) {
				ContractDto tmp = new ContractDto();
				tmp.setCon_no(rs.getInt(2));
				tmp.setBoard_no(rs.getInt(3));
				tmp.setParttimer_id(rs.getString(4));
				tmp.setCompany_id(rs.getString(5));
				tmp.setIs_contract(rs.getString(6));
				tmp.setAlbaestimate(rs.getString(7));
				tmp.setComestimate(rs.getString(8));
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("query준비/실행 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	
	@Override
	public List<ContractDto> pageContracted(int startRow, int endRow, String loginId, int role) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<ContractDto> res = new ArrayList<ContractDto>();
		String sql = (role<2)?albapageContracted:compageContracted;

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, loginId);
			pstm.setInt(2, startRow);
			pstm.setInt(3, endRow);
			
			System.out.println("query 준비"+sql);
			rs = pstm.executeQuery();
			System.out.println("query 실행 및 리턴");
			
			while(rs.next()) {
				ContractDto tmp = new ContractDto();
				tmp.setCon_no(rs.getInt(2));
				tmp.setBoard_no(rs.getInt(3));
				tmp.setParttimer_id(rs.getString(4));
				tmp.setCompany_id(rs.getString(5));
				tmp.setIs_contract(rs.getString(6));
				tmp.setAlbaestimate(rs.getString(7));
				tmp.setComestimate(rs.getString(8));
				res.add(tmp);
			}
			System.out.println("res?"+res);
		} catch (SQLException e) {
			System.out.println("query준비/실행 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	@Override
	public String comCategory(int num) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		try {
			pstm = con.prepareStatement(comCategorySql);
			pstm.setInt(1, num);
			System.out.println("query 준비"+comCategorySql);
			
			rs = pstm.executeQuery();
			System.out.println("query 실행"+rs );
			while(rs.next()) {
				res = rs.getString(1);
			}
			System.out.println("회사카테고리 조회 결과는" + res);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}	
		return res;
	}
	@Override
	public int checkCateNum(String comId) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		 
		try {
			pstm = con.prepareStatement(CategoryNumSql);
			pstm.setString(1, comId);
			System.out.println("query 준비"+CategoryNumSql);
			rs = pstm.executeQuery();
			System.out.println("queyr 실행");
			while(rs.next()) {
				res = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("query 준비 실행 에럴");
			e.printStackTrace();
		}finally {
			System.out.println("db종료");
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	
	@Override
	public int albaAbility(String albaId, int categoryNum) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;	
		try {
			pstm = con.prepareStatement(albaAbility);
			pstm.setString(1, albaId);
			pstm.setInt(2, categoryNum);
			System.out.println("query 준비"+albaAbility);
			
			res = pstm.executeUpdate();
			System.out.println("query 실행");
			if(res > 0) {
				commit(con);
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	//지원 자수
	@Override
	public int selectApplyCnt(int no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			pstm= con.prepareStatement(selectApplyCntSql);
			pstm.setInt(1, no);
			
			rs = pstm.executeQuery();
			while( rs.next()) {
				cnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return cnt;
	}
	
	//지원자 아이디 리스트 반환
	@Override
	public List<String> selectAlbaListByNo(int no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs =null;
		List<String> albaList = new ArrayList<String>();
		
		try {
			pstm = con.prepareStatement(selectAlbaListByNoSql);
			pstm.setInt(1, no);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				String id = rs.getNString(1);
				albaList.add(id);
			while ( rs.next() ) {
				String id2 = rs.getNString(1);
				albaList.add(id2);
			}
			}else {
				albaList=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return albaList;
	}
	
	//알바생 구인글 지원 
	@Override
	public int albaApplyContract(ContractDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(albaApplyContractSql);
			pstm.setInt(1, dto.getBoard_no());
			pstm.setString(2, dto.getParttimer_id());
			pstm.setNString(3, dto.getCompany_id());
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	@Override
	public int isExistContrant(ContractDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		int res = 0;
		ResultSet rs = null;
		
		try {
			pstm = con.prepareStatement(isExistContractSql);
			pstm.setInt(1, dto.getBoard_no());
			pstm.setNString(2, dto.getParttimer_id());
			pstm.setNString(3, dto.getCompany_id());
			
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				res = -1;
			}else {
				res = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	
	//진행된 계약인지??
	@Override
	public ContractDto isSignContract(int no) {
		Connection con =getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ContractDto dto = null;
		
		try {
			pstm = con.prepareStatement(isSignContractSql);
			pstm.setInt(1, no);
			
			rs = pstm.executeQuery();
			while(rs.next() ) {
				dto = new ContractDto(rs.getInt(1), rs.getInt(2), rs.getNString(3), rs.getNString(4), rs.getNString(5), rs.getNString(6), rs.getNString(7));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if( dto != null) {
				close(rs);
				
			}
			close(pstm);
			close(con);
		}
		
		return dto;
	}





}
