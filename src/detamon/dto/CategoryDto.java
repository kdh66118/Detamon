package detamon.dto;

public class CategoryDto {
	private int c_no; //카테고리 번호
	private String c_name; // 카테고리 이름
	
	public CategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CategoryDto(int c_no, String c_name) {
		super();
		this.c_no = c_no;
		this.c_name = c_name;
	}

	public int getC_no() {
		return c_no;
	}

	public void setC_no(int c_no) {
		this.c_no = c_no;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	
	

}
