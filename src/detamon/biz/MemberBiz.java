package detamon.biz;

import java.util.List;

import detamon.dto.MemberDto;
import detamon.dto.ParttimerAbilityDto;

public interface MemberBiz {
	
	
	public MemberDto login(String id, String pw); //로그인
	public boolean idChk(String id); // 아이디 중복체크
	public int signup(MemberDto dto); // 회원가입
	public String findId(String name, String rno); //아이디 찾기
	public String findEmailById(String id); // 비밀번호 찾기(이메일)
	public String findPw(String id); // 비밀번호 찾기
	public List<MemberDto> score(); // 신뢰도 가져오기
	public MemberDto selectOneById(String id); //아이디로 멤버검색
	public List<String> selectAlbaGender(int no); //지원자 성별리스트 반환
	public List<ParttimerAbilityDto> selectAbilityById(String writer); //아이디로 알바능숙도 반환
	public String selectNameById(String id); //아이디로 이름 검색
	public int changepw(MemberDto dto); // 비밀번호 변경
	public int deletemember(MemberDto dto); // 회원 탈퇴
	public int modifyMember(MemberDto dto);
	public List<MemberDto> selectPageScore(List<String> writerList); //신뢰도 가져오기
	public List<ParttimerAbilityDto> profile(String writer); // //마이페이지폼 신뢰도 가져오기
}
