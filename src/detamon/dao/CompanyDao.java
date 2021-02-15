package detamon.dao;

import detamon.dto.CompanyDto;

public interface CompanyDao {

	String selectAddrById = "SELECT COM_ADDR FROM COMPANY WHERE M_ID = ? ";
	String selectNameById = " SELECT COM_NAME FROM COMPANY WHERE M_ID = ?";
	String selectOneById = " SELECT * FROM COMPANY WHERE M_ID = ? ";
	String modifycompany = "UPDATE COMPANY SET COM_NAME = ? , COM_PHONE = ?, COM_ADDR = ? ,COM_CATEGORY=?,COM_LOCATION=? WHERE M_ID=? ";
	String selectid = "SELECT*FROM COMPANY WHERE M_ID=?";
	String insertcom = "INSERT INTO COMPANY VALUES(?,?,?,?,?,?,?)";
	
	public String selectAddrById(String m_id); //아이디로 회사 주소 검색
	public String selectNameById(String m_id); //아이디로 회사 이름 검색
	public CompanyDto selectOneById(String writer); //작성자로 회사검색
	public int modifycompany(CompanyDto dto); // 업체 정보 수정 
	public CompanyDto selectid(String m_id);
	public int  insertcom (CompanyDto dto);
}
