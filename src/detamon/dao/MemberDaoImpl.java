package detamon.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import detamon.dto.MemberDto;
import detamon.dto.ParttimerAbilityDto;

import static common.JDBCTemplate.*;

public class MemberDaoImpl implements MemberDao {

	
	//로그인
	@Override
	public MemberDto login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto login = new MemberDto();
		
		try {
			pstm = con.prepareStatement(loginSql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			
			rs = pstm.executeQuery();
			
			while ( rs.next() ){
				login.setM_no(rs.getInt(1));
				login.setM_id(rs.getString(2));
				login.setM_pw(rs.getString(3));
				login.setM_name(rs.getString(4));
				login.setM_rno(rs.getString(5));
				login.setM_phone(rs.getString(6));
				login.setM_addr(rs.getString(7));
				login.setM_email(rs.getNString(8));
				login.setM_gender(rs.getString(9));
				login.setM_role(rs.getInt(10));
				login.setM_score(rs.getDouble(11));
				login.setM_enabled(rs.getString(12));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
			close(rs);
			close(con);
		}
		
		return login;
	}

	//아이디 중복 체크
	@Override
	public boolean idChk(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean idused = false;
		
		try {
			pstm = con.prepareStatement(idChkSql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			if ( rs.next()) {
				idused = true;
			}else {
				idused = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return idused;
	}

	//회원가입
	@Override
	public int signup(MemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(signupSql);
			pstm.setString(1, dto.getM_id());
			pstm.setString(2, dto.getM_pw());
			pstm.setNString(3, dto.getM_name());
			pstm.setNString(4, dto.getM_rno());
			pstm.setNString(5, dto.getM_phone());
			pstm.setNString(6, dto.getM_addr());
			pstm.setNString(7, dto.getM_email());
			pstm.setNString(8, dto.getM_gender());
			pstm.setInt(9, dto.getM_role());
			
			res = pstm.executeUpdate();
			
			if ( res > 0 ) {
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

	//아이디 찾기
	@Override
	public String findId(String name, String rno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String findId = null;
		
		try {
			pstm = con.prepareStatement(selectIdByNameRno);
			pstm.setString(1, name);
			pstm.setString(2, rno);
			
			rs = pstm.executeQuery();
			
			while( rs.next() ) {
				findId = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return findId;
	}

	//비밀번호 찾기(이메일)
	@Override
	public String findEmailById(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String findEmail = null;
		
		try {
			pstm = con.prepareStatement(selectEmailById);
			pstm.setString(1, id);
			
			rs = pstm.executeQuery();
			
			while ( rs.next() ) {
				findEmail = rs.getNString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return findEmail;
	}

	//비밀번호 찾기
	@Override
	public String findPw(String id) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		ResultSet rs = null;
		String findpw = null;
		
		try {
			pstm = con.prepareStatement(selectPwById);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while( rs.next() ) {
				findpw = rs.getNString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		return findpw;
	}
	
		//작성자로 멤버 가져오기
		@Override
		public MemberDto selectOneById(String id) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			MemberDto dto = null;
			
			try {
				pstm = con.prepareStatement(selectOneById);
				pstm.setNString(1, id);
				
				rs = pstm.executeQuery();
				
				while( rs.next() ) {
					dto = new MemberDto();
					
					dto.setM_no(rs.getInt(1));
					dto.setM_id(rs.getString(2));
					dto.setM_pw(rs.getString(3));
					dto.setM_name(rs.getString(4));
					dto.setM_rno(rs.getString(5));
					dto.setM_phone(rs.getString(6));
					dto.setM_addr(rs.getString(7));
					dto.setM_email(rs.getNString(8));
					dto.setM_gender(rs.getString(9));
					dto.setM_role(rs.getInt(10));
					dto.setM_score(rs.getDouble(11));
					dto.setM_enabled(rs.getString(12));
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

		//지원자 성별 리스트 반환
		@Override
		public List<String> selectAlbaGender( List<String> albaIdList) {
			Connection con =getConnection();
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<String> genderList = new ArrayList<String>();
			
			try {
				pstm = con.prepareStatement(selectAlbaGenderSql);
				for(int i = 0 ; i < albaIdList.size(); i++) {
					pstm.setString(1, albaIdList.get(i) );
					
					rs = pstm.executeQuery();
					
					while ( rs.next() ) {
						genderList.add(rs.getString(1));
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return genderList;
		}
		//아이디로 알바능숙도 반환
		@Override
		public List<ParttimerAbilityDto> selectAbilityById(String writer) {
			Connection con = getConnection();
			PreparedStatement pstm = null;
			List<ParttimerAbilityDto> list = new ArrayList<ParttimerAbilityDto>();
			ResultSet rs = null;
			
			try {
				pstm = con.prepareStatement(selectAbilityByIdSql);
				pstm.setString(1, writer);
				rs = pstm.executeQuery();
				
				while (rs.next()) {
					ParttimerAbilityDto dto = new ParttimerAbilityDto();
					dto.setM_no(rs.getInt(1));
					dto.setM_id(rs.getNString(2));
					dto.setC_no(rs.getInt(3));
					dto.setA_cnt(rs.getInt(4));
					
					list.add(dto);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return list;
		}

		//아이디로 멤버 이름 검색
		@Override
		public String selectNameById(String id) {
			Connection con = getConnection();
			PreparedStatement pstm =null;
			ResultSet rs = null;
			String m_name = null;
			
			try {
				pstm = con.prepareStatement(selectNameByIdSql);
				pstm.setString(1, id);
				
				rs = pstm.executeQuery();
				
				while( rs.next() ) {
					m_name = rs.getNString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(rs);
				close(pstm);
				close(con);
			}
			return m_name;
		}
		//회원 탈퇴-------------------------------------------------
				@Override
				public int deletemeber(MemberDto dto) {
					Connection con = getConnection();
					PreparedStatement pstm = null;
					int res = 0;
					
					try {
						pstm = con.prepareStatement(deletemember);
						pstm.setNString(1,dto.getM_id());
						
						res=pstm.executeUpdate();
						
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
				//비밀번호 변경 --------------------------------------------------
				@Override
				public int changepw(MemberDto dto) {
					Connection con = getConnection();
					PreparedStatement pstm = null;
					
					int res =0;
					
					
					try {
						pstm = con.prepareStatement(changepw);
						pstm.setString(2, dto.getM_id());
						pstm.setString(1, dto.getM_pw());
						
						res= pstm.executeUpdate();
						
						if(res>0) {
							commit(con);
						}else {
							rollback(con);
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close(pstm);
						close(con);
					}
						
					
					return res;
				}

				@Override
				public int modifyMember(MemberDto dto) {
					Connection con = getConnection();
					PreparedStatement pstm = null;
					
					int res = 0;
					
					try {
						pstm = con.prepareStatement(modifymember);
						pstm.setNString(1, dto.getM_phone() );
						pstm.setNString(2, dto.getM_email());
						pstm.setString(3,dto.getM_addr());
						pstm.setNString(4, dto.getM_name());
						pstm.setNString(5, dto.getM_id());
						
						res=pstm.executeUpdate();
						
						if(res>0) {
							commit(con);
						}else {
							rollback(con);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close(pstm);
						close(con);
					}
					
					return res;
				}

				//신뢰도 가져오기
				@Override
				public List<MemberDto> selectPageScore(List<String> writerList) {
					Connection con = getConnection();
					PreparedStatement pstm = null;
					ResultSet rs = null;
					List<MemberDto> scorelist = new ArrayList<MemberDto>();
					try {
						pstm = con.prepareStatement(selectPageScore);
						
						for( int i = 0 ; i < writerList.size(); i++) {
							pstm.setNString(1, writerList.get(i));
							rs = pstm.executeQuery();
							
							if ( rs.next()) {
								MemberDto dto = new MemberDto(rs.getDouble(1));
								scorelist.add(dto);
							}
						}
						
						
					} catch (SQLException e) {
						e.printStackTrace();
					}finally {
						if(rs!=null) {
							close(rs);
							
						}
						close(pstm);
						close(con);
					}
					
					return scorelist;
				}
				//마이페이지폼 프로필확인 숙련도 가져오기
				@Override
				public List<ParttimerAbilityDto> profile(String writer) {
					Connection con = getConnection();
					PreparedStatement pstm = null;
					List<ParttimerAbilityDto> list = new ArrayList<ParttimerAbilityDto>();
					ResultSet rs = null;
					
					
					try {
						pstm = con.prepareStatement(profile);
						pstm.setNString(1, writer);
						rs= pstm.executeQuery();
						while(rs.next()) {
							ParttimerAbilityDto dto = new ParttimerAbilityDto();
							dto.setM_no(rs.getInt(1));
							dto.setM_id(rs.getNString(2));
							dto.setC_no(rs.getInt(3));
							dto.setA_cnt(rs.getInt(4));
							
							list.add(dto);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close(rs);
						close(pstm);
						close(con);
					}
					
					
					
					return list;
				}
				
				// 신뢰도 점수 가져오기
				public List<MemberDto> score() {
					Connection con = getConnection();
					PreparedStatement pstm = null;
					ResultSet rs = null;
					List<MemberDto> scorelist = new ArrayList<MemberDto>();
					try {
						pstm = con.prepareStatement(score);
						rs = pstm.executeQuery();
						
						while(rs.next()) {
							MemberDto dto = new MemberDto(rs.getDouble(1));
							scorelist.add(dto);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}finally {
						close(rs);
						close(pstm);
						close(con);
					}
					
					return scorelist;
				}
				

}
