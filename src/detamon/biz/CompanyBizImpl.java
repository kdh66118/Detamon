package detamon.biz;

import detamon.dao.CompanyDao;
import detamon.dao.CompanyDaoImpl;
import detamon.dto.CompanyDto;

public class CompanyBizImpl implements CompanyBiz {
	CompanyDao dao = new CompanyDaoImpl();
	
	
	//아이디로 회사 주소 검색
	@Override
	public String selectAddrById(String m_id) {
		return dao.selectAddrById(m_id);
	}

	//아이디로 회사 이름 검색
	@Override
	public String selectNameById(String m_id) {
		return dao.selectNameById(m_id);
	}
	//작성자로 회사 검색
		@Override
		public CompanyDto selectOneById(String writer) {
			return dao.selectOneById(writer);
		}
	//업체 정보 수정
		@Override
		public int modifycompany(CompanyDto dto) {
			return dao.modifycompany(dto);
		}

		@Override
		public CompanyDto selectid(String m_id) {
			return dao.selectid(m_id);
		}

		@Override
		public int insertcom(CompanyDto dto) {
			return dao.insertcom(dto);
		}

	
}
