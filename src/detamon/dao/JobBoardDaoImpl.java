package detamon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import detamon.dto.CategoryDto;
import detamon.dto.JobBoardDto;
import detamon.dto.LocationDto;
import detamon.dto.ParttimerAbilityDto;

import static common.JDBCTemplate.*;

public class JobBoardDaoImpl implements JobBoardDao {

	//모든 게시글 검색
	@Override
	public List<JobBoardDto> selectAllBoard() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<JobBoardDto> list = new ArrayList<JobBoardDto>();
		
		try {
			pstm = con.prepareStatement(selectAllBoardSql);
			rs = pstm.executeQuery();
			
			while( rs.next() ) {
				JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
						rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;
	}

	@Override
	public List<JobBoardDto> selectStoreBoard() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<JobBoardDto> list = new ArrayList<JobBoardDto>();
		
		try {
			pstm = con.prepareStatement(selectstoreBoard);
			rs = pstm.executeQuery();
			
			while( rs.next() ) {
				JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
						rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;

	}
	// ------------------------------알바 찾기 게시글 검색 -----------------------------------------------------------------
	@Override
	public List<JobBoardDto> selectalbaBoard() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<JobBoardDto> list = new ArrayList<JobBoardDto>();
		
		try {
			pstm = con.prepareStatement(selectalbaBoard);
			rs = pstm.executeQuery();
			
			while( rs.next() ) {
				JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
						rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
			
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return list;

	}
	//-----------------------------카테고리 검색(아직 이상해서 손보고 붙일 예정)  ------------------------------------
	@Override
	public List<JobBoardDto> selectcategory(int loc,int category) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<JobBoardDto> list = new ArrayList<JobBoardDto>();
		
		try {
			pstm = con.prepareStatement(selectcategory);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	//모든 로케이션 반환
		@Override
		public List<LocationDto> selectAllLoc() {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<LocationDto> locList = new ArrayList<LocationDto>();
			
			try {
				pstm = con.prepareStatement(selectAllLocSql);
				rs = pstm.executeQuery();
				
				while( rs.next() ) {
					LocationDto dto = new LocationDto(rs.getInt(1),rs.getString(2));
					locList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return locList;
		}

		//모든 카테고리 반환
		@Override
		public List<CategoryDto> selectAllCategory() {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<CategoryDto> categoryList = new ArrayList<CategoryDto>();
			
			try {
				pstm = con.prepareStatement(selectAllCategorySql);
				rs = pstm.executeQuery();
				
				while( rs.next() ) {
					CategoryDto dto = new CategoryDto(rs.getInt(1),rs.getString(2));
					categoryList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return categoryList;
		}

		
		//조회수 TOP5 게시물
		@Override
		public List<JobBoardDto> selectCntList(int type_no) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> cntList = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(selectCntListSql);
				pstm.setInt(1, type_no);
				
				rs = pstm.executeQuery();
				
				while( rs.next() ) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getNString(3), rs.getString(4) , rs.getNString(5), rs.getTimestamp(6), rs.getTimestamp(7),rs.getTimestamp(8),
										rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					
					cntList.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			return cntList;
		}
		//글번호로 작성자 가져오기
		@Override
		public String selectWriterByNo(int no) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			String writer = null;
			
			try {
				pstm= con.prepareStatement(selectWriterByNoSql);
				pstm.setInt(1, no);
				
				rs = pstm.executeQuery();
				
				while(rs.next()) {
					writer = rs.getNString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				close(rs);
				close(pstm);
				close(con);
			}
			
			return writer;
		}

		//글번호로 게시글 가져오기
		@Override
		public JobBoardDto selectOneByNo(int no) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			JobBoardDto dto = null;
			
			try {
				pstm = con.prepareStatement(selectOneByNo);
				pstm.setInt(1, no);
				rs = pstm.executeQuery();
				
				while( rs.next() ) {
					dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
				}
						
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
					
			return dto;
		}
		
		//조회수 증가
		@Override
		public int plusBoardCnt(int no) {
			Connection con =getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			try {
				pstm = con.prepareStatement(plusBoardCntSql);
				pstm.setInt(1, no);
				res=pstm.executeUpdate();
				
				if ( res > 0) {
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
		//업체찾기 카테고리 null값 없을시 
		@Override
		public List<JobBoardDto> selectallcategory(int loc, int category) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			
			
			try {
				pstm = con.prepareStatement(selectallcategory);
				pstm.setInt(1, loc);
				pstm.setInt(2, category);
		
				rs =pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
				System.out.println("아");
			}

		
			return list;
		}

		@Override
			//업체 카테고리 category null 검색
			public List<JobBoardDto> selectloccategory(int loc) {
				Connection con = getConnection();
				PreparedStatement pstm =null;
				ResultSet rs = null;
				List<JobBoardDto> list = new ArrayList<JobBoardDto>();
				
				try {
					pstm = con.prepareStatement(selectloccategory);
					pstm.setInt(1, loc);
					
					rs= pstm.executeQuery();
					while(rs.next()) {
						JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
								rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
						list.add(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(pstm);
					close(rs);
					close(con);
					
				}
				
				
				
				return list;
			}
		//업체 카테고리검색 loc null 값 일때
		@Override
		public List<JobBoardDto> selectsectorcategory(int category) {
			Connection con = getConnection();
			PreparedStatement pstm =null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(selectsectorcategory);
				pstm.setInt(1, category);
				
				rs= pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
			}
			
			
			
			return list;
		}
		// 대타찾기 카테고리 검색 null 없을시
		@Override
		public List<JobBoardDto> searchalbaboard(int loc, int category) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			
			
			try {
				pstm = con.prepareStatement(searchalbaboard);
				pstm.setInt(1, loc);
				pstm.setInt(2, category);
		
				rs =pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
				System.out.println("아");
			}

		
			return list;
		}
		//대타 찾기 카테고리검색 category null 일시 
		@Override
		public List<JobBoardDto> searchlocalbaboard(int loc) {
			Connection con = getConnection();
			PreparedStatement pstm =null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(searchlocalbaboard);
				pstm.setInt(1, loc);
				
				rs= pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
				
			}
			
			
			
			return list;
		}
		// 대타찾기 카테고리 검색시 loc null 일때
		@Override
		public List<JobBoardDto> searchsectoralbaboard(int category) {
			Connection con = getConnection();
			PreparedStatement pstm =null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(searchsectoralbaboard);
				pstm.setInt(1, category);
				
				rs= pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
			}
			
			
			
			return list;
		}

		//페이지 별로 게시글 가져오기
		@Override
		public List<JobBoardDto> selectPageBoard(int startRow, int endRow) {
			Connection con  = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			try {
				pstm = con.prepareStatement(selectPageBaordSql);
				pstm.setInt(1, startRow);
				pstm.setInt(2, endRow);
				
				rs = pstm.executeQuery();
				
				while ( rs.next() ) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			return list;
		}

		
		//업체찾기 페이지 별로 받아오기
		@Override
		public List<JobBoardDto> selectFindComPageBoard(int startRow, int endRow) {
			Connection con  = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(selectFindComPageBoardSql);
				pstm.setInt(1, startRow);
				pstm.setInt(2, endRow);
				
				rs = pstm.executeQuery();
				
				while ( rs.next() ) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			return list;
		}	
		// 내게시글 보기
		@Override
		public List<JobBoardDto> myboard(String writer) {
			Connection con =getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(myboard);
				pstm.setString(1, writer);
				rs = pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
			}
			
			
			return list;
		}

		//통합검색 업체찾기 게시글 수 
		@Override
		public int getNumberOfFindComBoardByWord(String searchWord) {
			Connection con =getConnection();
			PreparedStatement pstm =null;
			ResultSet rs = null;
			int cnt = 0;
			
			try {
				pstm= con.prepareStatement(getNumberOfFindComBoardByWordSql);
				pstm.setNString(1, searchWord);
				rs = pstm.executeQuery();
				
				while(rs.next()) {
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

		//통합검색 알바 찾기 게시글 수
		@Override
		public int getNumberOfFindAlbaBoardByWord(String searchWord) {
			Connection con =getConnection();
			PreparedStatement pstm =null;
			ResultSet rs = null;
			int cnt = 0;
			
			try {
				pstm= con.prepareStatement(getNumberOfFindAlbaBoardByWordSql);
				pstm.setNString(1, searchWord);
				rs = pstm.executeQuery();
				
				while(rs.next()) {
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

		//통합검색 업체찾기 페이징
		@Override
		public List<JobBoardDto> selectFindComBoardByWord(int startRow, int endRow, String searchWord) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(selectFindComBoardByWordSql);
				pstm.setNString(1, searchWord);
				pstm.setInt(2, startRow);
				pstm.setInt(3, endRow);
				
				rs = pstm.executeQuery();
				while( rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			
			return list;
		}

		//통합검색 알바찾기 페이징
		@Override
		public List<JobBoardDto> selectFindAlbaBoardByWord(int startRow, int endRow, String searchWord) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(selectFindAlbaBoardByWordSql);
				pstm.setNString(1, searchWord);
				pstm.setInt(2, startRow);
				pstm.setInt(3, endRow);
				
				rs = pstm.executeQuery();
				while( rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			
			return list;
		}

		//글삭제
		@Override
		public int deleteBoard(int no) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			try {
				pstm = con.prepareStatement(deleteBoardSql);
				pstm.setInt(1, no);
				res = pstm.executeUpdate();
				
				if ( res > 0 ) {
					commit(con);
				}else {
					rollback(con);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
			}
			
			return res;
		}

		//게시글 수정
		@Override
		public int updateBoard(JobBoardDto dto, String startDate, String endDate, String startTime, String endTime) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			
			try {
				pstm = con.prepareStatement(updateBoardSql);
				pstm.setNString(1, dto.getTitle());
				pstm.setNString(2, dto.getContent());
				pstm.setNString(3, startDate);
				pstm.setNString(4, endDate);
				pstm.setNString(5, startTime);
				pstm.setString(6, endTime);
				pstm.setInt(7, dto.getMoney());
				pstm.setInt(8, dto.getLoc());
				pstm.setInt(9, dto.getCategory());
				pstm.setInt(10, dto.getNo());
				
				res = pstm.executeUpdate();
				
				if(res > 0) {
					commit(con);
				}else {
					rollback(con);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
			}
			return res;
		}	
		//게시글작성
		@Override
		public int insertCompanyBoard(JobBoardDto dto, String start_date, String end_date, String start_time,
				String end_time, int m_role) {

			Connection con = getConnection();
			PreparedStatement pstm = null;
			int res = 0;
			//1보다 크면 업체 
			String sql = (m_role>1)?insertCompanyBoardSql:insertAlbaBoardSql;
			System.out.println(dto.toString());
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, dto.getWriter());
				pstm.setString(2, dto.getTitle());
				pstm.setString(3, dto.getContent());
				pstm.setString(4, start_date);
				pstm.setString(5, end_date);
				pstm.setString(6, start_time);
				pstm.setString(7, end_time);
				pstm.setInt(8, dto.getMoney());
				pstm.setInt(9, dto.getLoc());
				pstm.setInt(10, dto.getCategory());

				System.out.println("03.query 준비: " + sql);
				res = pstm.executeUpdate();
				System.out.println("04.query 실행 및 리턴");
				
				if ( res > 0 ) {
					commit(con);
				}else {
					rollback(con);
				}
			} catch (SQLException e) {
				System.out.println("3/4단계 에러");
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
				System.out.println("05.db 종료\n");
			}
			return res;	
		}

		//대타 찾기 게시판 페이징 가져오기
		@Override
		public List<JobBoardDto> selectFindAlbaPageBoard(int startRow, int endRow) {
			Connection con  = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(selectFindAlbaPageBoardSql);
				pstm.setInt(1, startRow);
				pstm.setInt(2, endRow);
				
				rs = pstm.executeQuery();
				
				while ( rs.next() ) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			
			return list;
		}

		@Override
		public List<JobBoardDto> termalbaboard() {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
				try {
					pstm = con.prepareStatement(termalbaboard);
					rs = pstm.executeQuery();
					while(rs.next()) {
						JobBoardDto dto = new JobBoardDto(rs.getInt(2),rs.getInt(1));
						list.add(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(pstm);
					close(rs);
					close(con);
				}
				
			return list;
			
		}

		@Override
		public JobBoardDto boardno(int no) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			JobBoardDto dto = null;
			
			try {
				pstm = con.prepareStatement(boardno);
				pstm.setInt(1, no);
				rs =pstm.executeQuery();
				while(rs.next()) {
					dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
			}
			
			return dto;
		
		}

		@Override
		public List<JobBoardDto> termstoreboard() {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
				try {
					pstm = con.prepareStatement(termstoreboard);
					rs = pstm.executeQuery();
					while(rs.next()) {
						JobBoardDto dto = new JobBoardDto(rs.getInt(2),rs.getInt(1));
						list.add(dto);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					close(pstm);
					close(rs);
					close(con);
				}
				
			return list;
			
		}

		@Override
		public List<JobBoardDto> alllocboard(int loc) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(alllocboard);
				pstm.setInt(1, loc);
				rs = pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
			}
			
			
			return list;
		}

		@Override
		public List<JobBoardDto> alllocboard2(int loc) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = new ArrayList<JobBoardDto>();
			
			try {
				pstm = con.prepareStatement(alllocboard2);
				pstm.setInt(1, loc);
				rs = pstm.executeQuery();
				while(rs.next()) {
					JobBoardDto dto = new JobBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getTimestamp(6),
							rs.getTimestamp(7), rs.getTimestamp(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getTimestamp(12), rs.getInt(13), rs.getInt(14));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close(pstm);
				close(rs);
				close(con);
			}
			
			
			return list;
		}

		

		@Override
		public int getNumberOfSearchComBoardByCategory(int categoryno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int cnt = 0;
			
			try {
				pstm = con.prepareStatement(getNumberOfSearchComBoardByCategorySql);
				pstm.setInt(1, categoryno);
				
				rs = pstm.executeQuery();
				
				if ( rs.next() ) {
					cnt = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					close(rs);
				}
				close(pstm);
				close(con);
			}
			return cnt;
		}

		@Override
		public int getNumberOfSearchAlbaBoardByCategory(int categoryno) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int cnt = 0;
			
			try {
				pstm = con.prepareStatement(getNumberOfSearchAlbaBoardByCategorySql);
				pstm.setInt(1, categoryno);
				
				rs = pstm.executeQuery();
				
				if ( rs.next() ) {
					cnt = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(rs != null) {
					close(rs);
				}
				close(pstm);
				close(con);
			}
			return cnt;
		}

		@Override
		public List<JobBoardDto> searchComBoardByCategory(int categoryno, int startRow, int endRow) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = null;
			
			try {
				pstm = con.prepareStatement(searchComBoardByCategorySql);
				pstm.setInt(1, categoryno);
				pstm.setInt(2, startRow);
				pstm.setInt(3, endRow);
				
				rs = pstm.executeQuery();
				
				if( rs.next() == true) {
					list = new ArrayList<JobBoardDto>();
					JobBoardDto dto2 = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					list.add(dto2);
					while(rs.next()) {
						JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
								rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
						
						list.add(dto);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if( rs != null) {
					close(rs);
					
				}
				close(pstm);
				close(con);
			}
			return list;
		}

		@Override
		public List<JobBoardDto> searchAlbaBoardByCategory(int categoryno, int startRow, int endRow) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<JobBoardDto> list = null;
			
			try {
				pstm =con.prepareStatement(searchAlbaBoardByCategorySql);
				pstm.setInt(1, categoryno);
				pstm.setInt(2, startRow);
				pstm.setInt(3, endRow);
				
				rs = pstm.executeQuery();
				
				if( rs.next() == true) {
					list = new ArrayList<JobBoardDto>();
					JobBoardDto dto2 = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
							rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
					list.add(dto2);
					while(rs.next()) {
						JobBoardDto dto = new JobBoardDto(rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6), rs.getTimestamp(7),
								rs.getTimestamp(8), rs.getTimestamp(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getTimestamp(13), rs.getInt(14), rs.getInt(15));
						
						list.add(dto);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if( rs != null) {
					close(rs);
					
				}
				close(pstm);
				close(con);
			}
			return list;
		}

		@Override
		public HashMap<String, Object> selectAbility(String id) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			HashMap<String, Object> res = new HashMap<String, Object>();
			
			try {
				pstm = con.prepareStatement(ability);
				pstm.setString(1, id);
				
				rs = pstm.executeQuery();
				
				while(rs.next()){
					ParttimerAbilityDto tmp = new ParttimerAbilityDto();
					tmp.setM_no(rs.getInt(1));
					tmp.setM_id(rs.getString(2));
					tmp.setC_no(rs.getInt(3));
					tmp.setA_cnt(rs.getInt(4));
					
					res.put(""+rs.getInt(3), tmp);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
				close(con);
			}
			return res;
		}

		


}