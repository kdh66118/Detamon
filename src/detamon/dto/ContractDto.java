package detamon.dto;

public class ContractDto {
	private int con_no; //계약번호
	private int board_no; //게시글 번호
	private String parttimer_id; //알바생 아이디
	private String company_id; //사장님 아이디
	private String is_contract; //계약 여부 Y/N
	private String albaestimate; //업체가 알바생평가 
	private String comestimate; //알바생이 업체평가
	public ContractDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContractDto(int con_no, int board_no, String parttimer_id, String company_id, String is_contract,
			String albaestimate, String comestimate) {
		super();
		this.con_no = con_no;
		this.board_no = board_no;
		this.parttimer_id = parttimer_id;
		this.company_id = company_id;
		this.is_contract = is_contract;
		this.albaestimate = albaestimate;
		this.comestimate = comestimate;
	}
	public int getCon_no() {
		return con_no;
	}
	public void setCon_no(int con_no) {
		this.con_no = con_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getParttimer_id() {
		return parttimer_id;
	}
	public void setParttimer_id(String parttimer_id) {
		this.parttimer_id = parttimer_id;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	public String getIs_contract() {
		return is_contract;
	}
	public void setIs_contract(String is_contract) {
		this.is_contract = is_contract;
	}
	public String getAlbaestimate() {
		return albaestimate;
	}
	public void setAlbaestimate(String albaestimate) {
		this.albaestimate = albaestimate;
	}
	public String getComestimate() {
		return comestimate;
	}
	public void setComestimate(String comestimate) {
		this.comestimate = comestimate;
	}
	
}
