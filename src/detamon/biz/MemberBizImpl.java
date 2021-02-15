package detamon.biz;

import java.util.List;

import detamon.dao.ContractDao;
import detamon.dao.ContractDaoImpl;
import detamon.dao.MemberDao;
import detamon.dao.MemberDaoImpl;
import detamon.dto.MemberDto;
import detamon.dto.ParttimerAbilityDto;

public class MemberBizImpl implements MemberBiz{
	MemberDao memberDao = new MemberDaoImpl();
	ContractDao contractDao = new ContractDaoImpl(); 
	
	//로그인
	@Override
	public MemberDto login(String id, String pw) {
		return memberDao.login(id,pw);
	}

	//아이디 중복체크
	@Override
	public boolean idChk(String id) {
		return memberDao.idChk(id);
	}

	//회원가입
	@Override
	public int signup(MemberDto dto) {
		return memberDao.signup(dto);
	}

	//아이디 찾기
	@Override
	public String findId(String name, String rno) {
		return memberDao.findId(name, rno);
	}

	//비밀번호 찾기(이메일)
	@Override
	public String findEmailById(String id) {
		return memberDao.findEmailById(id);
	}

	//비밀번호 찾기
	@Override
	public String findPw(String id) {
		return memberDao.findPw(id);
	}
	
	
	

	//아이디로 멤버검색
	@Override
	public MemberDto selectOneById(String id) {
		return memberDao.selectOneById(id);
	}

	//지원자 성별 리스트 반환
	@Override
	public List<String> selectAlbaGender(int no) {
		
		List<String> albaIdList = contractDao.selectAlbaListByNo(no);
		
		
		if(albaIdList == null) {
			return null;
		}else {
			
			return memberDao.selectAlbaGender(albaIdList);
		}
	}
	//아이디로 알바 능숙도 반환
	@Override
	public List<ParttimerAbilityDto> selectAbilityById(String writer) {
		return memberDao.selectAbilityById(writer);
	}

	//아이디로 이름 검색
	@Override
	public String selectNameById(String id) {
		return memberDao.selectNameById(id);
	}

	@Override
	public int changepw(MemberDto dto) {
		return memberDao.changepw( dto);
	}

	@Override
	public int deletemember(MemberDto dto) {
		return memberDao.deletemeber(dto);
	}

	@Override
	public int modifyMember(MemberDto dto) {
		return memberDao.modifyMember(dto);
	}

	//신뢰도 가져오기
	@Override
	public List<MemberDto> selectPageScore(List<String> writerList) {
		// TODO Auto-generated method stub
		return memberDao.selectPageScore(writerList);
	}	
	//마이페이지 신뢰도 가져오기
	@Override
	public List<ParttimerAbilityDto> profile(String writer) {
		
		return memberDao.profile(writer);
	}

	@Override
	public List<MemberDto> score() {
		
		return memberDao.score();
	}
	
	
}
