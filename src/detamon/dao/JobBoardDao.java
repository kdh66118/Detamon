package detamon.dao;

import java.util.HashMap;
import java.util.List;

import detamon.dto.CategoryDto;
import detamon.dto.JobBoardDto;
import detamon.dto.LocationDto;

public interface JobBoardDao {
	String selectAllBoardSql = "SELECT * FROM JOBBOARD ORDER BY NO DESC";
	String selectstoreBoard = " SELECT *  FROM JOBBOARD WHERE TYPE_NO=2 ORDER BY NO DESC"; //업체찾기 게시글 검색
	String selectalbaBoard = " SELECT *  FROM JOBBOARD WHERE TYPE_NO=1"; //알바찾기 게시글 검색
	String selectcategory = "SELECT*FROM JOBBOARD WHERE LOC=? AND CATEGORY=?"; //카테고리검색
	String selectAllLocSql = " SELECT * FROM LOCATION "; //모든 loc반환
	String selectAllCategorySql = " SELECT * FROM CATEGORY "; //모든 카테고리 반환
	String selectCntListSql = " SELECT ROWNUM, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE , START_TIME, END_TIME, "+
							" MONEY, LOC, CATEGORY, REGDATE, CNT, TYPE_NO "+
							" FROM ( SELECT * FROM JOBBOARD WHERE TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS') < TO_CHAR(START_DATE,'YYYYMMDDHH24MISS') AND TYPE_NO = ? "+
							" ORDER BY CNT DESC ) WHERE ROWNUM <= 5 ";
	String selectWriterByNoSql = " SELECT WRITER FROM JOBBOARD WHERE NO = ? ";
	String selectOneByNo = " SELECT * FROM JOBBOARD WHERE NO = ? ";
	String plusBoardCntSql =" UPDATE JOBBOARD SET CNT = CNT+1 WHERE NO = ?";		
	
	String selectallcategory = "SELECT*FROM JOBBOARD WHERE LOC=? AND CATEGORY=? AND TYPE_NO=2"; // 업체 카테고리검색
	String selectloccategory = "SELECT*FROM JOBBOARD WHERE LOC=? AND TYPE_NO=2"; //업체 category null 값 검색
	String selectsectorcategory = "SELECT*FROM JOBBOARD WHERE CATEGORY=? AND TYPE_NO =2"; //업체 loc null 값 검색 
	String searchalbaboard = "SELECT*FROM JOBBOARD WHERE LOC=? AND CATEGORY=? AND TYPE_NO=1"; // 알바 카테고리 검색
	String searchlocalbaboard = "SELECT*FROM JOBBOARD WHERE LOC=? AND TYPE_NO=1"; //알바 catrgory null 값 검색
	String searchsectoralbaboard = "SELECT*FROM JOBBOARD WHERE CATEGORY=? AND TYPE_NO =1"; // 알바 loc null 값 검색
	String selectPageBaordSql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
							" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
							" FROM JOBBOARD ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //페이지 별로 게시글가져오기
	String selectFindComPageBoardSql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM (SELECT * FROM JOBBOARD ORDER BY NO DESC) WHERE TYPE_NO=2 ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //업체찾기 게시판 페이지 별로 게시글가져오기
	
