package detamon.dto;

public class JobBoardTypeDto {
	private int type_no; //게시판 종류
	private String board_name; // 게시판이름
	public JobBoardTypeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobBoardTypeDto(int type_no, String board_name) {
		super();
		this.type_no = type_no;
		this.board_name = board_name;
	}
	public int getType_no() {
		return type_no;
	}
	public void setType_no(int type_no) {
		this.type_no = type_no;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	
	
}
