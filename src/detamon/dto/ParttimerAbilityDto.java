package detamon.dto;

public class ParttimerAbilityDto {
	private int m_no; //회원 번호
	private String m_id; //회원 아이디
	private int c_no; //카테고리 번호
	private int a_cnt; //계약 횟수
	public ParttimerAbilityDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ParttimerAbilityDto(int m_no, String m_id, int c_no, int a_cnt) {
		super();
		this.m_no = m_no;
		this.m_id = m_id;
		this.c_no = c_no;
		this.a_cnt = a_cnt;
	}
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getA_cnt() {
		return a_cnt;
	}
	public void setA_cnt(int a_cnt) {
		this.a_cnt = a_cnt;
	}
	
	

}