	String myboard = "SELECT*FROM JOBBOARD WHERE WRITER=?";//마이페이지 내게시글 보기 
	String getNumberOfFindComBoardByWordSql = "SELECT COUNT(*) FROM JOBBOARD WHERE TITLE LIKE ? AND TYPE_NO = 2"; //통합검색 업체찾기 게시글수
	String getNumberOfFindAlbaBoardByWordSql = "SELECT COUNT(*) FROM JOBBOARD WHERE TITLE LIKE ? AND TYPE_NO = 1";//통합검색 알바찾기 게시글수
	String selectFindComBoardByWordSql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM (SELECT * FROM JOBBOARD ORDER BY NO DESC) WHERE TITLE LIKE ? AND TYPE_NO = 2  ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //통합검색 업체찾기 게시글 페이징
	String selectFindAlbaBoardByWordSql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM (SELECT * FROM JOBBOARD ORDER BY NO DESC) WHERE TITLE LIKE ? AND TYPE_NO = 1 ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //통합검색 알바 찾기 게시글 페이징
	String deleteBoardSql = "DELETE FROM JOBBOARD WHERE NO = ?"; //글 삭제
	String updateBoardSql = " UPDATE JOBBOARD SET TITLE = ? , CONTENT = ? , START_DATE = TO_DATE(? , 'YYYY-MM-DD' ) , END_DATE = TO_DATE(? , 'YYYY-MM-DD' ) , START_TIME =TO_DATE(? , 'YYYYMMDDHH24MI' ) , END_TIME = TO_DATE(? , 'YYYYMMDDHH24MI' ) , MONEY = ? , LOC = ? , CATEGORY = ? WHERE NO = ?" ; //글 수정
	
	String insertCompanyBoardSql = " INSERT INTO JOBBOARD VALUES(JOBBOARDSEQ.NEXTVAL, ?, ?, ?, TO_DATE(? , 'YYYY-MM-DD' ), TO_DATE(? , 'YYYY-MM-DD' ), TO_DATE(? , 'YYYYMMDDHH24MI' ), TO_DATE(? , 'YYYYMMDDHH24MI' ), ?, ?, ?, SYSDATE, 0, 2) " ;   //업체 게시글 작성   
	String insertAlbaBoardSql = " INSERT INTO JOBBOARD VALUES(JOBBOARDSEQ.NEXTVAL, ?, ?, ?, TO_DATE(? , 'YYYY-MM-DD' ), TO_DATE(? , 'YYYY-MM-DD' ), TO_DATE(? , 'YYYYMMDDHH24MI' ), TO_DATE(? , 'YYYYMMDDHH24MI' ), ?, ?, ?, SYSDATE, 0, 1) ";   //알바 게시글 작성
	String selectFindAlbaPageBoardSql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM (SELECT * FROM JOBBOARD ORDER BY NO DESC) WHERE TYPE_NO=1 ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //대타찾기 게시판 페이지 별로 게시글가져오기
	String termalbaboard = "SELECT ROUND((START_DATE-SYSDATE)),NO FROM JOBBOARD WHERE TYPE_NO=1"; // 대타찾기 기간 
	
	String boardno = "SELECT*FROM JOBBOARD WHERE NO=?"; //기간 카테고리 검색시 
	
	String termstoreboard = "SELECT ROUND((START_DATE-SYSDATE)),NO FROM JOBBOARD WHERE TYPE_NO=2"; // 업체찾기 기간 
	String alllocboard = "SELECT * FROM JOBBOARD WHERE LOC = ? AND TYPE_NO=1"; //알바생 
	String alllocboard2 = "SELECT * FROM JOBBOARD WHERE LOC = ? AND TYPE_NO=2"; //업체
	String getNumberOfSearchComBoardByCategorySql = " SELECT COUNT(*) FROM JOBBOARD WHERE CATEGORY = ? AND TYPE_NO = 2"; //업종별 구인/구직 (홈) 전체 게시글 수
	String getNumberOfSearchAlbaBoardByCategorySql = " SELECT COUNT(*) FROM JOBBOARD WHERE CATEGORY = ? AND TYPE_NO = 1"; //업종별 구인/구직 (홈) 전체 게시글 수
	String searchComBoardByCategorySql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM (SELECT * FROM JOBBOARD ORDER BY NO DESC) WHERE CATEGORY = ? AND TYPE_NO=2 ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //업종별 구인/구직(홈) 페이지 별로 게시글가져오기
	String searchAlbaBoardByCategorySql = " SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM ( SELECT ROWNUM N, NO, WRITER, TITLE, CONTENT, START_DATE, END_DATE, START_TIME, END_TIME, MONEY, LOC, CATEGORY, REGDATE,CNT, TYPE_NO " +
			" FROM (SELECT * FROM JOBBOARD ORDER BY NO DESC) WHERE CATEGORY = ? AND TYPE_NO=1 ORDER BY NO DESC ) WHERE N >= ? AND N <= ? "; //업종별 구인/구직(홈) 페이지 별로 게시글가져오기
	// 알바생 어빌리티 조회 
	String ability = " SELECT * FROM PARTTIMER_ABILITY WHERE M_ID = ? ";
	
