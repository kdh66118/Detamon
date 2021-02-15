package detamon.biz;

import java.util.HashMap;
import java.util.List;

import detamon.dto.CategoryDto;
import detamon.dto.JobBoardDto;
import detamon.dto.LocationDto;

public interface JobBoardBiz {
		
	
	public List<JobBoardDto> selectAllBoard(); //모든 게시글 검색
	public List<JobBoardDto> selectStoreBoard(); //업체찾기 게시글 검색
	public List<JobBoardDto> selectalbaBoard(); // 알바 찾기 게시글 검색
	public List<LocationDto> selectAllLoc(); // 모든 로케이션 반환
	public List<CategoryDto> selectAllCategory(); // 모든 카테고리 반환
	public List<JobBoardDto> selectCntList(int type_no); // 조회수 top5 게시물 가져오기
	public String selectWriterByNo(int no);	//글번호로 작성자 받아오기
	public JobBoardDto selectOneByNo(int no); //글번호로 게시물 받아오기
	public int plusBoardCnt(int no) ;//조회수 증가
	public List<JobBoardDto> selectallcategory(int loc,int category); // 업체찾기 카테고리 검색
	public List<JobBoardDto> selectcategory(int loc,int category);
	public List<JobBoardDto> selectloccategory(int loc);
	public List<JobBoardDto> selectsectorcategory(int category);
	public List<JobBoardDto> searchalbaboard(int loc,int category);// 대타찾기 카테고리 검색 
	public List<JobBoardDto> searchlocalbaboard(int loc);  //대타찾기 loc 널값일때
	public List<JobBoardDto> searchsectoralbaboard(int category);// 대타찾기 category 널값일때
	public List<JobBoardDto> selectPageBoard(int startRow, int endRow); //게시글 페이지 별로 받아오기
	public List<JobBoardDto> selectFindComPageBoard(int startRow, int endRow); //업체찾기 페이지 별로 받아오기
	public int getNumberOfFindComBoard(); //업체찾기 게시판 게시물수
	public List<JobBoardDto> myboard(String writer); // 내게시글 보기 
	public int getNumberOfFindComBoardByWord(String searchWord); //통합검색 업체찾기 게시글 수
	public int getNumberOfFindAlbaBoardByWord(String searchWord); //통함검색 알바찾기 게시글 수 
	public List<JobBoardDto> selectFindComBoardByWord(int startRow, int endRow, String searchWord); //통합검색 업체찾기 게시글 페이지별로
	public List<JobBoardDto> selectFindAlbaBoardByWord(int startRow, int endRow, String searchWord); //통합검색 알바찾기 게시글 페이지 별로
	public int deleteBoard(int no); //글삭제
	public int updateBoard(JobBoardDto dto, String startDate, String endDate, String startTime, String endTime); //글 수정
	public int insertCompanyBoard(JobBoardDto dto, String start_date, String end_date, String start_time, String end_time, int m_role); //업체 게시글 작성
	public int getNumberOfFindAlbaBoard(); //대타찾기 게시판 게시물수
	public List<JobBoardDto> selectFindAlbaPageBoard(int startRow, int endRow); //업체찾기 페이지 별로 받아오기
	public List<JobBoardDto> termalbaboard(); // 대타찾기 기간 구하기
	public JobBoardDto boardno(int no); // 해당 게시글 번호 가져오기
	public List<JobBoardDto> termstoreboard(); // 업체찾기 기간 구하기
	public List<JobBoardDto> alllocboard(int loc); // 알바찾기 기간게시글 리스트
	public List<JobBoardDto> alllocboard2(int loc); // 업체찾기 기간게시글 리스트
	public int getNumberOfSearchComBoardByCategory(int categoryno); //업종별 구인/구직(홈) 총 게시글 수
	public int getNumberOfSearchAlbaBoardByCategory(int categoryno); //업종별 구인/구직(홈) 총 게시글 수
	public List<JobBoardDto> searchComBoardByCategory(int categoryno, int startRow, int endRow); //업종별 구인/구직(홈) 게시글 페이징으로가져오기
	public List<JobBoardDto> searchAlbaBoardByCategory(int categoryno, int startRow, int endRow); //업종별 구인/구직(홈) 게시글 페이징으로가져오기

	//알바 어빌리티 
	public HashMap<String, Object> selectAbility(String id);


	
}
