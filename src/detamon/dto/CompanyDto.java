package detamon.dto;

public class CompanyDto {
	private String m_id;
	private String com_name;
	private int com_location;
	private String com_addr;
	private int com_category;
	private String com_phone;
	private double com_score;
	
	public CompanyDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CompanyDto(String m_id, String com_name, int com_location, String com_addr, int com_category,
			String com_phone, double com_score) {
		super();
		this.m_id = m_id;
		this.com_name = com_name;
		this.com_location = com_location;
		this.com_addr = com_addr;
		this.com_category = com_category;
		this.com_phone = com_phone;
		this.com_score = com_score;
	}

	public CompanyDto(String com_name, String com_phone,String com_addr, int com_category, int com_location,
			String m_id) {
		super();
		this.m_id = m_id;
		this.com_name = com_name;
		this.com_location = com_location;
		this.com_addr = com_addr;
		this.com_category = com_category;
		this.com_phone = com_phone;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public CompanyDto(String com_name, String com_phone, String com_addr, int com_category, String m_id) {
		super();
		this.m_id = m_id;
		this.com_name = com_name;
		this.com_addr = com_addr;
		this.com_category = com_category;
		this.com_phone = com_phone;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public int getCom_location() {
		return com_location;
	}

	public void setCom_location(int com_location) {
		this.com_location = com_location;
	}

	public String getCom_addr() {
		return com_addr;
	}

	public void setCom_addr(String com_addr) {
		this.com_addr = com_addr;
	}

	public int getCom_category() {
		return com_category;
	}

	public void setCom_category(int com_category) {
		this.com_category = com_category;
	}

	public String getCom_phone() {
		return com_phone;
	}

	public void setCom_phone(String com_phone) {
		this.com_phone = com_phone;
	}

	public double getCom_score() {
		return com_score;
	}

	public void setCom_score(double com_score) {
		this.com_score = com_score;
	}

	@Override
	public String toString() {
		return "CompanyDto [m_id=" + m_id + ", com_name=" + com_name + ", com_location=" + com_location + ", com_addr="
				+ com_addr + ", com_category=" + com_category + ", com_phone=" + com_phone + ", com_score=" + com_score
				+ "]";
	}
	
	
	
}