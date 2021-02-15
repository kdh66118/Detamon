package detamon.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import detamon.biz.CompanyBiz;
import detamon.biz.CompanyBizImpl;
import detamon.biz.ContractBiz;
import detamon.biz.ContractBizImpl;
import detamon.biz.JobBoardBiz;
import detamon.biz.JobBoardBizImpl;
import detamon.biz.MemberBiz;
import detamon.biz.MemberBizImpl;
import detamon.dto.CategoryDto;
import detamon.dto.CompanyDto;
import detamon.dto.ContractDto;
import detamon.dto.JobBoardDto;
import detamon.dto.LocationDto;
import detamon.dto.MemberDto;
import detamon.dto.MemberScoreDto;
import detamon.dto.ParttimerAbilityDto;

@WebServlet("/JobBoardController")
public class DetamonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static int pageSize = 15; //한페이지에 보여줄 개수
	private final static int pageGroupSize = 5; //페이지 그룹 사이즈
    public DetamonController() {
        super();
    }

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+command+"]");
		
		MemberBiz memberBiz = new MemberBizImpl();
		JobBoardBiz boardBiz = new JobBoardBizImpl();
		CompanyBiz companyBiz = new CompanyBizImpl();
		ContractBiz contractBiz = new ContractBizImpl(); 
		
		
		//근처 업체 찾기(검색)
		if(command.equals("findnearcom")) {
			
			
			String findAddr = request.getParameter("postaddr");
			
			//페이지 번호받기
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			
			
			
			double findx = Double.parseDouble(request.getParameter("findx"));
			double findy = Double.parseDouble(request.getParameter("findy"));
			
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			
			
			List<JobBoardDto> findList =  boardBiz.selectStoreBoard();
			
			//모든 게시글에 실제주소(addr) 추가
			for( int i = 0 ; i < findList.size() ; i++) {
				String id = findList.get(i).getWriter();
				String addr = companyBiz.selectAddrById(id);
				findList.get(i).setAddr( addr );
			}		
			
			request.setAttribute("findaddr", findAddr);
			request.setAttribute("list", findList);
			request.setAttribute("findx", findx);
			request.setAttribute("findy", findy);
			request.setAttribute("pagenum", pageNum);
			
			
			
			
			
			dispatch("views/findnearcom/getdistance.jsp",request, response);
			
		}
		//근처 업체 검색 결과
		else if (command.equals("findnearcomres")) {
			String[] distance = request.getParameterValues("distance");
			String[] no = request.getParameterValues("no");
			String findAddr = request.getParameter("findaddr");
			
			
			double findx = Double.parseDouble(request.getParameter("findx"));
			double findy = Double.parseDouble(request.getParameter("findy"));

		
			List<JobBoardDto> findlist = new ArrayList<JobBoardDto>();
			
			
			
			
			//현재페이지만 가져오기
			List<JobBoardDto> allList = boardBiz.selectStoreBoard();
			System.out.println("사이즈"+allList.size());
			//모든 게시글에 실제주소(addr) 추가
			//모든 게시글에 계산한 거리(distance) 추가
			for( int i = 0 ; i < allList.size() ; i++) {
				String id = allList.get(i).getWriter();
				String addr = companyBiz.selectAddrById(id);
				allList.get(i).setAddr( addr );
				
				allList.get( Integer.parseInt(no[i]) ).setDistance( Double.parseDouble(distance[i]) );
			}
			
			//모든 게시글에 회사 이름 추가
			for(int i = 0 ; i < allList.size() ; i++) {
				String id = allList.get(i).getWriter();
				String com_name = companyBiz.selectNameById(id);
				allList.get(i).setCom_name(com_name);
				
			}
			
			
			SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
			Calendar cal = Calendar.getInstance();
			String today = null;
			today = formatter.format(cal.getTime());
			
			String tmpday = null;
			//기간이 안지난것만
			for(int i = 0 ; i < allList.size(); i++) {
				tmpday = formatter.format(allList.get(i).getStart_date());
				Date Dtoday = null;
				Date Dtmpday = null;
				try {
					Dtoday= formatter.parse(today);
					Dtmpday = formatter.parse(tmpday);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				int compare = Dtoday.compareTo( Dtmpday );
				
				if(compare < 0 && allList.get(i).getDistance() < 10){  ////거리조건
					//System.out.println("day1 < day2");
					findlist.add(allList.get(i));
				}
				
				
			}
			
			//////////////////페이징처리////////////////////////////////////
			
			
			
			
			int currentPage = Integer.parseInt(request.getParameter("pagenum")); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			
			
			/////////결과 페이지 만듬
			List<JobBoardDto> findResList = new ArrayList<JobBoardDto>();
			
			for(int i = startRow-1 ; i < endRow; i++) {
				if( i  < findlist.size()) {
					findResList.add(findlist.get(i));
				}
			}
			////////////
			
			int boardCnt = findlist.size(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			/////////////////////////////////////////////////////////////////////////
			
			//계산 결과 세션에 저장 페이징할때사용 (속도개선)
			HttpSession session = request.getSession(true);			
			session.setAttribute("nearcom", findlist);
			session.setMaxInactiveInterval(60*60);
			
			
			findAddr = URLDecoder.decode(findAddr,"UTF-8");
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("findx", findx);
			request.setAttribute("findy", findy);
			request.setAttribute("findaddr", findAddr);
			request.setAttribute("findnearlist", findResList);
			dispatch("views/findnearcom/findnearcomform.jsp",request, response);
		}
		//페이지 이동할때 들어옴 (세션 정보를 가져와서 속도를 빠르게)
		else if (command.equals("movepage")) {
			String findAddr = request.getParameter("findaddr");
			
			HttpSession session = request.getSession(true);
		    //로그인되어있는 정보로 member가져오기
			MemberDto dto = (MemberDto)session.getAttribute("login");
			
			
			List<JobBoardDto> findlist = (List<JobBoardDto>)session.getAttribute("nearcom");
			
			double findx = Double.parseDouble(request.getParameter("findx"));
			double findy = Double.parseDouble(request.getParameter("findy"));
			
			/////////////////////////페이징
			int currentPage = Integer.parseInt(request.getParameter("pagenum")); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			
			int boardCnt = findlist.size(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			/////////////////////////////////////////////////////////////////////////
			
			findAddr = URLDecoder.decode(findAddr,"UTF-8");
			
			List<JobBoardDto> findResList = new ArrayList<JobBoardDto>();
			
			for(int i = startRow-1 ; i < endRow; i++) {
				if( i  < findlist.size()) {
					findResList.add(findlist.get(i));
				}
			}			
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("findx", findx);
			request.setAttribute("findy", findy);
			request.setAttribute("findaddr", findAddr);
			request.setAttribute("findnearlist", findResList);
			dispatch("views/findnearcom/findnearcomform.jsp",request, response);
		}
		//근처 업체 찾기 폼 (처음 진입)
		else if(command.equals("findnearcomform")) {
			dispatch("views/findnearcom/findnearcomform.jsp",request, response);
			//response.sendRedirect("views/findnearcom/findnearcomform.jsp");
		}
		//홈으로 이동
			else if(command.equals("home")) {
				List<LocationDto> locList = boardBiz.selectAllLoc();
				List<CategoryDto> categoryList = boardBiz.selectAllCategory();
				
				List<JobBoardDto> parttimerCntList = boardBiz.selectCntList(1);
				List<JobBoardDto> companyCntList = boardBiz.selectCntList(2);
				
				
				//모든 게시글에 회사 이름 추가
				for(int i = 0 ; i < companyCntList.size() ; i++) {
					String id = companyCntList.get(i).getWriter();
					String com_name = companyBiz.selectNameById(id);
					companyCntList.get(i).setCom_name(com_name);
					String addr = companyBiz.selectAddrById(id);
					companyCntList.get(i).setAddr( addr );
				}
				
						
				
				
				request.setAttribute("parttimerlist", parttimerCntList);
				request.setAttribute("companylist", companyCntList);
				request.setAttribute("categorylist", categoryList);
				request.setAttribute("loclist", locList);
				dispatch("views/home/home.jsp",request, response);
				//response.sendRedirect("home.jsp");
			
			}
		//계약확인 폼(계약확인)
		else if(command.equals("contract")) {	
		    HttpSession session = request.getSession(true);
		    //로그인되어있는 아이디로 member정보 가져오기
			MemberDto dto = (MemberDto)session.getAttribute("login");
			System.out.println("dtoId:"+dto);
			//로그인 정보 없을시
			if(dto == null) {
				jsResponse("로그인 해주세요!", "loginform", response);
			}else {
			//로그인 되어있는id로 멤버 정보 가져오기
			MemberDto udto = contractBiz.selectMember(dto.getM_id());
			System.out.println("udto"+udto.getM_role()+":"+udto.getM_addr()+":"+udto.getM_id());
			session.setAttribute("udto", udto);
			//id와 등급으로 계약 정보 리스트로 가져오기
			List<ContractDto> cdto = new ArrayList<ContractDto>(contractBiz.selectContract(udto.getM_id(), udto.getM_role()));
			System.out.println("등급: "+udto.getM_role());
			
			//계약정보들 게시글번호로 조회하기
			List<JobBoardDto> jlistdto = new ArrayList<JobBoardDto>();
			for(ContractDto condto : cdto) {
			    JobBoardDto jdto = (JobBoardDto)contractBiz.selectJobBoard(condto.getBoard_no());
			    System.out.println("dto 투스티링"+dto.toString());
				jlistdto.add(jdto);
			    System.out.println("ㅋㅋㅋㅋㅋ"+jlistdto.toString());
			}
			request.setAttribute("jlistdto", jlistdto);
			session.setAttribute("cdto", cdto);	
			System.out.println("cdto있나"+cdto.isEmpty());
			
			//페이징
				
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			System.out.println("페이지넘버"+currentPage);
			//현재 로그인 아이디 등급
			String loginId = udto.getM_id();
			int role = udto.getM_role();
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = cdto.size(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			System.out.println("전체게시글 수 "+boardCnt);
			System.out.println("첫번호"+startRow+"마지막번호"+endRow);
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			System.out.println("시작페이지"+startPage+"끝페이지"+endPage);
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			
			List<ContractDto> pagingList = contractBiz.pageContract(startRow, endRow, loginId, role);
			System.out.println("pagingList"+pagingList.toString());
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("list", pagingList);
			
			dispatch("views/contract/contract.jsp", request, response);
			}
		}
		//계약확인 폼(체결된계약)
		else if(command.equals("contracted")) {
			System.out.println("널?");
		    HttpSession session = request.getSession(true);
			String userId = request.getParameter("userId");
			System.out.println("널??"+userId);
			String is_contract = request.getParameter("is_contract");
			System.out.println("널?222?"+is_contract);
			int role = Integer.parseInt(request.getParameter("role"));
			System.out.println("널????"+role);
			System.out.println("userId: "+ userId + "in_contract"+ is_contract+"등급:"+role);
			//등급, 아이디, 계약여부 Y인 계약만 가져오기 
			List<ContractDto> contractedDto = new ArrayList<ContractDto>(contractBiz.selectContracted(userId, is_contract, role)); 
			session.setAttribute("conDto", contractedDto);
			System.out.println("contractDto"+contractedDto.toString());
			
			List<JobBoardDto> jlistdto = new ArrayList<JobBoardDto>();
			List<MemberScoreDto> scoredto = new ArrayList<MemberScoreDto>();
			for(ContractDto dto : contractedDto) {
			    JobBoardDto jdto = (JobBoardDto)contractBiz.selectJobBoard(dto.getBoard_no());
			    System.out.println("dto 투스티링"+dto.toString());
				jlistdto.add(jdto);
				
				//MemberScoreDto msdto = (MemberScoreDto)contractBiz.checkScore(dto.getBoard_no());
				//scoredto.add(msdto);
			    System.out.println("ㅋㅋㅋㅋㅋ"+jlistdto.toString());
			    System.out.println("게시글번호로 점수판 조회"+scoredto.toString());
			}
			session.setAttribute("jlistdto", jlistdto);
			session.setAttribute("scoredto", scoredto);
			
			
			//페이징
			
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			System.out.println("페이지넘버"+currentPage);
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = contractedDto.size(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			System.out.println("전체게시글 수 "+boardCnt);
			System.out.println("첫번호"+startRow+"마지막번호"+endRow);
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			System.out.println("시작페이지"+startPage+"끝페이지"+endPage);
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			
			List<ContractDto> pagingList = contractBiz.pageContracted(startRow, endRow, userId, role);
			System.out.println("pagingList"+pagingList.toString());
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("list", pagingList);		
			
			dispatch("views/contract/contracted.jsp", request, response);
		}
		//계약확인 폼(정보확인)
		else if(command.equals("contractinfo")) {
		    HttpSession session = request.getSession(true);
			//게시글 정보 가져오기
		    int jobBoardNo = Integer.parseInt(request.getParameter("jobboardno"));
		    System.out.println("jobboardno:"+jobBoardNo);
		    JobBoardDto jdto = (JobBoardDto)contractBiz.selectJobBoard(jobBoardNo);
		    System.out.println("게시글정보 :"+jdto.toString());
			session.setAttribute("jdto", jdto);	
			
			//게시글 번호로 계약조회
			ContractDto cdto = contractBiz.checkScore(jobBoardNo);
			String com = cdto.getComestimate();
			String alba = cdto.getAlbaestimate();
			request.setAttribute("com", com);
			request.setAttribute("alba", alba);
			request.setAttribute("cdto", cdto);
			System.out.println("업체평가여부 :" + com+"알바생평가여부zz"+alba+"계약정보"+cdto.toString());
		    //회사정보 가져오기
		    String companyId = request.getParameter("companyId");
		    System.out.println("회사아이디 : " + companyId);
		    CompanyDto pdto = (CompanyDto)contractBiz.selectCompany(companyId);
			MemberDto comdto = contractBiz.selectMember(companyId);
			//회사 이메일
			String com_email = comdto.getM_email();
			request.setAttribute("com_email", com_email);
			
			//회사 카테고리
			String comCategory = contractBiz.comCategory(pdto.getCom_category());
			System.out.println("회사카테고리는"+comCategory);
			request.setAttribute("comCategory", comCategory);
				
			session.setAttribute("comdto", comdto);
			session.setAttribute("pdto", pdto);	
			System.out.println("회사정보보오ㅗ:"+pdto.toString()+"사업자 정보"+comdto.toString());	
		
			//알바생 정보 가져오기
			String parttimerId = request.getParameter("parttimerId");
			System.out.println("파트타이머 아이디"+parttimerId);
			MemberDto adto = contractBiz.selectMember(parttimerId);
			session.setAttribute("adto", adto);
			String alba_email = adto.getM_email();
			request.setAttribute("alba_email", alba_email);
			
			//회사.알바생 이메일 확인
			System.out.println("회사이메일"+com_email+"알바 이메일"+alba_email);
			String gender = (adto.getM_gender().equals("M"))?"남자":"여자";
			request.setAttribute("gender", gender);
			// 수락여부
			String accept = request.getParameter("accept");
			System.out.println("계약여부"+accept);
			
			List<ParttimerAbilityDto> albaAblitiy = memberBiz.selectAbilityById(parttimerId);
			request.setAttribute("albaabilitiy", albaAblitiy);
		
			// 평가여부 확인
			//String divide = request.getParameter("divide");
			//System.out.println("스코어"+divide);
			
			dispatch("views/contract/contractinfo.jsp", request, response);
		}
		//정보확인에서 수락 버튼 클릭시 contract테이블 is_contract Y값으로 변경
		else if (command.equals("contracting")) {
			String is_contract = request.getParameter("is_contract");
			String companyId = request.getParameter("companyId");
			String parttimerId = request.getParameter("parttimerId");
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			
			System.out.println("is_contract:"+is_contract+"companyId:"+companyId+"parttimerId: "+ parttimerId+"no:"+board_no);
			
			int res = contractBiz.acceptContract(is_contract, companyId, parttimerId, board_no);
			System.out.println("res:"+res);
			
			if(res>0) {	//업데이트 성공시
				dispatch("views/contract/contract.jsp", request, response);
			}else { //업데이트 실패시
				jsResponse("에러발생!", "contract", response);
			}
		}
		//정보확인에서 거절버튼 클릭시 contract테이블에서is_contract X값으로 변경
		else if(command.equals("cancleContract")) {
			String companyId = request.getParameter("companyId");
			String parttimerId = request.getParameter("parttimerId");
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			
			System.out.println("companyId:"+companyId+"parttimerId: "+ parttimerId+"no:"+board_no);

			int res = contractBiz.cancleContract(companyId, parttimerId, board_no);
			System.out.println("res : "+res);
			
			if(res>0) {
				jsResponse("거절성공!", "contract", response);
			}else { //업데이트 실패시
				jsResponse("에러발생!", "contract", response);
			}
		}
		//알바생id와 업체id, 점수, 회원등급 받아 회원이 업체인지 알바생인지 구분하여 점수주기 
		else if(command.equals("insertScore")) {
			String albaId = request.getParameter("albaId");
			String comId = request.getParameter("comId");	
			int role = Integer.parseInt(request.getParameter("role"));
			int score = Integer.parseInt(request.getParameter("score"));
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			//업종 카테고리 받아오기
			int categoryNum = contractBiz.checkCateNum(comId);			
			System.out.println("회사 카테고리 넘버"+categoryNum);
				
			//게시글 번호로 계약 정보 가져오기
			ContractDto chkEstimate = contractBiz.checkScore(boardno); 
			//알바생이 업체 평가여부
			String comEstimate = chkEstimate.getComestimate();
			//업체가 알바생 평가여부
			String albaEstimate = chkEstimate.getAlbaestimate();
			System.out.println("알바:"+albaId+"업체:"+comId+"등급:"+role+"점수:"+score+"게시글번호:"+boardno+"업체평가:"+comEstimate+"알바평가:"+albaEstimate);
			//insert 결과값 담을res
			int res = 0;
			
			//점수주는 사람이 사업자(role=2)인지 알바생(role=1)인지 구분하여 매개변수 전달
			//업체일때
			if(role==2) {  //role이 2이면 사업자 -> 알바생 점수줌
				res = contractBiz.insertScore(albaId, score, role);
				//contract 테이블에 Y로변경
				contractBiz.updateContract(boardno, 2);
				//알바생 아이디로 평균점수 조회
				int avgScore = contractBiz.avgScore(albaId);
				System.out.println("avgScore는?"+avgScore);
				//알바생 평균점수 갱신
				int upNum = contractBiz.updateAvgScore(albaId, avgScore );
				System.out.println(upNum>0?"갱신완료":"갱신실패");
				
				//알바생도 평가를 했다면 
				if(comEstimate.equals("Y")) {
					// 계약종료
					int contractRes = contractBiz.endContract(boardno);
					//알바생 어빌리티 쌓아주기
					int albaAbility = contractBiz.albaAbility(albaId, categoryNum);
					System.out.println(albaAbility+"이게 1이면 어빌리티 쌓인거임");
					if(contractRes>0) {
						jsResponse("평점주기완료계약종료! 둘다 평가완료", "contract", response);
					}
				}
				//알바생이 평가 안했으면
				else {
					jsResponse("평점주기완료!알바생이 아직안함", "contract", response);
				}
		  }
			//점수 주는 사람이 알바생일떄
			else {
				res = contractBiz.insertScore(comId, score, role);
				contractBiz.updateContract(boardno, 1);		
				//업체 아이디로 평균점수 조회
				int avgScore = contractBiz.avgScore(comId);
				System.out.println("avgScore는?"+avgScore);
				//업체 평균점수 갱신
				int upNum = contractBiz.updateAvgScore(comId, avgScore );
				System.out.println(upNum>0?"갱신완료":"갱신실패");
				
				//업체도 평가를 했다면 
				if(albaEstimate.equals("Y")) {
					// 계약종료
					int contractRes = contractBiz.endContract(boardno);
					int albaAbility = contractBiz.albaAbility(albaId, categoryNum);
					System.out.println(albaAbility+"이게 1이면 어빌리티 쌓인거임");
					if(contractRes>0) {
						jsResponse("평점주기완료계약종료!둘다함", "contract", response);
					}
				}
				//업체가 평가 안했으면
				else {
					jsResponse("평점주기완료!사장이 안함", "contract", response);
				}	
		  }
			
		}
		//체결된 계약에서 팝업창으로 알바생 id와 업체 id 게시글번호 넘겨주기
		else if(command.equals("score")) {
			String alba = request.getParameter("albaId");
			String com = request.getParameter("comId");
			int board_no = Integer.parseInt(request.getParameter("boardno"));
			System.out.println("albaId:"+alba+"comId:"+com);
			
			request.setAttribute("alba", alba);
			request.setAttribute("com", com);
			request.setAttribute("boardno", board_no);
			dispatch("views/contract/estimate.jsp",request,response);
 		}
		//로그인 form으로 
		else if ( command.equals("loginform")) {
			dispatch("views/login/loginform.jsp", request, response);
			
		}
		//로그인 
		else if ( command.equals("login")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			MemberDto login = memberBiz.login(id,pw);
			//로그인성공
			if(login.getM_id() != null) {
				HttpSession session = request.getSession(true);
				
				session.setAttribute("login", login);
				session.setMaxInactiveInterval(60*60);
				
				//홈으로 이동
				dispatch("controller.do?command=home",request, response);
			}
			//로그인 실패
			else {
				//로그인폼으로 다시 이동
				jsResponse("로그인 실패!", "loginform", response);
				//dispatch("views/login/loginform.jsp", request, response);
			}
		}
		//로그아웃
		else if( command.equals("logout")) {
			HttpSession session = request.getSession(true);
			
			session.invalidate();
			dispatch("controller.do?command=home",request, response);
			
		}
		//회원 가입 선택
		else if ( command.equals("joinform")) {
			dispatch("views/join/join.jsp",request, response);
		}
		//회원 가입(정보입력
		else if( command.equals("joininputform")) {
			//1-> 알바생 2->사장님
			int m_role = Integer.parseInt(request.getParameter("m_role"));
			
			request.setAttribute("m_role", m_role);
			dispatch("views/join/joininputform.jsp",request, response);
			
		}
		//아이디 중복체크
		else if( command.equals("idchk")) {
			String id = request.getParameter("id");
			
			boolean isUsed = memberBiz.idChk(id);
			
			request.setAttribute("id", id);
			request.setAttribute("isused", isUsed);
			dispatch("views/join/idchk.jsp",request, response);
		}
		//마이페이지 폼 (로그인 했을시만 접근가능)
				else if(command.equals("mypage")) {
					  HttpSession session = request.getSession(true);
					    //로그인되어있는 정보로 member가져오기
						MemberDto dto = (MemberDto)session.getAttribute("login");
					
						
						
						//로그인 정보 없을시
						if(dto == null) {
							jsResponse("로그인 해주세요!", "loginform", response);
						}else {
							CompanyDto dto2 = companyBiz.selectid(dto.getM_id());
							
							request.setAttribute("dto2", dto2);
							dispatch("views/mypage/mypageform.jsp",request,response);
						}
					
				}
				//업체 등록 폼 으로 이동
				else if(command.equals("storeupload")) {
					HttpSession session = request.getSession(true);
					MemberDto dto = (MemberDto)session.getAttribute("login");
					
					request.setAttribute("dto", dto);
					dispatch("views/mypage/storeupload.jsp",request,response);
				}
				//업체 등록하기 버튼 클릭시 업체정보등록
				else if(command.equals("insertcom")) {
					HttpSession session = request.getSession(true);
					MemberDto dto = (MemberDto)session.getAttribute("login");
					String id = request.getParameter("id");
					String com_name = request.getParameter("com_name");
					String phone = request.getParameter("phone");
					String addr = request.getParameter("addr");
					int category = Integer.parseInt(request.getParameter("category"));
					int loc = Integer.parseInt(request.getParameter("loc"));
					System.out.println("id :"+id);
					System.out.println("com_name :"+com_name);
					System.out.println("phone :"+phone);
					System.out.println("addr :"+addr);
					System.out.println("cat :" +category);
					System.out.println("loc :"+loc);
					
					CompanyDto insert = new CompanyDto(id,com_name,loc,addr,category,phone,0);
					int res = companyBiz.insertcom(insert);
					if( res > 0 ) {//성공
			
						jsResponse("등록 성공!!", "mypage", response);
					}else {//실패
						jsResponse("등록 실패!!", "mypage", response);
					}
					
				}
				//비밀번후 변경 페이지 진입
				else if(command.equals("changeform")) {
					 HttpSession session = request.getSession(true);
					 MemberDto dto = (MemberDto)session.getAttribute("login");
					 request.setAttribute("dto",dto);
					 
					dispatch("views/mypage/changepwd.jsp",request,response);
				}
				//변경 버튼 클릭시 
				else if(command.equals("changepw")) {
					String newpw = request.getParameter("newpwd");
					String oldpw = request.getParameter("pwd");
					
					MemberDto dto = new MemberDto(oldpw,newpw);
					int res = memberBiz.changepw(dto);

					
					if( res > 0 ) {//성공
					HttpSession session = request.getSession(true);
						 
					MemberDto login = memberBiz.login(dto.getM_id(),dto.getM_pw());
					session.setAttribute("login", login);
				    session.setMaxInactiveInterval(60*60);
						
						jsResponse("변경 완료!!", "home", response);
					}else {//실패
						jsResponse("변경 실패!!", "changepwd", response);
					}
				}
				//탈퇴 버튼 yes 클릭시 
				else if(command.equals("deletemember")) {
					HttpSession session = request.getSession(true);
					MemberDto dto = (MemberDto)session.getAttribute("login");
					request.setAttribute("dto",dto);
					
					int res = memberBiz.deletemember(dto);
					
					if(res>0) {
						jsResponse("탈퇴 성공!!", "home", response);
						session.invalidate();
					}else {
						jsResponse("탈퇴 실패!!", "mypage", response);
					}
				}
				// 마이페이지 수정 버튼 클릭시 수정 form 이동 
				else if(command.equals("membermodifyform")) {
					HttpSession session = request.getSession(true);
					 MemberDto dto = (MemberDto)session.getAttribute("login");
					 request.setAttribute("dto",dto);
					dispatch("views/mypage/membermodify.jsp",request,response);
					
				}
				else if(command.equals("membermodify")) {
				
					 String phone = request.getParameter("phone");
					 String email = request.getParameter("email");
					 String addr = request.getParameter("addr");
					 String nickname = request.getParameter("nickname");
					 String id = request.getParameter("id");
					 String pw = request.getParameter("pw");
					 MemberDto dto = new MemberDto(phone,email,addr,nickname,id);
					 
					 int res = memberBiz.modifyMember(dto);
					 
					 if(res>0) {
						 jsResponse("변경 완료!!", "home", response);
						 HttpSession session = request.getSession(true);
						 
						 MemberDto login = memberBiz.login(id,pw);
						 session.setAttribute("login", login);
						session.setMaxInactiveInterval(60*60);
						 
						 
						 
					 }else {
						 jsResponse("변경 실패!!", "membermodifyform", response);
					 }
				}
				//업체 정보 수정 form 이동
				else if(command.equals("storemodifyform")) {
					HttpSession session = request.getSession(true);
					 MemberDto dto = (MemberDto)session.getAttribute("login");
					 CompanyDto com = companyBiz.selectOneById(dto.getM_id());
					 request.setAttribute("com", com);
					
					
					dispatch("views/mypage/storemodify.jsp",request,response);
				}
				//업체 정보 수정 수정버튼 클릭시
				else if(command.equals("storemodify")) {
					 HttpSession session = request.getSession(true);
					 MemberDto dto2 = (MemberDto)session.getAttribute("login");
					String name = request.getParameter("com_name");
					String phone = request.getParameter("phone");
					String email = request.getParameter("email");
					String addr = request.getParameter("addr");
					int loc = Integer.parseInt(request.getParameter("loc"));
					int category = Integer.parseInt(request.getParameter("category"));
					System.out.println("name :"+name);
					System.out.println("phone :"+phone);
					System.out.println("addr :"+addr);
					System.out.println("category :"+category);
					System.out.println("id :"+dto2.getM_id());
					
					CompanyDto dto = new CompanyDto(name,phone,addr,category,loc,dto2.getM_id());
					int res = companyBiz.modifycompany(dto);
					
					if(res>0) {
						jsResponse("변경 완료!!", "home", response);
					}else {
						jsResponse("변경 실패!!", "storemodifyform", response);
					}
				}
		// 마이페이지 내 게시글 확인 버튼 클릭시 myboard 페이지 이동
				else if(command.equals("myboard")) {
					HttpSession session = request.getSession(true);
					MemberDto dto = (MemberDto)session.getAttribute("login");
					List<JobBoardDto> list = boardBiz.myboard(dto.getM_id());
					String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
					int currentPage = Integer.parseInt(pageNum); // 현재페이지
					
					int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
					int endRow = currentPage * pageSize; //페이지 마지막 글
					int boardCnt = boardBiz.myboard(dto.getM_id()).size(); //전체 게시글 수
					int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
					
					int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
					/*
					 * [1][2][3][4][5] -> 1
					 * [6][7][8][9][10] -> 2
					 */
					
					int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
					int endPage = startPage + pageGroupSize-1 ; //끝페이지
					
					//만약 끝페이지가 전체페이지수보다 많으면!!!
					if (endPage > totalPageNum) {
						endPage = totalPageNum;				
					}
				
					request.setAttribute("startPage", startPage);
					request.setAttribute("endPage", endPage);
					request.setAttribute("currentPage", currentPage);
					request.setAttribute("totalPageNum", totalPageNum);
					request.setAttribute("list", list);
					dispatch("views/mypage/myboard.jsp",request,response);
				}

		
		//업체찾기 게시판 목록
		else if(command.equals("list")) {
			
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = boardBiz.getNumberOfFindComBoard(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			
			List<JobBoardDto> pagingList = boardBiz.selectFindComPageBoard(startRow, endRow);
			
			for( int i = 0 ; i < pagingList.size() ; i++) {
				pagingList.get(i).setCom_name(companyBiz.selectNameById(pagingList.get(i).getWriter()));
			}
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("list", pagingList);
			dispatch("views/jobboard/storeboard.jsp",request,response);
				
		}
		//알바 찾기 게시판 목록
		else if(command.equals("alba")) {
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = boardBiz.getNumberOfFindAlbaBoard(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			
			List<JobBoardDto> pagingList = boardBiz.selectFindAlbaPageBoard(startRow, endRow);
			List<String> writerList = new ArrayList<String>(); //라이트 담을변수
			
			for(int i = 0 ; i < pagingList.size() ; i ++) {
				writerList.add(pagingList.get(i).getWriter());
			}
			
			
			List<MemberDto> scorelist = memberBiz.selectPageScore(writerList);
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("scorelist", scorelist);
			request.setAttribute("list", pagingList);
			dispatch("views/jobboard/albaboard.jsp",request,response);
		}
		//마이페이지 프로필 확인 
		else if(command.equals("myprofile")) {
			
			HttpSession session = request.getSession(true);
			MemberDto dto = (MemberDto)session.getAttribute("login");
			
			List<ParttimerAbilityDto> albaAblitiy = memberBiz.profile(dto.getM_id());
			System.out.println(albaAblitiy);
			request.setAttribute("albaAblitiy", albaAblitiy);
			
			dispatch("views/mypage/myprofile.jsp",request,response);
			
		}
		//알바찾기 카테고리 검색
		else if(command.equals("albasearch")) {
			List<JobBoardDto> list = boardBiz.searchalbaboard(Integer.parseInt(request.getParameter("type2")),Integer.parseInt(request.getParameter("type1")));
			
			List<MemberDto> scorelist = memberBiz.score();
			List<JobBoardDto> list2 = new ArrayList<JobBoardDto>();
			List<JobBoardDto> term = boardBiz.termalbaboard();
			List<JobBoardDto> list3 = new ArrayList<JobBoardDto>();
			
		
			
		
			if(Integer.parseInt(request.getParameter("type3"))==0) {
				for(int i=0;i<term.size();i++) {
					if(term.get(i).getLoc()==0) {
					
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto1 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<4) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<4) {
						
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto2 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<8) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<8) {
						
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto3 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<15) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<15) {
						
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto4 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<22) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<22) {
						
					
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto5 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<31) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<31) {
						
					
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto6 :"+dto);
						list2.add(dto);
						
					}
				}
			}
			
		
			for(int i=0;i<list2.size();i++) {
				for(int j=0;j<list.size();j++) {
					if(list.get(j).getNo()==list2.get(i).getNo()) {
						JobBoardDto dto = boardBiz.boardno(list.get(j).getNo());
						System.out.println(dto);
						list3.add(dto);
					}
				}
			}
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = list3.size(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
				
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("list3", list3);
		
			request.setAttribute("scorelist", scorelist);
			
			
			
			
			
			
			dispatch("views/jobboard/albaboardsearch.jsp",request,response);		
		}
		// 업체찾기 카테고리 검색
		else if(command.equals("storesearch")) {
							
			
			List<JobBoardDto> list = boardBiz.selectallcategory(Integer.parseInt(request.getParameter("type2")),Integer.parseInt(request.getParameter("type1")) );
			List<JobBoardDto> list2 = new ArrayList<JobBoardDto>(); 
			List<JobBoardDto> term = boardBiz.termstoreboard();  
			List<JobBoardDto> list3 = new ArrayList<JobBoardDto>();
			
			
			
			if(Integer.parseInt(request.getParameter("type3"))==0) {
				for(int i=0;i<term.size();i++) {
					if(term.get(i).getLoc()==0) {
					
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto1 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<4) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<4) {
						
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto2 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<8) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<8) {
						
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto3 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<15) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<15) {
						
						
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto4 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<22) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<22) {
						
					
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto5 :"+dto);
						list2.add(dto);
						
					}
				}
			}else if(Integer.parseInt(request.getParameter("type3"))<31) {
				for(int i=0; i<term.size();i++) {
					if(term.get(i).getLoc()<31) {
						
					
						JobBoardDto dto = boardBiz.boardno(term.get(i).getNo());
						System.out.println("dto6 :"+dto);
						list2.add(dto);
						
					}
				}
			}
			
		
			for(int i=0;i<list2.size();i++) {
				for(int j=0;j<list.size();j++) {
					if(list.get(j).getNo()==list2.get(i).getNo()) {
						JobBoardDto dto = boardBiz.boardno(list.get(j).getNo());
						System.out.println(dto);
						list3.add(dto);
					}
				}
			}
			
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = list3.size(); //전체 게시글 수
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
		
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			List<JobBoardDto> pagingList = boardBiz.selectFindComPageBoard(startRow,endRow);
			for(int i=0;i<pagingList.size();i++) {
				pagingList.get(i).setCom_name(companyBiz.selectNameById(pagingList.get(i).getWriter()));
			}
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("list3", list3);
			
		
			dispatch("views/jobboard/storeboardsearch.jsp",request,response);
							
		}
		//회원 가입
		else if ( command.equals("join")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String rno = request.getParameter("rno");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");
			String gender = request.getParameter("gender");
			int role = Integer.parseInt(request.getParameter("m_role")); 
			String email = request.getParameter("email");
					
			MemberDto dto = new MemberDto( 0, id, pw, name, rno, phone, addr, email, gender, role, 0, "Y");
					
			int res = memberBiz.signup(dto);
					
			if( res > 0 ) {//성공
				jsResponse("회원 가입 성공!!", "home", response);
			}else {//실패
				jsResponse("회원 가입 실패!!", "joinform", response);
			}
					
		}
		//아이디찾기
		else if( command.equals("findidform")) {
			dispatch("views/findid/findidform.jsp",request,response);
		}
		//아이디 찾기 결과
		else if (command.equals("findid")) {
			String name = request.getParameter("name");
			String rno = request.getParameter("rno");
			
			String findId = memberBiz.findId(name, rno);
			
			request.setAttribute("name", name);
			request.setAttribute("findid", findId);
			dispatch("views/findid/findidres.jsp",request,response);
		}
		//비밀번호 찾기 
		else if ( command.equals("findpwform")) {
			String id = request.getParameter("id");
			
			request.setAttribute("id", id);
			dispatch("views/findpw/findpwform.jsp",request,response);
		}
		//비밀번호 찾기(이메일 인증으로)
		else if ( command.equals("findpw")) {
			String id = request.getParameter("id");
			
			String email = memberBiz.findEmailById(id);
			
			request.setAttribute("findid", id);
			request.setAttribute("findemail", email);
			dispatch("views/findpw/findpwemail.jsp",request,response);
		}
		//비밀번호 찾기 결과
		else if ( command.equals("findpwres")) {
			String id = request.getParameter("id");
			
			
			String pw = memberBiz.findPw(id);
			
			request.setAttribute("findid", id);
			request.setAttribute("findpw", pw);
			dispatch("views/findpw/findpwres.jsp",request,response);
		}
		//지역별 구인/구직(홈화면)
		else if( command.equals("findboardbyloc")) {
			int category = Integer.parseInt((request.getParameter("category") ==null || request.getParameter("category").trim() =="") ? "1" : request.getParameter("category"));
			int locno = Integer.parseInt(request.getParameter("locno"));
			List<LocationDto> locList = boardBiz.selectAllLoc();
			List<CategoryDto> categoryList = boardBiz.selectAllCategory();
			
			if(category ==1) {
				List<JobBoardDto> list = boardBiz.alllocboard2(locno);
				String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
				int currentPage = Integer.parseInt(pageNum); // 현재페이지
				
				int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
				int endRow = currentPage * pageSize; //페이지 마지막 글
				int boardCnt = list.size(); //전체 게시글 수
				int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
				
				int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
				/*
				 * [1][2][3][4][5] -> 1
				 * [6][7][8][9][10] -> 2
				 */
				
				int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
				int endPage = startPage + pageGroupSize-1 ; //끝페이지
				
				//만약 끝페이지가 전체페이지수보다 많으면!!!
				if (endPage > totalPageNum) {
					endPage = totalPageNum;				
				}
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPageNum", totalPageNum);
				request.setAttribute("list", list);
			}else {
				List<JobBoardDto> list = boardBiz.alllocboard(locno);
				String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
				int currentPage = Integer.parseInt(pageNum); // 현재페이지
				
				int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
				int endRow = currentPage * pageSize; //페이지 마지막 글
				int boardCnt = list.size(); //전체 게시글 수
				int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
				
				int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
				/*
				 * [1][2][3][4][5] -> 1
				 * [6][7][8][9][10] -> 2
				 */
				
				int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
				int endPage = startPage + pageGroupSize-1 ; //끝페이지
				
				//만약 끝페이지가 전체페이지수보다 많으면!!!
				if (endPage > totalPageNum) {
					endPage = totalPageNum;				
				}
				request.setAttribute("startPage", startPage);
				request.setAttribute("endPage", endPage);
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("totalPageNum", totalPageNum);
				request.setAttribute("list", list);
			}
			request.setAttribute("locno", locno);
			request.setAttribute("categorylist", categoryList);
			request.setAttribute("loclist", locList);
		    request.setAttribute("category", category);
		 
		    System.out.println(request.getParameter("ca"));
		    dispatch("views/home/locsearch.jsp",request,response);
		}
		//업종별 구인/구직(홈화면)
		else if(command.equals("findboardbycategory")) {
			int categoryno = Integer.parseInt(request.getParameter("categoryno"));
			int category = Integer.parseInt((request.getParameter("category") ==null || request.getParameter("category").trim() =="") ? "1" : request.getParameter("category"));
			List<JobBoardDto> searchList = null;
			int boardCnt = 0;
			
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			
			if(category == 1 ) {//업체찾기
				boardCnt = boardBiz.getNumberOfSearchComBoardByCategory(categoryno); //전체 게시글 수
			}else { //대타찾기
				boardCnt = boardBiz.getNumberOfSearchAlbaBoardByCategory(categoryno); //전체 게시글 수
			}
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			
			
			
			List<CategoryDto> categoryList = boardBiz.selectAllCategory();
			
			if(category == 1) {//업체찾기
				searchList = boardBiz.searchComBoardByCategory(categoryno, startRow, endRow);
				for(int i = 0 ; i < searchList.size() ; i++) {
					searchList.get(i).setCom_name(companyBiz.selectNameById( searchList.get(i).getWriter()));
				}
			}else {//대타찾기
				searchList = boardBiz.searchAlbaBoardByCategory(categoryno, startRow, endRow);
			}
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("searchlist", searchList);
			request.setAttribute("allcategory", categoryList);
			request.setAttribute("categoryno", categoryno);
			request.setAttribute("category", category);
			dispatch("views/home/findboardbycategory.jsp",request,response);
		}
		//게시글 디테일
		else if ( command.equals("boarddetail")) {
			int no = Integer.parseInt(request.getParameter("no"));
			
			//조회수 증가
			boardBiz.plusBoardCnt(no);
			
			
			JobBoardDto board = boardBiz.selectOneByNo(no);
			ContractDto contract = contractBiz.isSignContract(no); //계약이진행되고있는가? 진행이안되있으면 null
			
			//구인 게시글
			if(board.getType_no() == 2) {
				String writer = boardBiz.selectWriterByNo(no);
				CompanyDto company = companyBiz.selectOneById(writer);
				MemberDto member = memberBiz.selectOneById(writer);
				int applyCnt = contractBiz.selectApplyCnt(no);
				
				
				List<String> albaGender = memberBiz.selectAlbaGender(no);
				int maleCnt;
				int femaleCnt;
				if(albaGender == null) {
					maleCnt = 0;
					femaleCnt = 0;
				}
				else {
					maleCnt = 0;
					femaleCnt = 0;
					for(int i = 0 ; i < albaGender.size(); i++) {
						if(albaGender.get(i).equals("M")) {
							maleCnt++;
						}else {
							femaleCnt++;
						}
					}
				}
				
				request.setAttribute("malecnt", maleCnt);
				request.setAttribute("femalecnt", femaleCnt);
				request.setAttribute("applycnt", applyCnt);
				request.setAttribute("member", member);
				request.setAttribute("board", board);
				request.setAttribute("company", company);
			}
			//구직 게시글
			else if(board.getType_no() ==1) {
				String writer = boardBiz.selectWriterByNo(no);
				MemberDto member = memberBiz.selectOneById(writer);
				List<ParttimerAbilityDto> albaAblitiy = memberBiz.selectAbilityById(writer);
				int applyCnt = contractBiz.selectApplyCnt(no);
				
				request.setAttribute("applycnt", applyCnt);
				request.setAttribute("member", member);
				request.setAttribute("board", board);
				request.setAttribute("albaabilitiy", albaAblitiy);
			}
			if( contract != null) {
				request.setAttribute("issigncontract", contract);
				
			}
			dispatch("views/jobboard/boarddetail.jsp",request,response);
		}
		//구직글 신청
		else if ( command.equals("applyalba")) {
			int no = Integer.parseInt(request.getParameter("no"));
			String writer = request.getParameter("writer");
			HttpSession session = request.getSession(true);
			MemberDto dto = (MemberDto)session.getAttribute("login");
			System.out.println("컨트롤러 구직글:"+"no: "+no+" writer: "+writer+" 알바id: " +dto.getM_id());
			
			ContractDto contractDto = new ContractDto();
			contractDto.setBoard_no(no);
			contractDto.setParttimer_id(dto.getM_id());
			contractDto.setCompany_id(writer);
			int res = contractBiz.albaApplyContract(contractDto);
			
			if( res == -2) {
				jsResponse("이미 신청된 계약입니다.", "boarddetail&no="+no, response);
			}else if(res ==1) {
				jsResponse("지원을 성공했습니다.", "boarddetail&no="+no, response);
			}else {
				jsResponse("지원을 실패했습니다.", "boarddetail&no="+no, response);
			}
		}
		//구인글 신청
		else if( command.equals("applycompany")) {
			int no = Integer.parseInt(request.getParameter("no"));
			String writer = request.getParameter("writer");
			HttpSession session = request.getSession(true);
			MemberDto dto = (MemberDto)session.getAttribute("login");
			
			ContractDto contractDto = new ContractDto();
			contractDto.setBoard_no(no);
			contractDto.setParttimer_id(writer);
			contractDto.setCompany_id(dto.getM_id());
			int res = contractBiz.albaApplyContract(contractDto);
			
			if( res == -2) {
				jsResponse("이미 신청된 계약입니다.", "boarddetail&no="+no, response);
			}else if(res ==1) {
				jsResponse("지원을 성공했습니다.", "boarddetail&no="+no, response);
			}else {
				jsResponse("지원을 실패했습니다.", "boarddetail&no="+no, response);
			}
		}
		//헤더 검색창
		else if ( command.equals("headersearch")) {
			
			int category = Integer.parseInt((request.getParameter("category") ==null || request.getParameter("category").trim() =="") ? "1" : request.getParameter("category"));
			List<JobBoardDto> searchList = null;
			String searchWord = request.getParameter("search_word");
			String pageNum = (request.getParameter("pagenum") ==null || request.getParameter("pagenum").trim() =="") ? "1" : request.getParameter("pagenum");
			
			int currentPage = Integer.parseInt(pageNum); // 현재페이지
			
			int startRow = (currentPage -1) * pageSize +1; //페이지 첫번째글
			int endRow = currentPage * pageSize; //페이지 마지막 글
			int boardCnt = 0;
			
			if(category == 1) { //업체찾기
				boardCnt = boardBiz.getNumberOfFindComBoardByWord(searchWord); //전체 게시글 수
			}
			else {//알바찾기
				boardCnt = boardBiz.getNumberOfFindAlbaBoardByWord(searchWord); //전체 게시글 수
			}
			
			
			int totalPageNum = boardCnt / pageSize + 1; //총페이지 개수
			
			int numPageGroup = (int)Math.ceil((double)currentPage/pageGroupSize ); //페이지 그룹
			/*
			 * [1][2][3][4][5] -> 1
			 * [6][7][8][9][10] -> 2
			 */
			
			int startPage = (numPageGroup -1)*pageGroupSize + 1; //시작페이지
			int endPage = startPage + pageGroupSize-1 ; //끝페이지
			
			//만약 끝페이지가 전체페이지수보다 많으면!!!
			if (endPage > totalPageNum) {
				endPage = totalPageNum;				
			}
			
			
			if(category == 1 ) {//업체찾기
				searchList = boardBiz.selectFindComBoardByWord(startRow, endRow, searchWord);
			}
			else {//알바찾기
				 searchList = boardBiz.selectFindAlbaBoardByWord(startRow, endRow, searchWord);
			}


			
			
			for(int i = 0 ; i < searchList.size() ; i++) {
				String id = searchList.get(i).getWriter();
				String com_name = companyBiz.selectNameById(id);
				searchList.get(i).setCom_name(com_name);
				
			}
			
			if(searchList.size() == 0) {//비어있으면 널
				searchList = null;
			}
			
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("totalPageNum", totalPageNum);
			request.setAttribute("pagenum", pageNum);
			request.setAttribute("searchlist", searchList);
			request.setAttribute("category", category);
			request.setAttribute("searchword", searchWord);
			dispatch("views/search/headersearch.jsp",request,response);
		}
		//글 수정폼
		else if ( command.equals("updateboardform")) {
			int no = Integer.parseInt(request.getParameter("no"));

			
			JobBoardDto board = boardBiz.selectOneByNo(no);
			
			//구인 게시글
			if(board.getType_no() == 2) {
				String writer = boardBiz.selectWriterByNo(no);
				CompanyDto company = companyBiz.selectOneById(writer);
				MemberDto member = memberBiz.selectOneById(writer);
				
				
				request.setAttribute("member", member);
				request.setAttribute("board", board);
				request.setAttribute("company", company);
			}
			//구직 게시글
			else if(board.getType_no() ==1) {
				String writer = boardBiz.selectWriterByNo(no);
				MemberDto member = memberBiz.selectOneById(writer);
				
				request.setAttribute("member", member);
				request.setAttribute("board", board);
				
			}
			
			List<LocationDto> loc = boardBiz.selectAllLoc();
			List<CategoryDto> category = boardBiz.selectAllCategory();
		
			request.setAttribute("loc", loc);
			request.setAttribute("category", category);
			dispatch("views/jobboard/boardupdateform.jsp",request,response);
			
		}
		//글 수정
		else if ( command.contains("updateboard")) {
			int no = Integer.parseInt(request.getParameter("no"));
			String startDate = request.getParameter("startdate");
			String endDate = request.getParameter("enddate");
			String startTime = request.getParameter("starttime");
			String endTime = request.getParameter("endtime");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int money =  Integer.parseInt(request.getParameter("money"));
			int loc = Integer.parseInt(request.getParameter("loc"));
			int category = Integer.parseInt(request.getParameter("category"));
			
			JobBoardDto dto = new JobBoardDto();
			dto.setNo(no);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setMoney(money);
			dto.setLoc(loc);
			dto.setCategory(category);
			
			int res = boardBiz.updateBoard(dto, startDate,endDate,startTime,endTime);
			
			if(res > 0) {
				jsResponse("글 수정 성공!", "boarddetail&no="+no, response);
			}else {
				jsResponse("글 수정 실패!", "updateboardform&no="+no, response);
			}
			
		}
		//글 삭제
		else if ( command.contentEquals("deleteboard")) {
			int no = Integer.parseInt(request.getParameter("no"));
			String afterPage = request.getParameter("afterpage");
			
			int res = boardBiz.deleteBoard(no);
			
			if( res > 0) {
				jsResponse("글 삭제 성공!", afterPage, response);
			}else {
				jsResponse("거절성공!", afterPage, response);
			}
			
		}
		
		
		//알바 게시글 쓰기
		else if(command.equals("upload-alba")) {	
			HttpSession session = request.getSession(true);
			//로그인되어있는 정보로 member가져오기
			MemberDto dto = (MemberDto)session.getAttribute("login");
			System.out.println("dtoId:"+dto);
			//로그인 정보 없을시
			if(dto == null) {
			jsResponse("로그인 해주세요!", "loginform", response);
			}
			//료그인 정보가 있을시 
			else {
				//현재 로그인 아이디로 업체인지 알바인지 구분
				int grade = dto.getM_role();
				System.out.println("grade"+grade);
					if(grade == 2) {//알바생
						jsResponse("알바생이 아닙니다.", "home", response);

					}
					else {
						//int loginNo = dto.getM_no();
						//System.out.println("알바생 mno"+loginNo);
						request.setAttribute("m", dto);
						System.out.println("사용자 아이디"+dto.getM_id());
						//유저아이디로 어빌리티 조회 
						HashMap<String, Object> ability =boardBiz.selectAbility(dto.getM_id());
						request.setAttribute("ability", ability);
						
						ParttimerAbilityDto jobA = (ParttimerAbilityDto)ability.get("1"); //카페
						ParttimerAbilityDto jobB = (ParttimerAbilityDto)ability.get("2"); //편의점
						ParttimerAbilityDto jobC = (ParttimerAbilityDto)ability.get("3"); //음식점
						ParttimerAbilityDto jobD = (ParttimerAbilityDto)ability.get("4"); //일용직
						ParttimerAbilityDto jobE = (ParttimerAbilityDto)ability.get("5"); //배달
						ParttimerAbilityDto jobF = (ParttimerAbilityDto)ability.get("6"); //유흥
						ParttimerAbilityDto jobG = (ParttimerAbilityDto)ability.get("7"); //기타
						request.setAttribute("jobA", jobA.getA_cnt());
						request.setAttribute("jobB", jobB.getA_cnt());
						request.setAttribute("jobC", jobC.getA_cnt());
						request.setAttribute("jobD", jobD.getA_cnt());
						request.setAttribute("jobE", jobE.getA_cnt());
						request.setAttribute("jobF", jobF.getA_cnt());
						request.setAttribute("jobG", jobG.getA_cnt());

						//유저의 성별을 남자, 여자로 변경 
						String gender = (dto.getM_gender().equals("M")?"남자":"여자");
						System.out.println("성별은?"+gender);
						request.setAttribute("gender", gender);
						dispatch("views/upload/upload-alba.jsp", request, response);	
					}
				}		
		}		
		//업체 게시글 작성폼으로 
		else if(command.equals("upload-store")) {	
			HttpSession session = request.getSession(true);
			//로그인되어있는 정보로 member가져오기
			MemberDto dto = (MemberDto)session.getAttribute("login");
			//System.out.println("dtoId:"+dto.toString());
			//로그인 정보 없을 시
			if(dto == null) {
				jsResponse("로그인 해주세요!", "loginform", response);
			}
			//로그인 정보가 있을 시
			else {
				//현재 로그인 아이디로 업체인지 알바인지 구분
				int grade = dto.getM_role();
				System.out.println("grade"+grade);
					if(grade == 1) {//알바생
						jsResponse("업체가 아닙니다.", "home", response);

					}
					else {
						String loginId = dto.getM_id();
						request.setAttribute("m", dto);
						CompanyDto comdto = contractBiz.selectCompany(loginId); 
						System.out.println("회사 정보"+comdto.toString());
						if(comdto.getM_id() == null || comdto.getCom_name() == null || comdto.getCom_name() == null) {
							jsResponse("업체정보를 입력해주세요.", "mypage", response);

						}else {
							request.setAttribute("comdto", comdto);		
							dispatch("views/upload/upload-store.jsp", request, response);	
						}
					}
				}		
			}	
			
		//게시글 작성완료
		else if(command.equals("insertJobboard")) {
			System.out.println("넘어오나 ?");
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int money = Integer.parseInt(request.getParameter("money"));
			String start_date = request.getParameter("start-date");
			String start_time = request.getParameter("start-time");
			String end_date = request.getParameter("end-date");
			String end_time = request.getParameter("end-time");
			int category = Integer.parseInt(request.getParameter("job-name"));
			int loc = Integer.parseInt(request.getParameter("location"));
			//회원구분
			int m_role = Integer.parseInt(request.getParameter("m_role"));

			System.out.println("여기까지넘어오나?");
			System.out.println("작성자"+writer);
			System.out.println("시작날짜"+start_date+"시작시간"+start_time+"끝나는 날짜"+end_date+"끝나는시간"+end_time);
			//(writer, title, content, money, start_date, start_time, end_date, end_time, category, loc);
			JobBoardDto comJob = new JobBoardDto(writer, title, content,  money, loc, category);
			
			System.out.println("글작성 데이터 ?"+comJob);
			int res = boardBiz.insertCompanyBoard(comJob, start_date, end_date, start_time, end_time, m_role);
			if( res > 0 ) {//성공
				if(m_role>1){ //업체면 업체리스트
					jsResponse("글 작성 성공!!", "list", response);
				}// 알바일경우 알바리스트
				else {
					jsResponse("글 작성 성공!!", "alba", response);
				}			
			}else {//실패
				if(m_role>1){ //업체면 업체리스트
					jsResponse("글 작성 실패!!", "list", response);
				}// 알바일경우 알바리스트
				else {
					jsResponse("글 작성 실패!!", "alba", response);
				}
			}
		}			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	//forward함수
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}
	
	//alert 띄운후 url이동 함수 msg command
	private void jsResponse(String msg, String command, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
                + "alert('"+msg+"');"
                + "location.href='controller.do?command="+command+"';"
                + "</script>";
		PrintWriter out = response.getWriter();
		out.print(s);
	}

}
