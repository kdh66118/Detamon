package detamon.dto;

public class MemberRoleDto {
	private int rolo_no; //등급 번호
	private String role_name; // 등급 이름
	public MemberRoleDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberRoleDto(int rolo_no, String role_name) {
		super();
		this.rolo_no = rolo_no;
		this.role_name = role_name;
	}
	public int getRolo_no() {
		return rolo_no;
	}
	public void setRolo_no(int rolo_no) {
		this.rolo_no = rolo_no;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
}