	public List<JobBoardDto> selectAllBoard(); //모든 게시글 검색
	public List<JobBoardDto> selectStoreBoard(); // 업체 찾기 게시글 검색
	public List<JobBoardDto> selectalbaBoard(); // 알바 찾기 게시글 검
	public List<JobBoardDto> selectcategory(int loc,int category); // 카테고리검색
	public List<LocationDto> selectAllLoc(); //모든 loc 반환
	public List<CategoryDto> selectAllCategory(); //모든카테고리 반환
	public List<JobBoardDto> selectCntList(int type_no); //조회수 top5 게시물
	public String selectWriterByNo(int no); //글번호로 작성자 가져오기
	public JobBoardDto selectOneByNo(int no); //글번호로 게시글 가져오기
	public int plusBoardCnt(int no); //조회수 증가
	public List<JobBoardDto> selectallcategory(int loc,int category); //  업체 카테고리검색
	public List<JobBoardDto> selectloccategory(int loc); // 지역만 체크시
	public List<JobBoardDto> selectsectorcategory(int category); // 업종만 체크시
	public List<JobBoardDto> searchalbaboard(int loc, int category); // 알바 카테고리검색
	public List<JobBoardDto> searchlocalbaboard(int loc); // 알바 category null 값 검색 
	public List<JobBoardDto> searchsectoralbaboard(int category); //알바 loc null 값 검색
	public List<JobBoardDto> selectPageBoard(int startRow, int endRow); //게시글 페이지별로 가져오기
	public List<JobBoardDto> selectFindComPageBoard(int startRow, int endRow); // 업체찾기 페이지 별로 가져오기
	public List<JobBoardDto> myboard(String  writer); //내 게시글 보기
	public int getNumberOfFindComBoardByWord(String searchWord); //통합검색  업체찾기 총 게시글
	public int getNumberOfFindAlbaBoardByWord(String searchWord); //통합검색 알바찾기 총 게시글
	public List<JobBoardDto> selectFindComBoardByWord(int startRow, int endRow, String searchWord);// 통합검색 업체찾기 페이징
	public List<JobBoardDto> selectFindAlbaBoardByWord(int startRow, int endRow, String searchWord); //통합검색 알바찾기 페이징
	public int deleteBoard(int no); //글 삭제
	public int updateBoard(JobBoardDto dto, String startDate, String endDate, String startTime, String endTime); //글 수정
	public int insertCompanyBoard(JobBoardDto dto, String start_date, String end_date, String start_time, String end_time, int m_role); //업체 게시글 작성
	public List<JobBoardDto> selectFindAlbaPageBoard(int startRow, int endRow); //대타 찾기 페이지 별로 가져오기
	public List<JobBoardDto> termalbaboard(); //대타찾기 기간 검색 
	public JobBoardDto boardno(int no); //게시글 번호
	public List<JobBoardDto> termstoreboard(); //업체찾기 기간 검색
	public List<JobBoardDto> alllocboard(int loc);//알바생
	public List<JobBoardDto> alllocboard2(int loc);//업체
	
	public int getNumberOfSearchComBoardByCategory(int categoryno);
	public int getNumberOfSearchAlbaBoardByCategory(int categoryno);
	public List<JobBoardDto> searchComBoardByCategory(int categoryno, int startRow, int endRow);
	public List<JobBoardDto> searchAlbaBoardByCategory(int categoryno, int startRow, int endRow);


	//알바 어빌리티 
	public HashMap<String, Object> selectAbility(String id);


}
