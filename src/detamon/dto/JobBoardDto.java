package detamon.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JobBoardDto {
	private int no;
	private String writer;
	private String title;
	private String content;
	private Date start_date;
	private Date end_date;
	private Date start_time;
	private Date end_time;
	private int money;
	private int loc;
	private int category;
	private Date regDate;
	private int cnt;
	private int type_no;
	private double distance;
	private String addr;
	private String com_name;
	
	public JobBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public JobBoardDto(String writer, String title, String content, int money, int loc, int category) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.money = money;
		this.loc = loc;
		this.category = category;
	}



	public JobBoardDto(String writer, String title, String content, Date start_date, Date end_date, Date start_time,
			Date end_time, int money, int loc, int category) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.money = money;
		this.loc = loc;
		this.category = category;
	}

	public JobBoardDto(int no, String writer, String title, String content, Date start_date, Date end_date,
			Date start_time, Date end_time, int money, int loc, int category, Date regDate, int type_no) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.money = money;
		this.loc = loc;
		this.category = category;
		this.regDate = regDate;
		this.type_no = type_no;
	}

	public JobBoardDto(int no, String writer, String title, String content, Date start_date, Date end_date,
			Date start_time, Date end_time, int money, int loc, int category, Date regDate, int cnt, int type_no) {
		super();
		this.no = no;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
		this.money = money;
		this.loc = loc;
		this.category = category;
		this.regDate = regDate;
		this.cnt = cnt;
		this.type_no = type_no;
	}
	public JobBoardDto(int no, int loc) {
		super();
		this.no = no;
		this.loc = loc;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getType_no() {
		return type_no;
	}

	public void setType_no(int type_no) {
		this.type_no = type_no;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	@Override
	public String toString() {
		return "JobBoardDto [no=" + no + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", start_time=" + start_time + ", end_time="
				+ end_time + ", money=" + money + ", loc=" + loc + ", category=" + category + ", regDate=" + regDate
				+ ", cnt=" + cnt + ", type_no=" + type_no + ", distance=" + distance + ", addr=" + addr + ", com_name="
				+ com_name + "]";
	}
	
	
	
	
}
