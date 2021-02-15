package detamon.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import detamon.dto.CompanyDto;

public class CompanyDaoImpl implements CompanyDao {

	
	//아이디로 회사 주소 검색
	@Override
	public String selectAddrById(String m_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String addr = null;
		
		try {
			pstm = con.prepareStatement(selectAddrById);
			pstm.setString(1, m_id);
			rs = pstm.executeQuery();
			
			while ( rs.next()) {
				addr = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return addr;
	}

	
	//아이디로 회사 이름 검색
	@Override
	public String selectNameById(String m_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		try {
			pstm = con.prepareStatement(selectNameById);
			pstm.setNString(1, m_id);
			rs = pstm.executeQuery();
			
			while ( rs.next()) {
				res = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return res;
	}
	//작성자로 회사 검색
	@Override
	public CompanyDto selectOneById(String writer) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CompanyDto dto = null;
		
		try {
			pstm = con.prepareStatement(selectOneById);
			pstm.setNString(1, writer);
			rs=pstm.executeQuery();
			
			while( rs.next() ) {
				dto = new CompanyDto(rs.getNString(1), rs.getNString(2), rs.getInt(3), rs.getNString(4), rs.getInt(5), rs.getNString(6), rs.getDouble(7));
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
	// 업체 정보 수정 -----------------------------------
	@Override
	public int modifycompany(CompanyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		
		int res = 0;
		
		try {
			pstm = con.prepareStatement(modifycompany);
			pstm.setNString(1, dto.getCom_name());
			pstm.setString(2, dto.getCom_phone());
			pstm.setNString(3, dto.getCom_addr());
			pstm.setInt(4, dto.getCom_category());
			pstm.setInt(5, dto.getCom_location());
			pstm.setNString(6, dto.getM_id());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
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

	//id로 company 정보 가져오기
	@Override
	public CompanyDto selectid(String m_id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		CompanyDto dto = new CompanyDto();
		try {
			pstm = con.prepareStatement(selectid);
			pstm.setString(1,m_id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				dto = new CompanyDto(rs.getNString(1),rs.getNString(2),rs.getInt(3),rs.getNString(4),rs.getInt(5),rs.getNString(6),rs.getDouble(7));
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
	// 업체 정보 등록하기
	@Override
	public int insertcom(CompanyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		int res = 0;
		try {
			pstm = con.prepareStatement(insertcom);
			pstm.setNString(1, dto.getM_id());
			pstm.setNString(2, dto.getCom_name());
			pstm.setInt(3, dto.getCom_location());
			pstm.setNString(4, dto.getCom_addr());
			pstm.setInt(5, dto.getCom_category());
			pstm.setNString(6, dto.getCom_phone());
			pstm.setDouble(7,dto.getCom_score());
		
			res = pstm.executeUpdate();
			
			if(res>0) {
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
}
