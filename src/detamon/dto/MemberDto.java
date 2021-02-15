package detamon.dto;

public class MemberDto {
	
	private int m_no; //회원 번호
	private String m_id; //회원 아이디
	private String m_pw; //회원 비밀번호
	private String m_name;//회원 이름
	private String m_rno;  //주민등록번호
	private String m_phone; //핸드폰 번호
	private String m_addr; //회원 주소
	private String m_email; //회원 이메일
	private String m_gender; //회원 성별
	private int m_role; //회원 등급(번호)
	private double m_score; // 평가 점수
	private String m_enabled; //회원가입 여부
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MemberDto(int m_no, String m_id, String m_pw, String m_name, String m_rno, String m_phone, String m_addr,
			String m_email, String m_gender, int m_role, double m_score, String m_enabled) {
		super();
		this.m_no = m_no;
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_rno = m_rno;
		this.m_phone = m_phone;
		this.m_addr = m_addr;
		this.m_email = m_email;
		this.m_gender = m_gender;
		this.m_role = m_role;
		this.m_score = m_score;
		this.m_enabled = m_enabled;
	}


	public MemberDto(String m_phone, String m_email, String m_addr, String m_name, String m_id) {
		super();
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_addr = m_addr;
		this.m_email = m_email;
	}


	public MemberDto(String m_id, String m_pw) {
		super();
		this.m_id = m_id;
		this.m_pw = m_pw;
	}


	public MemberDto(double m_score) {
		super();
		this.m_score = m_score;
	}


	public String getM_email() {
		return m_email;
	}


	public void setM_email(String m_email) {
		this.m_email = m_email;
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
	public String getM_pw() {
		return m_pw;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_rno() {
		return m_rno;
	}
	public void setM_rno(String m_rno) {
		this.m_rno = m_rno;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_addr() {
		return m_addr;
	}
	public void setM_addr(String m_addr) {
		this.m_addr = m_addr;
	}
	public String getM_gender() {
		return m_gender;
	}
	public void setM_gender(String m_gender) {
		this.m_gender = m_gender;
	}
	public int getM_role() {
		return m_role;
	}
	public void setM_role(int m_role) {
		this.m_role = m_role;
	}
	public double getM_score() {
		return m_score;
	}
	public void setM_score(double m_score) {
		this.m_score = m_score;
	}
	public String getM_enabled() {
		return m_enabled;
	}
	public void setM_enabled(String m_enabled) {
		this.m_enabled = m_enabled;
	}
	
	
	

}
