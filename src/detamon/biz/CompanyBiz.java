package detamon.biz;

import detamon.dto.CompanyDto;

public interface CompanyBiz {
	
	public String selectAddrById(String m_id); //아이디로 회사 주소 검색
	public String selectNameById(String m_id); //아이디로 회사 이름 검색
	public CompanyDto selectOneById(String writer); //작성자로 회사 검색
	public int modifycompany(CompanyDto dto); // 업체 정보 수정
	public CompanyDto selectid(String m_id); // 아이디로 업체 정보 가져오기
	public int insertcom(CompanyDto dto);  // 업체 정보 등록
}
