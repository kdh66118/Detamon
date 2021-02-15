package detamon.dto;

public class MemberScoreDto {
	private int score_no;
	private String id;		//평가 받는 아이디
	private int score; 		// 평점
	public MemberScoreDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberScoreDto(int score_no, String id, int score) {
		super();
		this.score_no = score_no;
		this.id = id;
		this.score = score;
	}
	public int getScore_no() {
		return score_no;
	}
	public void setScore_no(int score_no) {
		this.score_no = score_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
