package detamon.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import detamon.dao.JobBoardDao;
import detamon.dao.JobBoardDaoImpl;
import detamon.dto.CategoryDto;
import detamon.dto.JobBoardDto;
import detamon.dto.LocationDto;

public class JobBoardBizImpl implements JobBoardBiz {
	JobBoardDao jobBoardDao = new JobBoardDaoImpl();

	//모든 게시글 검색
	@Override
	public List<JobBoardDto> selectAllBoard() {
		return jobBoardDao.selectAllBoard();
	}
	
	//업체 찾기 검색
	public List<JobBoardDto> selectStoreBoard(){
		return jobBoardDao.selectStoreBoard();
	}
	
	//대타 찾기 검색
	public List<JobBoardDto> selectalbaBoard(){
		return jobBoardDao.selectalbaBoard();
	}
	
	//모든 loc 반환
		@Override
		public List<LocationDto> selectAllLoc() {
			return jobBoardDao.selectAllLoc();
		}
		
	//모든 카테고리 반환
	@Override
	public List<CategoryDto> selectAllCategory() {
		return jobBoardDao.selectAllCategory();
	}
	
	//조회수 top5게시물 
	@Override
	public List<JobBoardDto> selectCntList(int type_no) {
		
		
		return jobBoardDao.selectCntList(type_no);
	}
	//글 번호로 작성자 받아오기
		@Override
		public String selectWriterByNo(int no) {
			return jobBoardDao.selectWriterByNo(no);
		}

		//글 번호로 게시글 가져오기
		@Override
		public JobBoardDto selectOneByNo(int no) {
			return jobBoardDao.selectOneByNo(no);
		}

		//조회수 증가
		@Override
		public int plusBoardCnt(int no) {
			return jobBoardDao.plusBoardCnt(no);
		}
		@Override
		public List<JobBoardDto> selectallcategory(int loc , int category) {
			
			return jobBoardDao.selectallcategory( loc, category);
		}
		@Override
		public List<JobBoardDto> selectcategory(int loc, int category) {
			
			return jobBoardDao.selectcategory(loc,category);
		}
		@Override
		public List<JobBoardDto> selectloccategory(int loc) {
			
			return jobBoardDao.selectloccategory(loc);
		}
		@Override
		public List<JobBoardDto> selectsectorcategory(int category) {
			
			return jobBoardDao.selectsectorcategory(category);
		}
		@Override
		public List<JobBoardDto> searchalbaboard(int loc, int category) {
			return jobBoardDao.searchalbaboard(loc, category);
		}
		@Override
		public List<JobBoardDto> searchlocalbaboard(int loc) {
			
			return jobBoardDao.searchlocalbaboard(loc);
		}
		@Override
		public List<JobBoardDto> searchsectoralbaboard(int category) {
			
			return jobBoardDao.searchsectoralbaboard(category);
		}

		//게시글 페이지 별로 받아오기
		@Override
		public List<JobBoardDto> selectPageBoard(int startRow, int endRow) {
			return jobBoardDao.selectPageBoard(startRow, endRow);
		}

		//업체찾기 페이지 별로 받아오기
		@Override
		public List<JobBoardDto> selectFindComPageBoard(int startRow, int endRow) {
			return jobBoardDao.selectFindComPageBoard(startRow, endRow);
		}

		//업체찾기 게시글 수
		@Override
		public int getNumberOfFindComBoard() {
			List<JobBoardDto> list = selectStoreBoard();
			int boardCnt = list.size();
			return boardCnt;
		}

		@Override
		public List<JobBoardDto> myboard(String writer) {
			return jobBoardDao.myboard(writer);
		}

		//통합검색 회사 총 게시글 수
		@Override
		public int getNumberOfFindComBoardByWord(String searchWord) {
			searchWord = "%"+searchWord + "%";
			return jobBoardDao.getNumberOfFindComBoardByWord(searchWord);
		}

		//통합검색 알바 총 게시글 수
		@Override
		public int getNumberOfFindAlbaBoardByWord(String searchWord) {
			searchWord = "%"+searchWord + "%";
			return jobBoardDao.getNumberOfFindAlbaBoardByWord(searchWord);
		}

