package detamon.dto;

public class LocationDto {
	
	private int l_no; //로케이션 번호
	private String l_name; // 로케이션 이름
	public LocationDto(int l_no, String l_name) {
		super();
		this.l_no = l_no;
		this.l_name = l_name;
	}
	public LocationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getL_no() {
		return l_no;
	}
	public void setL_no(int l_no) {
		this.l_no = l_no;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	
	
}
