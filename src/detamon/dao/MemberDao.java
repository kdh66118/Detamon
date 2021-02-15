package detamon.dao;

import java.util.List;

import detamon.dto.MemberDto;
import detamon.dto.ParttimerAbilityDto;

public interface MemberDao {
	String loginSql = " SELECT * FROM MEMBER WHERE M_ID = ? AND M_PW = ? AND M_ENABLED='Y' ";
	String idChkSql = " SELECT * FROM MEMBER WHERE M_ID = ? ";
	String signupSql = " INSERT INTO MEMBER VALUES( MEMBERSEQ.NEXTVAL, ? ,?, ?, ?, ?, ?, ?, ? , ?, 0 , 'Y' )";
	String selectIdByNameRno = " SELECT M_ID FROM MEMBER WHERE M_NAME = ? AND M_RNO=? ";
	String selectEmailById = " SELECT M_EMAIL FROM MEMBER WHERE M_ID = ? ";
	String selectPwById= " SELECT M_PW FROM MEMBER WHERE M_ID = ?";
	
	String selectOneById = "SELECT * FROM MEMBER WHERE M_ID = ? ";
	String selectAlbaGenderSql = " SELECT M_GENDER FROM MEMBER WHERE M_ID = ?";
	String selectAbilityByIdSql = " SELECT * FROM PARTTIMER_ABILITY WHERE M_ID = ? ORDER BY C_NO ";
	String selectNameByIdSql = " SELECT M_NAME FROM MEMBER WHERE M_ID = ? "; //아이디로 멤버이름 검색	
	String changepw = "UPDATE MEMBER SET M_PW=? WHERE M_PW=?"; //비밀번호 변경
	String deletemember = "UPDATE MEMBER SET M_ENABLED='N' WHERE M_ID=?";  // 회원탈퇴
	String modifymember = "UPDATE MEMBER SET M_PHONE = ? , M_EMAIL=?,M_ADDR=?,M_NAME=? WHERE M_ID=?";
	String selectPageScore = " SELECT M_SCORE FROM MEMBER WHERE M_ID = ?  "; //신뢰도 점수 가져오기
	String profile = "SELECT * FROM PARTTIMER_ABILITY WHERE M_ID=?"; // 마이페이지 프로필확인 숙련도가져오기
	String score = "SELECT M.M_SCORE FROM MEMBER M INNER JOIN JOBBOARD J ON M.M_ID = J.WRITER"; // 신뢰도 점수 가져오기
	
	public MemberDto login(String id, String pw); //로그인
	public boolean idChk(String id); //아이디 중복체크
	public int signup(MemberDto dto); //회원가입
	public List<MemberDto> score(); // 신뢰점수 가져오기
	public String findId(String name, String rno); //아이디 찾기
	public String findEmailById(String id); //비밀번호 찾기(이메일)
	public String findPw(String id); //비밀번호 찾기
	public MemberDto selectOneById(String id); //아이디로 멤버 가져오기
	public List<String> selectAlbaGender(List<String> albaIdList); //지원자 성별리스트
	public List<ParttimerAbilityDto> selectAbilityById(String writer); //아이디로 알바 능숙도 반환
	public String selectNameById(String id); //아이디로 멤버이름 검색
	public int changepw(MemberDto dto); // 비밀번호 변경 
	public int deletemeber(MemberDto dto);  // 회원탈퇴
	public int modifyMember(MemberDto dto);
	public List<MemberDto> selectPageScore(List<String> writerList); //신뢰도 가져오기
	public List<ParttimerAbilityDto> profile(String writer); //마이페이지 프로필확인숙련도 가져오기 
}