		//통함검색 회사찾기 페이징 
		@Override
		public List<JobBoardDto> selectFindComBoardByWord(int startRow, int endRow, String searchWord) {
			searchWord = "%"+searchWord + "%";
			return jobBoardDao.selectFindComBoardByWord(startRow, endRow, searchWord);
		}

		
		//통합검색 알바 찾기 페이징
		@Override
		public List<JobBoardDto> selectFindAlbaBoardByWord(int startRow, int endRow, String searchWord) {
			searchWord = "%"+searchWord + "%";
			return jobBoardDao.selectFindAlbaBoardByWord(startRow, endRow, searchWord);
		}

		//글 삭제
		@Override
		public int deleteBoard(int no) {
			return jobBoardDao.deleteBoard(no);
		}

		//글 수정
		@Override
		public int updateBoard(JobBoardDto dto, String startDate, String endDate, String startTime, String endTime) {
			//startDate = "TO_DATE('" + startDate + "','YYYY-MM-DD')";
			//endDate = "TO_DATE('" + endDate + "','YYYY-MM-DD')";
			startTime = "00010101"+startTime.substring(0,2) + startTime.substring(3, 5);
			endTime = "00010101"+endTime.substring(0,2) + endTime.substring(3, 5);
			//startTime = "TO_DATE('" + startTime + "','YYYYMMDDHH24MI')";
			//endTime = "TO_DATE('" + endTime + "','YYYYMMDDHH24MI')";
			
			System.out.println("시작:"+startDate);
			System.out.println("끗:"+endDate);
			System.out.println("시작:"+startTime);
			System.out.println("끗:"+endTime);
			System.out.println(endTime);
			return jobBoardDao.updateBoard(dto,startDate,endDate,startTime,endTime);
		}
		
		@Override
		public int insertCompanyBoard(JobBoardDto dto, String start_date, String end_date, String start_time,String end_time, int m_role) {
			
			start_time = "00010101"+start_time.substring(0,2) + start_time.substring(3, 5);
			end_time = "00010101"+end_time.substring(0,2) + end_time.substring(3, 5);
			//startTime = "TO_DATE('" + startTime + "','YYYYMMDDHH24MI')";
			//endTime = "TO_DATE('" + endTime + "','YYYYMMDDHH24MI')";
			
			System.out.println("시작:"+start_date);
			System.out.println("끗:"+end_date);
			System.out.println("시작:"+start_time);
			System.out.println("끗:"+end_time);
			return jobBoardDao.insertCompanyBoard(dto, start_date, end_date, start_time, end_time, m_role);
		}

		//대타찾기 게시판 게시글 수
		@Override
		public int getNumberOfFindAlbaBoard() {
			List<JobBoardDto> list = selectalbaBoard();
			int boardCnt = list.size();
			return boardCnt;
		}

		//대타찾기 페이지 별로 받아오기
		@Override
		public List<JobBoardDto> selectFindAlbaPageBoard(int startRow, int endRow) {
			return jobBoardDao.selectFindAlbaPageBoard(startRow, endRow);
		}
		@Override
		public List<JobBoardDto> termalbaboard() {
			
			return jobBoardDao.termalbaboard();
		}

		@Override
		public JobBoardDto boardno(int no) {
			
			return jobBoardDao.boardno(no);
		}

		@Override
		public List<JobBoardDto> termstoreboard() {
			
			return jobBoardDao.termstoreboard();
		}

		@Override
		public List<JobBoardDto> alllocboard(int loc) {
			
			return jobBoardDao.alllocboard(loc);
		}

		@Override
		public List<JobBoardDto> alllocboard2(int loc) {
			return jobBoardDao.alllocboard2(loc);
		}

		

		@Override
		public int getNumberOfSearchComBoardByCategory(int categoryno) {
			return jobBoardDao.getNumberOfSearchComBoardByCategory(categoryno);
		}

		@Override
		public int getNumberOfSearchAlbaBoardByCategory(int categoryno) {
			return jobBoardDao.getNumberOfSearchAlbaBoardByCategory(categoryno);
		}

		@Override
		public List<JobBoardDto> searchComBoardByCategory(int categoryno, int startRow, int endRow) {
			return jobBoardDao.searchComBoardByCategory(categoryno, startRow, endRow);
		}

		@Override
		public List<JobBoardDto> searchAlbaBoardByCategory(int categoryno, int startRow, int endRow) {
			return jobBoardDao.searchAlbaBoardByCategory(categoryno, startRow, endRow);
		}

		@Override
		public HashMap<String, Object> selectAbility(String id) {
			return jobBoardDao.selectAbility(id);
		}

		
	}
