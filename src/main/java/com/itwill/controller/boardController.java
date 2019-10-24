package com.itwill.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.annotation.Resource;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.logging.log4j2.Log4j2AbstractLoggerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.itwill.domain.BoardBean;
import com.itwill.domain.BoardCommentBean;
import com.itwill.domain.FileSettingBean;
import com.itwill.domain.MemberBean;
import com.itwill.service.AirlineService;
import com.itwill.service.BoardService;
import com.itwill.service.MemberService;
import com.itwill.service.UtilService;
import com.sun.media.jfxmedia.logging.Logger;
import com.itwill.domain.PageBean;

@Controller
public class boardController {

	@Inject
	private BoardService service;
	@Inject
	private UtilService utilService;
	@Inject
	private MemberService memberService;
	@Inject
	private AirlineService airlineService;
	
	@Resource(name = "uploadPath")
	private String uploadPath;

	
	

	
	// 여기서부터 투어 템플릿 페이지 이동 ~
	

	// http://localhost:8080/myapp/tourindex
	@RequestMapping(value = "/tourindex", method = RequestMethod.GET)
	public String tourindex(HttpSession session, Model model) {
		System.out.println("/boardController GET tourindex() ");

		// MAP 으로 파라미터를 만들어서 json 으로 변환해서 넘기기
		// 국가 가져오기
		// selectBox
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "CO");
		map.put("control_id", "selCountry_dep");
		map.put("type", "code");
		map.put("class", "form-control3 form-control");
		map.put("selectItem", ""); // 로드시 selset 할 데이터
		model.addAttribute("selCountry_dep", utilService.getCodeData(map));
		

		// 도시 가져오기
		map.clear();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "CT");
		map.put("control_id", "selCity_dep");
		map.put("type", "code");
		map.put("class", "form-control");
		model.addAttribute("selCity_dep", utilService.getCodeData(map));

		// 국가 가져오기
		// selectBox
		map.clear();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "CO");
		map.put("control_id", "selCountry_arr");
		map.put("type", "code");
		map.put("class", "form-control3 form-control");
		map.put("selectItem", ""); // 로드시 selset 할 데이터
		model.addAttribute("selCountry_arr", utilService.getCodeData(map));

		// 도시 가져오기
		map.clear();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "CT");
		map.put("control_id", "selCity_arr");
		map.put("type", "code");
		map.put("class", "form-control");
		model.addAttribute("selCity_arr", utilService.getCodeData(map));
		
		
		// 항공권 검색 밑 각 게시판으로 연결하기
		
		// 후기 - 8개
		map.clear();
		map.put("board_id", "review");
		map.put("count", 8);
		List<BoardBean> reviewList = service.getMainList(map);
		
		// 본문에서 썸네일 용 사진 추출
		for(BoardBean bean : reviewList) {
			String content = bean.getContent();
			String target = "<img src=";
			int target_num = content.indexOf(target);
			if(target_num == -1) { // 사진파일이 없으면
				bean.setFile_use("N");
			} else { 
				bean.setFile_use("Y");
				String img = content.substring(target_num,(content.substring(target_num).indexOf("style=\"width:")+target_num)) + "style=\"width: 100%; height:100%;\">";
				bean.setThumb(img);
			}
		}
		
		model.addAttribute("reviewList", reviewList);
		
		// 동행 - 3개
		map.clear();
		map.put("board_id", "together");
		map.put("count", 3);
		List<BoardBean> togetherList = service.getMainList(map);
		
		// 게시글 regi_id 에서 프로필 사진 가져오기
		for(BoardBean bean : togetherList) {
			MemberBean mb = memberService.getMemberInfoById(bean.getRegi_id());
			bean.setProfile(mb.getProfile_photo());
			bean.setGender(mb.getGender());
		}
		
		model.addAttribute("togetherList", togetherList);
		
		// 꿀팁 - 3개
		map.clear();
		map.put("board_id", "honey");
		map.put("count", 3);
		List<BoardBean> honeyList = service.getMainList(map);
		
		for(BoardBean bean : honeyList) {
			String content = bean.getContent();
			String target = "<img src=";
			int target_num = content.indexOf(target);
			if(target_num == -1) { // 사진파일이 없으면
				bean.setFile_use("N");
			} else { 
				bean.setFile_use("Y");
				String img = content.substring(target_num,(content.substring(target_num).indexOf("style=\"width:")+target_num)) + "style=\"width: 100%; height:100%;\">";
				bean.setThumb(img);
			}
		}
		String img = "<img src=\"data:image/png;base64,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\" data-filename=\"honey.png\" style=\"width: 50%; height:50%;\">";
		model.addAttribute("honeyimg", img);
		model.addAttribute("honeyList", honeyList);
		
		// 갤러리 - 8개
		map.clear();
		map.put("board_id", "gallery");
		map.put("count", 8);
//		List<BoardBean> galleryList = service.getMainList(map);
//		for(BoardBean bean : galleryList) {
//			FileSettingBean fileBean = (FileSettingBean) service.getFileBySeq(bean.getSeq());
//			bean.setFile_name(fileBean.getFile_name());
//			bean.setPath1(fileBean.getPath() + "/" + fileBean.getFile_name());
//		}
//		model.addAttribute("galleryList", galleryList);

		List<FileSettingBean> file = null;
		List<BoardBean> board = null;

		file = service.getFile();
		board = service.getBoardFileByOrder("gallery");

		model.addAttribute("file", file);
		model.addAttribute("galleryList", board);
		
		return "team_project/tour/index";
	}

	// http://localhost:8080/myapp/tourcontact
	@RequestMapping(value = "/tourcontact", method = RequestMethod.GET)
	public String tourcontact(HttpSession session) {
		System.out.println("/boardController GET tourcontact() ");

		return "team_project/tour/contact";
	}

	// http://localhost:8080/myapp/tour/delete
	@RequestMapping(value = "/tour/delete", method = RequestMethod.GET)
	public String tourDelete(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("/boardController POST delete() ");

		int seq = Integer.parseInt(request.getParameter("seq"));
		String etc1 = request.getParameter("etc1");

		System.out.println("delete 할때: " + seq + ", board_id=" + etc1);

		int result = service.delete(seq);

		if (result == 1) {
			model.addAttribute("alert", "정상적으로 삭제되었습니다.");
			model.addAttribute("etc1", etc1);

			return "team_project/tour/board/honey/alert";
		} else {
			model.addAttribute("alert", "삭제 실패!");
			model.addAttribute("etc1", etc1);

			return "team_project/tour/board/honey/alert";
		}
	}

// 자유 게시판		
	// 자유게시판 눌렀을때
	// freeboardWrite.jsp에서 글을 쓴 후 이동
	// freeboardContent.jsp에서 목록으로 눌렀을때 이동
	@RequestMapping(value = "/freeboard", method = RequestMethod.GET)
	public String freeboard(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET freeboard() ");
		PageBean pageBean = new PageBean();

		// 검색 기능 세팅
		// searchSelect 는 subject 또는 regi_id 이다 (각각 제목검색, 작성자검색)
		String searchSelect = (String) request.getParameter("searchSelect");
		// 검색할 스트링
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pageBean.setSearchSelect(searchSelect);
		pageBean.setSearch(search);

		// 페이징 세팅
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		pageBean.setPageSize(5);
		pageBean.setPageNum(pageNum);

		// 서비스에서 freeboard List 가져오기
//			List<BoardBean> boardList = service.getBoardList();

		// 페이징 처리하여 자유게시판의 게시물들 가져오기
		pageBean.setBoard_id("free");
		List<BoardBean> boardList = service.getBoardList(pageBean);

		// 모든 게시물의 날짜 형식 yyyy-MM-dd 로 변환
		for (BoardBean bb : boardList) {
			int yyyy = bb.getRegi_date().getYear() + 1900;
			int MM = bb.getRegi_date().getMonth() + 1;
			int dd = bb.getRegi_date().getDate();

			String date = yyyy + " - " + MM + " - " + dd;
//				System.out.println(date);
			bb.setEtc3(date);
			
			// 닉네임 저장
			bb.setNickName(memberService.getMemberInfoById(bb.getRegi_id()).getNickname());
			
		}

		// 자유게시판의 것 갯수 세기
		// 추가 - 검색시 검색된 것만 갯수 세기
		pageBean.setCount(service.getBoardCount(pageBean));

		// 모델에 저장
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("searchSelect", searchSelect);
		model.addAttribute("search", search);

		return "team_project/tour/board/freeboard";
	}

	// freeboard.jsp 에서 write를 눌렀을때
	// http://localhost:8080/myapp/freeboardWrite
	@RequestMapping(value = "/freeboardWrite", method = RequestMethod.GET)
	public String freeboardWrite(BoardBean bean,HttpSession session, Model model) {
		System.out.println("/boardController GET freeboardWrite()");
		// 세션값 없으면 뒤로 돌려보냄~
		String regi_id = (String) session.getAttribute("id");

		if (regi_id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "team_project/tour/member/msg";
		}
		

		return "team_project/tour/board/freeboardWriteForm";
	}

	// http://localhost:8080/myapp/freeboard/delete
	@RequestMapping(value = "/freeboard/delete", method = RequestMethod.GET)
	public String freeboardDelete(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("/boardController POST delete() ");
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
		System.out.println("delete 할때: " + seq );
		
		int result = service.delete(seq);
		
		return "redirect:/freeboard";
		
	}
	
	@RequestMapping(value = "/freeboardUpdate", method = RequestMethod.GET)
	public String freeboardUpdate(BoardBean bb, Model model, HttpServletRequest request,HttpSession session) {
		System.out.println("/boardController GET freeboardUpdate() ");
		
		// 세션값 없으면 뒤로 돌려보냄~
		String regi_id = (String) session.getAttribute("id");

		if (regi_id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "redirect:/freeboard";
		}

		System.out.println("BoardController /board/freeboardUpdate get update()");

		int seq = Integer.parseInt(request.getParameter("seq"));

		BoardBean bbb = service.getArticle(seq);

		String pageNum = request.getParameter("pageNum");

		model.addAttribute("bb1", bbb);
		model.addAttribute("pageNum", pageNum);

		return "team_project/tour/board/freeboardUpdateForm";
	}
	
	// http://localhost:8080/myapp/freeboardUpdate
			@RequestMapping(value = "/freeboardUpdate", method = RequestMethod.POST)
			public String freeboardUpdatePost(BoardBean bb, Model model, HttpServletRequest request) {
				System.out.println("/boardController GET freeboardUpdatePost() ");
				
				int result = service.contentUpdate(bb);
				System.out.println("게시물 수정: " + result);
				if(result > 0) {
					model.addAttribute("msg","게시물이 수정되었습니다.");
					model.addAttribute("url","freeboardcontent?seq="+bb.getSeq());
					
				} else {
					System.out.println("게시물 수정 실패~~~~");
					model.addAttribute("msg","게시물 수정에 실패하였습니다.");
					return "team_project/tour/member/msg";
				}

				return "redirect:/freeboard";
			}
	
	// freeboardWrite.jsp에서 submit 눌렀을때
	@RequestMapping(value = "/board/freeboardWrite", method = RequestMethod.POST)
	public String freeboardWritePost(HttpSession session, HttpServletRequest request, BoardBean bean) {
		System.out.println("BoardController POST freeboardWritePost()");

		
		
		// 모든 게시판 공통 세팅
		// 작성자 아이디
		String regi_id = (String) session.getAttribute("id");
		bean.setRegi_id(regi_id);

		// 작성자 ip
		bean.setRegi_ip_addr(utilService.getIp(request));

		// 조회수 0으로 초기화
		bean.setReadcount(0);
		// 날짜 MM/dd/yyyy 형
//			String BoardDate = new SimpleDateFormat("yyyy-MM-dd").format(bean.getRegi_date());
//			bean.setEtc3(BoardDate);

		// 자유게시판 세팅
		// 게시판 종류
		bean.setBoard_id("free");
		// 공지인가? - normal N/ notice Y 이 들어간다
		String isNotice = request.getParameter("notice_use");
		if(isNotice == null) {
			isNotice = "N";
		}
		bean.setNotice_use(isNotice);
		// 비밀글 - 공개 N / 비공개 Y
//			String isSecret = request.getParameter("secret");
		bean.setSecret_use("N");
		// 좋아요 싫어요 0 으로 초기화
		bean.setLike(0);
		bean.setHate(0);
		// 추천수 0으로 초기화
		bean.setRecommend(0);
		// regi_ip_addr, modifier_ip_addr 세팅
		bean.setRegi_ip_addr(utilService.getIp(request));
		bean.setModifier_ip_addr(utilService.getIp(request));

		// 다른 게시판 세팅들 - 다른 게시판 세팅은 모두 주석 처리 하겠음
		// 나라 설정
//			bean.setCountry(null);

//			service 에 bean insert 한다
		service.write(bean);

		return "redirect:/freeboard";
	}

	// freeboard.jsp 게시글을 눌렀을때
	// http://localhost:8080/myapp/freeboardContent
	@RequestMapping(value = "/freeboardContent", method = RequestMethod.GET)
	public String freeboardContent(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET freeboardContent()");

		// 번호 가져오기
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		// 조회수 증가
		service.updateReadcount(seq);

		// 번호에 맞는 게시물 가져오기
		BoardBean article = service.getArticle(seq);
		// 닉네임 가져오기
		article.setNickName(memberService.getMemberInfoById(article.getRegi_id()).getNickname());
		model.addAttribute("Article", article);

		// 게시물에 맞는 댓글 가져오기
		List<BoardCommentBean> commentList = service.getCommentList(seq);
		// 댓글의 regi_id 를 통해 memberBean 을 가져오기 (프로필 사진)
		for(BoardCommentBean bean : commentList) {
			MemberBean mb = memberService.getMemberInfoById(bean.getRegi_id());
			bean.setProfile(mb.getProfile_photo());
			bean.setGender(mb.getGender());
		}
		model.addAttribute("commentList", commentList);

		return "team_project/tour/board/freeboardcontent";
	}
	
	// freeboardContent에서 댓글 썻을때
	@RequestMapping(value = "/freeboardComment", method = RequestMethod.POST)
	public String freeboardComment(Model model, HttpSession session, HttpServletRequest request, BoardCommentBean commentBean) {
		System.out.println("/boardController POST freeboardComment()");

		// 작성자 아이디
		String regi_id = (String) session.getAttribute("id");
		commentBean.setRegi_id(regi_id);
		commentBean.setRegi_ip_addr(utilService.getIp(request));
		
		commentBean.setRe_ref(0);
		commentBean.setRe_lev(0);
		commentBean.setRe_seq(0);
		
		commentBean.setLikeCount(0);
		commentBean.setHateCount(0);
		
		// 댓글 저장
		service.insertComment(commentBean);
		int seq = commentBean.getBoard_seq();
		// 게시글 번호
//		model.addAttribute("seq", commentBean.getBoard_seq());
		
		return "redirect:/freeboardContent?seq="+seq;
	}

// 동행 게시판		
	// http://localhost:8080/myapp/together
	@RequestMapping(value = "/together", method = RequestMethod.GET)
	public String together(HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("/boardController GET together() ");
		PageBean pageBean = new PageBean();

		// 검색 기능 세팅
		// searchSelect 는 subject 또는 regi_id 이다 (각각 제목검색, 작성자검색)
		String searchSelect = (String) request.getParameter("searchSelect");
		// 검색할 스트링
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pageBean.setSearchSelect(searchSelect);
		pageBean.setSearch(search);

		// 페이징 세팅
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		pageBean.setPageSize(5);
		pageBean.setPageNum(pageNum);

		// 서비스에서 freeboard List 가져오기
//			List<BoardBean> boardList = service.getBoardList();

		// 페이징 처리하여 자유게시판의 게시물들 가져오기
		pageBean.setBoard_id("together");
		List<BoardBean> boardList = service.getBoardList(pageBean);

		// 모든 게시물의 날짜 형식 yyyy-MM-dd 로 변환
		for (BoardBean article : boardList) {
//			int yyyy = bb.getRegi_date().getYear() + 1900;
//			int MM = bb.getRegi_date().getMonth() + 1;
//			int dd = bb.getRegi_date().getDate();
//
//			String date = yyyy + " - " + MM + " - " + dd;
////				System.out.println(date);
//			bb.setEtc3(date);
			// 닉네임 가져오기
			article.setNickName(memberService.getMemberInfoById(article.getRegi_id()).getNickname());
		}

		// 자유게시판의 것 갯수 세기
		// 추가 - 검색시 검색된 것만 갯수 세기
		pageBean.setCount(service.getBoardCount(pageBean));

		// 모델에 저장
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("searchSelect", searchSelect);
		model.addAttribute("search", search);
		return "team_project/tour/board/together";
	}

	// together.jsp 에서 write를 눌렀을때
	// http://localhost:8080/myapp/togetherWrite
	@RequestMapping(value = "/togetherWrite", method = RequestMethod.GET)
	public String togetherWrite(BoardBean bean, Model model,HttpSession session) {
		System.out.println("/boardController GET togetherWrite()");
		// 세션값 없으면 뒤로 돌려보냄~
		String regi_id = (String) session.getAttribute("id");

		if (regi_id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "team_project/tour/member/msg";
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "CO");
		map.put("control_id", "selCountry_dep");
		map.put("type", "code");
		map.put("class", "form-control3 form-control");
		map.put("selectItem", ""); // 로드시 selset 할 데이터
		model.addAttribute("selCountry_dep", utilService.getCodeData(map));

		// 도시 가져오기
		map.clear();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "CT");
		map.put("control_id", "selCity_dep");
		map.put("type", "code");
		map.put("class", "form-control");
		model.addAttribute("selCity_dep", utilService.getCodeData(map));
		

		return "team_project/tour/board/togetherWriteForm";
	}
	
	@RequestMapping(value = "/togetherUpdate", method = RequestMethod.GET)
	   public String togetherUpdate(BoardBean bb, Model model, HttpServletRequest request,HttpSession session) {
	      System.out.println("/boardController GET togetherUpdate() ");
	      
	      // 세션값 없으면 뒤로 돌려보냄~
	      String regi_id = (String) session.getAttribute("id");

	      if (regi_id == null) {
	         model.addAttribute("msg", "잘못된 접근입니다.");
	         return "redirect:/together";
	         }
	            
	      Map<String, Object> map = new HashMap<String, Object>();
	      map.put("control_type", "select");
	      map.put("first_item_type", "select");
	      map.put("code_div", "CO");
	      map.put("control_id", "selCountry_dep");
	      map.put("type", "code");
	      map.put("class", "form-control3 form-control");
	      map.put("selectItem", "country"); // 로드시 selset 할 데이터
	      model.addAttribute("selCountry_dep", utilService.getCodeData(map));

	      // 도시 가져오기
	      map.clear();
	      map.put("control_type", "select");
	      map.put("first_item_type", "select");
	      map.put("code_div", "CT");
	      map.put("control_id", "selCity_dep");
	      map.put("type", "code");
	      map.put("class", "form-control");
	      map.put("selectItem", "city");   
	      model.addAttribute("selCity_dep", utilService.getCodeData(map));

	      System.out.println("BoardController /board/togetherUpdate get update()");

	      int seq = Integer.parseInt(request.getParameter("seq"));

	      BoardBean bbb = service.getArticle(seq);

	      String pageNum = request.getParameter("pageNum");

	      model.addAttribute("bb1", bbb);
	      model.addAttribute("pageNum", pageNum);

	      return "team_project/tour/board/togetherUpdateForm";
	   }
	   
	   // http://localhost:8080/myapp/togetherUpdate
	         @RequestMapping(value = "/togetherUpdate", method = RequestMethod.POST)
	         public String togetherUpdatePost(BoardBean bb, Model model, HttpServletRequest request) {
	            System.out.println("/boardController GET togetherUpdatePost() ");
	            
	            int result = service.contentUpdate(bb);
	            System.out.println("게시물 수정: " + result);
	            if(result > 0) {
	               model.addAttribute("msg","게시물이 수정되었습니다.");
	               model.addAttribute("url","togethercontent?seq="+bb.getSeq());
	               
	            } else {
	               System.out.println("게시물 수정 실패~~~~");
	               model.addAttribute("msg","게시물 수정에 실패하였습니다.");
	               return "team_project/tour/member/msg";
	            }

	            return "redirect:/together";
	         }
	
	// http://localhost:8080/myapp/together/delete
	@RequestMapping(value = "/together/delete", method = RequestMethod.GET)
	public String togetherDelete(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("/boardController POST delete() ");
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
		System.out.println("delete 할때: " + seq );
		
		int result = service.delete(seq);
		
		return "redirect:/together";
		
	}

	// togetherWrite.jsp에서 submit 눌렀을때
	@RequestMapping(value = "/togetherWrite", method = RequestMethod.POST)
	public String togetherWritePost(HttpSession session, HttpServletRequest request, BoardBean bean, Model model) {
		System.out.println("BoardController POST togetherWritePost()");

		// 모든 게시판 공통 세팅
		// 작성자 아이디
		String regi_id = (String) session.getAttribute("id");
		bean.setRegi_id(regi_id);

		// 작성자 ip
		bean.setRegi_ip_addr(utilService.getIp(request));

		// 조회수 0으로 초기화
		bean.setReadcount(0);
		// 날짜 MM/dd/yyyy 형
//		String BoardDate = new SimpleDateFormat("yyyy-MM-dd").format(bean.getRegi_date());
//		bean.setEtc3(BoardDate);

		// 동행게시판 세팅
		
		// 도시코드를 문자열로 바꿔온다
		String country = airlineService.getCTName((String)request.getParameter("selCountry_dep"));
		String city = airlineService.getCTName((String)request.getParameter("selCity_dep"));
		
		if(country == null) {
			model.addAttribute("msg", "여행지를 입력해 주세요");
			return "/team_project/tour/member/msg";
		}
		
		if(city == null) {
			bean.setCountry(country);
		} else {
			bean.setCountry(country + "/" + city);
		}
		
		// 게시판 종류
		bean.setBoard_id("together");
		// 공지인가? - normal N/ notice Y 이 들어간다
//		String isNotice = request.getParameter("notice");
		bean.setNotice_use("N");
		// 비밀글 - 공개 N / 비공개 Y
//		String isSecret = request.getParameter("secret");
		bean.setSecret_use("N");
		// 좋아요 싫어요 0 으로 초기화
		bean.setLike(0);
		bean.setHate(0);
		// 추천수 0으로 초기화
		bean.setRecommend(0);
		// regi_ip_addr, modifier_ip_addr 세팅
		bean.setRegi_ip_addr(utilService.getIp(request));
		bean.setModifier_ip_addr(utilService.getIp(request));

		// 다른 게시판 세팅들 - 다른 게시판 세팅은 모두 주석 처리 하겠음
		// 나라 설정
		if(bean.getCountry() == null) {
			bean.setCountry("");
		}

//		service 에 bean insert 한다
		service.write(bean);

		return "redirect:/together";
	}
	
	// together.jsp 게시글을 눌렀을때
	// http://localhost:8080/myapp/freeboardContent
	@RequestMapping(value = "/togetherContent", method = RequestMethod.GET)
	public String togetherContent(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET togetherContent()");

		// 번호 가져오기
		int seq = Integer.parseInt(request.getParameter("seq"));

		// 조회수 증가
		service.updateReadcount(seq);

		// 번호에 맞는 게시물 가져오기
		BoardBean article = service.getArticle(seq);
		model.addAttribute("Article", article);
		// 닉네임 가져오기
		article.setNickName(memberService.getMemberInfoById(article.getRegi_id()).getNickname());
	
		// 게시물에 맞는 댓글 가져오기
		List<BoardCommentBean> commentList = service.getCommentList(seq);
		// 댓글의 regi_id 를 통해 memberBean 을 가져오기 (프로필 사진)
		for(BoardCommentBean bean : commentList) {
			MemberBean mb = memberService.getMemberInfoById(bean.getRegi_id());
			bean.setProfile(mb.getProfile_photo());
			bean.setGender(mb.getGender());
		}
		model.addAttribute("commentList", commentList);

		return "team_project/tour/board/togethercontent";
	}
	
	// togetherContent에서 댓글 썻을때
	@RequestMapping(value = "/togetherComment", method = RequestMethod.POST)
	public String togetherComment(Model model, HttpSession session, HttpServletRequest request, BoardCommentBean commentBean) {
		System.out.println("/boardController POST freeboardComment()");

		// 작성자 아이디
		String regi_id = (String) session.getAttribute("id");
		commentBean.setRegi_id(regi_id);
		commentBean.setRegi_ip_addr(utilService.getIp(request));
		
		commentBean.setRe_ref(0);
		commentBean.setRe_lev(0);
		commentBean.setRe_seq(0);
		
		commentBean.setLikeCount(0);
		commentBean.setHateCount(0);
		
		// 댓글 저장
		service.insertComment(commentBean);
		int seq = commentBean.getBoard_seq();
		// 게시글 번호
//		model.addAttribute("seq", commentBean.getBoard_seq());
		
		return "redirect:/togetherContent?seq="+seq;
	}
	
// 여행후기		
	// http://localhost:8080/myapp/review
	@RequestMapping(value = "/tourreview", method = RequestMethod.GET)
	public String tourreview(HttpSession session,Model model, HttpServletRequest request) {
		System.out.println("/boardController GET tourreview() ");
		
		PageBean pageBean = new PageBean();

		// 검색 기능 세팅
		// searchoption 는 subject 또는  country, nickname
		String searchoption = request.getParameter("searchoption");
		// 검색할 스트링
		String search = "";

		if (searchoption == null) {
			// 검색이 없으면 제목 검색
			searchoption = "subject";
		} else if(searchoption.equals("country")) {
			// 후기게시판의 나라검색 
			// select로 나라 가져오기
			search = request.getParameter("country");
		} else {
			// 나머지 제목, 작성자, 내용등의 input box 검색 
			search = (String) request.getParameter("searchbox-review");
		}

		
		// 보여주기 기능
		// name = view / value = list, thumb
		String view = (String)request.getParameter("view");
		if(view == null) {
			view = "thumb";
		}
		model.addAttribute("view", view);
		
		pageBean.setSearchSelect(searchoption);
		pageBean.setSearch(search);

		// 페이징 세팅
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		pageBean.setPageSize(6);
		pageBean.setPageNum(pageNum);

		// 페이징 처리하여 자유게시판의 게시물들 가져오기
		pageBean.setBoard_id("review");
		List<BoardBean> boardList = service.getBoardList(pageBean);

		// 자유게시판의 것 갯수 세기
		// 추가 - 검색시 검색된 것만 갯수 세기
		pageBean.setCount(service.getBoardCount(pageBean));

		// 본문에서 썸네일 용 사진 추출
		for(BoardBean bean : boardList) {
		
			String content = bean.getContent();
			String target = "<img src=";
		
			int target_num = content.indexOf(target);
			
			if(target_num == -1) { // 사진파일이 없으면
				bean.setFile_use("N");
			} else { 
				bean.setFile_use("Y");
				String img = content.substring(target_num,(content.substring(target_num).indexOf("style=\"width:")+target_num)) + "style=\"width: 100%; height:100%;\">";
				bean.setThumb(img);
			}
		}
		
		
		// 모델에 저장
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("searchoption", searchoption);
		model.addAttribute("search", search);

		return "team_project/tour/board/review";
	}

	// http://localhost:8080/myapp/reviewWrite
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.GET)
	public String reviewWrite(HttpSession session,Model model) {
		System.out.println("/boardController GET tourreviewWrite() ");
		
		// 세션값 없으면 경고창
		String regi_id = (String) session.getAttribute("id");

		if (regi_id == null) {
			model.addAttribute("msg", "로그인 후 작성해주세요.");
			return "team_project/tour/member/msg";
		}

		MemberBean mb = memberService.userInfo(regi_id);
		model.addAttribute("mb", mb);

		return "team_project/tour/board/reviewWriteForm";
	}
	
	// http://localhost:8080/myapp/review/delete
	@RequestMapping(value = "/review/delete", method = RequestMethod.GET)
	public String reviewDelete(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("/boardController POST delete() ");
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
		System.out.println("delete 할때: " + seq );
		
		int result = service.delete(seq);
		
		return "redirect:/tourreview";
		
	}
	
	@RequestMapping(value = "/reviewUpdate", method = RequestMethod.GET)
	   public String reviewUpdate(BoardBean bb, Model model, HttpServletRequest request,HttpSession session) {
	      System.out.println("/boardController GET reviewUpdate() ");
	      
	      // 세션값 없으면 뒤로 돌려보냄~
	      String regi_id = (String) session.getAttribute("id");

	      if (regi_id == null) {
	         model.addAttribute("msg", "잘못된 접근입니다.");
	         return "redirect:/review";
	         }

	      System.out.println("BoardController /board/reviewUpdate get update()");

	      int seq = Integer.parseInt(request.getParameter("seq"));

	      BoardBean bbb = service.getArticle(seq);

	      String pageNum = request.getParameter("pageNum");

	      model.addAttribute("bb1", bbb);
	      model.addAttribute("pageNum", pageNum);

	      return "team_project/tour/board/reviewUpdateForm";
	   }
	   
	   // http://localhost:8080/myapp/reviewUpdate
	         @RequestMapping(value = "/reviewUpdate", method = RequestMethod.POST)
	         public String reviewUpdatePost(BoardBean bb, Model model, HttpServletRequest request) {
	            System.out.println("/boardController GET reviewUpdatePost() ");
	            
	            int result = service.contentUpdate(bb);
	            System.out.println("게시물 수정: " + result);
	            if(result > 0) {
	               model.addAttribute("msg","게시물이 수정되었습니다.");
	               model.addAttribute("url","reviewcontent?seq="+bb.getSeq());
	               
	            } else {
	               System.out.println("게시물 수정 실패~~~~");
	               model.addAttribute("msg","게시물 수정에 실패하였습니다.");
	               return "team_project/tour/member/msg";
	            }

	            return "redirect:/tourreview";
	         }
	
	// http://localhost:8080/myapp/reviewWrite
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.POST)
	public String reviewWritePost(HttpSession session, HttpServletRequest request, BoardBean bean,Model model) {
		System.out.println("/boardController tourreviewWritePost() ");
		System.out.println("review 여기 도착 1");
		
//		 모든 게시판 공통 세팅
//				 작성자 아이디
				String regi_id = (String) session.getAttribute("id");
				bean.setRegi_id(regi_id);

//				 작성자 ip
				bean.setRegi_ip_addr(utilService.getIp(request));

//				 조회수 0으로 초기화
				bean.setReadcount(0);

//				 자유게시판 세팅
//				 게시판 종류
				bean.setBoard_id("review");
//				 공지인가? - normal N/ notice Y 이 들어간다
				String Notice = request.getParameter("notice_use");
				if(Notice == null) {
					Notice = "N";
				}
				bean.setNotice_use(Notice);//
//				비밀글 - 공개 N / 비공개 Y
				bean.setSecret_use("N");
//				 좋아요 싫어요 0 으로 초기화
				bean.setLike(0);
				bean.setHate(0);
//				 추천수 0으로 초기화
				bean.setRecommend(0);
//				 regi_ip_addr, modifier_ip_addr 세팅
				bean.setRegi_ip_addr(utilService.getIp(request));
				bean.setModifier_ip_addr(utilService.getIp(request));

//				 다른 게시판 세팅들 - 다른 게시판 세팅은 모두 주석 처리 하겠음
//				 나라 설정
				bean.setCountry(request.getParameter("country"));
				bean.setSubject(request.getParameter("subject"));
				bean.setContent(request.getParameter("content"));
				
//					service 에 bean insert 한다
				int result = service.write(bean);
				System.out.println("review 여기 도착 2 " +result );
				if(result == 1) {
					System.out.println("review 여기 도착 3");
					String msg = "글이 정상적으로 등록 되었습니다.";
					model.addAttribute("msg",msg);
					model.addAttribute("url","tourreview");
				} else {
					System.out.println("글 등록 실패 ~~ tourreview");
					String msg = "글 등록 실패 ㅠㅠ";
					model.addAttribute("msg",msg);
					
					return "/team_project/tour/member/msg";
				}

				return "/team_project/tour/common/msg";
//		return "redirect:tourreview";
	}
	
	
	// http://localhost:8080/myapp/reviewContent
		@RequestMapping(value = "/reviewcontent", method = RequestMethod.GET)
		public String reviewContent(Model model, HttpServletRequest request) {
			System.out.println("/boardController GET freeboardContent()");

			// 번호 가져오기
			int seq = Integer.parseInt(request.getParameter("seq"));
			String regi_id = (String)request.getParameter("regi_id");
			
			// 조회수 증가
			service.updateReadcount(seq);

			// 번호에 맞는 게시물 가져오기
			BoardBean article = service.getArticle(seq);
			System.out.println("후기게시판 글 가져 왔냐 ?? " +  article);
			
			List<BoardCommentBean> commentList = service.getCommentList(seq);
			// 댓글의 regi_id 를 통해 memberBean 을 가져오기 (프로필 사진)
			for(BoardCommentBean bean : commentList) {
				MemberBean mb = memberService.getMemberInfoById(bean.getRegi_id());
				bean.setProfile(mb.getProfile_photo());
				bean.setGender(mb.getGender());
			}
			
			List<FileSettingBean> fileList = service.getFileById(regi_id);
			if(fileList!=null) {
				System.out.println("파일 가져왔냐? " + fileList);
				model.addAttribute("file", fileList);
			}
			// 게시물 모델에 담기
			model.addAttribute("Article", article);
			model.addAttribute("commentList", commentList);
			
			return "team_project/tour/board/reviewcontent";
		}	
	
		// reviewContent에서 댓글 썻을때
		@RequestMapping(value = "/reviewComment", method = RequestMethod.POST)
		public String reviewComment(Model model, HttpSession session, HttpServletRequest request, BoardCommentBean commentBean) {
			System.out.println("/boardController POST freeboardComment()");

			// 작성자 아이디
			String regi_id = (String) session.getAttribute("id");
			commentBean.setRegi_id(regi_id);
			commentBean.setRegi_ip_addr(utilService.getIp(request));
			
			commentBean.setRe_ref(0);
			commentBean.setRe_lev(0);
			commentBean.setRe_seq(0);
			
			commentBean.setLikeCount(0);
			commentBean.setHateCount(0);
			
			// 댓글 저장
			service.insertComment(commentBean);
			int seq = commentBean.getBoard_seq();
			// 게시글 번호
//			model.addAttribute("seq", commentBean.getBoard_seq());
			
			System.out.println("여기까지 오냐?1????????????????????");
			
			return "redirect:/reviewcontent?seq="+seq;
		}
	

// 갤러리
		
	
		// http://localhost:8080/myapp/imageDelete
		@RequestMapping(value = "/imageDelete", method = RequestMethod.GET)
		public String imageDelete(Model model, HttpServletRequest request) {
			System.out.println("/boardController GET imageDelete() ");
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			System.out.println("imageDelte    seq : "+seq);
			
			int result1 = service.imageDeleteFromFS(seq);
			int result2 = service.imageDeleteFromBoard(seq);
			
			System.out.println("result1, 2 : " + result1 + ", " + result2);
			if(result1 > 0 && result2 > 0) {
				System.out.println("image deleted ! ");
			}
			return "redirect:gallery";
		}
		
		

	// http://localhost:8080/myapp/gallery
	@RequestMapping(value = "/gallery", method = RequestMethod.GET)
	public String gallery(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET gallery() ");

		List<FileSettingBean> file = null;
		List<BoardBean> board = null;
//			String regi_id = request.getParameter("regi_id");
//			String board_id = request.getParameter("board_id");
//			System.out.println("갤러리 board_id = " + board_id);
//			
//			if(!regi_id.equals("")) {
//				file = service.getFileById(regi_id);
//				board = service.getBoardFileById(board_id, regi_id);
//			} else {
		file = service.getFile();
		board = service.getBoardFile("gallery");

//			}

		model.addAttribute("file", file);
		model.addAttribute("board", board);

		return "team_project/tour/board/gallery";
	}

	@RequestMapping(value = "/galleryId", method = RequestMethod.GET)
	public String galleryPost(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET gallery() ");

		List<FileSettingBean> file = null;
		List<BoardBean> board = null;
		String regi_id = request.getParameter("regi_id");
		String board_id = request.getParameter("board_id");
		System.out.println("갤러리 board_id = " + board_id);

		if (!regi_id.equals("")) {
			file = service.getFileById(regi_id);
			board = service.getBoardFileById(board_id, regi_id);
		} else {
			file = service.getFile();
			board = service.getBoardFile("gallery");

		}
		
		String realId = request.getParameter("regi_id");
		
		model.addAttribute("realId",realId);
		model.addAttribute("file", file);
		model.addAttribute("board", board);

		return "team_project/tour/board/gallery";
	}

	// http://localhost:8080/myapp/imageUpload
	@RequestMapping(value = "/imageUpload", method = RequestMethod.GET)
	public String imageUpload(HttpSession session) {
		System.out.println("/boardController GET imageUpload() ");

		return "team_project/tour/board/galleryWriteForm";
	}

	// http://localhost:8080/myapp/imageUpload
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public String imageUploadPost(MultipartFile file, HttpServletRequest request, HttpSession session,Model model)
			throws Exception {
		System.out.println("/boardController POST imageUpload() ");

		// 세션값 없으면 뒤로 돌려보냄~
		String id = (String) session.getAttribute("id");

		if (id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "team_project/tour/member/msg";
		}
		
		System.out.println("BoardController /board/fwrite post  fwritePost()");
		System.out.println("originalName: " + file.getOriginalFilename());
		System.out.println("size: " + file.getSize());
		System.out.println("contentType: " + file.getContentType());


		// 파일이름 중복인 경우 뒤에 숫자 붙이기
//			UUID uid = UUID.randomUUID();
//			String saveName = uid.toString()+"_"+file.getOriginalFilename();
		String filename1 = "";
		String filename = file.getOriginalFilename();
		System.out.println("filename ==> " + filename);
		int result = service.filenameCheck(filename);
			System.out.println("filenameCheck result ==> " + result);
			
		    if (result > 0) {          
		    	String name[] = filename.split("\\.");
		    	// 파일이름 난수방법으로 밀리쎄컨 붙여줌
		    	name[0] += System.currentTimeMillis();
		    	filename1 = name[0].concat("."+name[1]);
			} else {
		filename1 = filename;
			}
		    System.out.println("filename1 : " + filename1);
		    
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);

		String dateDir ="";
		dateDir = "\\"+ year + "/" + month + "/" + date;
		
		
		System.out.println("targetDir 찍어본다1 : "+ uploadPath);
		File targetDir = new File(uploadPath + dateDir.replace("\\", "/"));
			System.out.println("targetDir 찍어본다 : " + targetDir);
		if (!targetDir.exists()) {
			System.out.println("targetDir 만들기 전..");
			targetDir.mkdirs();
			System.out.println("targetDir 만들었다..");
		}
			System.out.println("targetDir 찍어본다2 : "+uploadPath);

		// 실제 파일은 file.getBytes()
		// 파일을 upload 폴더에 넣기 => 파일 카피
		File target = new File(uploadPath + dateDir, filename1);
		System.out.println("target: 이름: " +  target);
		
		FileCopyUtils.copy(file.getBytes(), target);
		
		// 이 밑으로는 파일업로드랑 관계X
		
		
		// 자바빈 저장
		BoardBean bb = new BoardBean();
			System.out.println(request.getParameter("hashtags"));

		bb.setBoard_id("gallery");


		// 해시태그 붙여서 etc1에 저장하기
		// 해시태그 중복 없애기
		String hashtag[] = request.getParameter("hashtags").split(",");
		Set<String> set = new HashSet<String>();
		for (String i : hashtag) {
			set.add(i);

		}
		// 해시태그 중복 없앤 값 # 붙여서 str에 담기
		String str = "";
		String hashtag2[] = set.toArray(new String[set.size()]);
		for(String i : hashtag2) {
			i = "#" + i;
			str += i;
			System.out.println("str출력 ㅡ> " + str);
		}

		bb.setEtc1(str);
		bb.setSubject(request.getParameter("hashtags"));
		bb.setNotice_use(request.getParameter("notice_use"));
		bb.setSecret_use(request.getParameter("secret_use"));
		bb.setRegi_id(id);
		bb.setContent(request.getParameter("content"));
		bb.setFile_use("1");
		// regi_ip_addr, modifier_ip_addr 세팅
		bb.setRegi_ip_addr(utilService.getIp(request));
		bb.setModifier_ip_addr(utilService.getIp(request));
		service.write(bb);
		
		System.out.println("여기여기" + dateDir);
		dateDir = dateDir.replace("\\", "");
		System.out.println("여기여기2" + dateDir);
		
		FileSettingBean fs = new FileSettingBean();
		fs.setFile_name(filename1);
		fs.setPath(dateDir);
		fs.setSize(file.getSize());
		fs.setRegi_id(id);
		fs.setExt(file.getContentType());
		fs.setBoard_seq(bb.getSeq());
		// regi_ip_addr, modifier_ip_addr 세팅
		fs.setRegi_ip_addr(utilService.getIp(request));
 
		service.fileUpload(fs);

		return "redirect:gallery";
	}

// 꿀팁관련
	// http://localhost:8080/myapp/honey
	@RequestMapping(value = "/honey", method = RequestMethod.GET)
	public String honey(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET honey() ");
		PageBean pb = new PageBean();

		String searchSelect = (String) request.getParameter("searchSelect");
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pb.setSearchSelect(searchSelect);
		pb.setSearch(search);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10;

		pb.setPageSize(pageSize);
		pb.setPageNum(pageNum);
		pb.setCurrentPage((Integer.parseInt(pageNum)));
		pb.setBoard_id("honey");

		List<BoardBean> list = service.getBoardList(pb);
//			pb.setCount(service.getBoardCount());

		pb.setCount(service.getBoardCount(pb));

		model.addAttribute("list", list);
		model.addAttribute("pageBean", pb);
		model.addAttribute("searchSelect", searchSelect);

		return "team_project/tour/board/honey/honey";
	}

	// http://localhost:8080/myapp/honeyWrite
	@RequestMapping(value = "/honeyWrite", method = RequestMethod.GET)
	public String honeyWrite(HttpSession session, Model model) {
		System.out.println("/boardController GET honeyWrite() ");

		// 세션값 없으면 뒤로 돌려보냄~
		String regi_id = (String) session.getAttribute("id");

		if (regi_id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "team_project/tour/member/msg";
		}

		MemberBean mb = memberService.userInfo(regi_id);
		model.addAttribute("mb", mb);

		return "team_project/tour/board/honey/honeyWriteForm";
	}

	// http://localhost:8080/myapp/honeyWrite
	@RequestMapping(value = "/honeyWrite", method = RequestMethod.POST)
	public String honeyWritePost(HttpServletRequest request, 
			MultipartHttpServletRequest mtfRequest, HttpSession session) throws Exception {
		System.out.println("/boardController GET honeyWritePost() ");
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		BoardBean bb = new BoardBean();

		 
		String Notice = request.getParameter("notice_use");
		if(Notice == null) {
			Notice = "N";
		}
		bb.setNotice_use(Notice);
		
		
		bb.setSubject(request.getParameter("subject"));
		bb.setContent(request.getParameter("content"));
		bb.setBoard_id("honey");
		bb.setSecret_use("N");
		bb.setEtc1(request.getParameter("board"));
		bb.setRegi_id(request.getParameter("id"));
		bb.setRegi_ip_addr(utilService.getIp(request));
		
		System.out.println("fileList를 뽑아보자 "+fileList);
		if (fileList != null) {
			bb.setFile_use("Y");
		} else {
			bb.setFile_use("N");
//			filename = file.getOriginalFilename();
		}

		service.write(bb);
		
		
		
//		Calendar cal = Calendar.getInstance();
//		int year = cal.get(Calendar.YEAR);
//		int month = cal.get(Calendar.MONTH) + 1;
//		int date = cal.get(Calendar.DATE);
//
//		String dateDir ="";
//		dateDir ="\\" + year + "/" + month + "/" + date+ "\\";
//		
//		
//		System.out.println("targetDir 찍어본다1 : "+ uploadPath);
//		File targetDir = new File(uploadPath + dateDir.replace('\\', '/'));
//			System.out.println("targetDir 찍어본다 : " + targetDir);
//		if (!targetDir.exists()) {
//			System.out.println("targetDir 만들기 전..");
//			targetDir.mkdirs();
//			System.out.println("targetDir 만들었다..");
//		}
//			System.out.println("targetDir 찍어본다2 : "+uploadPath);
//
//		// 실제 파일은 file.getBytes()
//		// 파일을 upload 폴더에 넣기 => 파일 카피
//		File target = new File(uploadPath + dateDir, filename1);
//		System.out.println("target: 이름: " +  target);
//		
//		FileCopyUtils.copy(file.getBytes(), target);

		if (fileList != null) {
			System.out.println("fileList 에 들ㅇ ㅓ 옹 ㅏ ㄹ따 ㄹ따 ㄹㅇ ㅏ "+ fileList);
			
			for(MultipartFile mf : fileList) {
				FileSettingBean fs = new FileSettingBean();
				String originalFileName = mf.getOriginalFilename();
				long fileSize = mf.getSize();
				
				System.out.println("오리지날 네임: "+ originalFileName);
				System.out.println("파일 사이즈 : "+ fileSize);
				
				String safeFile = uploadPath + System.currentTimeMillis() + originalFileName;
				String save = System.currentTimeMillis() + originalFileName;
				
				File indi = new File(uploadPath, save);
				FileCopyUtils.copy(mf.getBytes(), indi);
				System.out.println("indi파일은 뭘까 : "+indi);
				try {
	                
	                fs.setFile_name(save);
					fs.setPath(uploadPath);
					fs.setSize(mf.getSize());
					fs.setRegi_id(request.getParameter("id"));
					fs.setExt(mf.getContentType());
					fs.setBoard_seq(bb.getSeq());
					fs.setRegi_ip_addr(utilService.getIp(request));

					service.fileUpload(fs);
					
					mf.transferTo(new File(safeFile));
					
					System.out.println("DB에 저장한 파일이름: "+save);
	                
	            } catch (IllegalStateException e) {
	                System.out.println("honeyWrite 파일 올리는데 오류발생 " + e.getMessage());
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
			}
				
			
			
		}

		return "redirect:honey";
	}
	
	
	
	
	
	
	// http://localhost:8080/myapp/honeyWrite
//		@RequestMapping(value = "/honeyWrite", method = RequestMethod.POST)
//		public String honeyWritePost(MultipartFile file, HttpServletRequest request, 
//				MultipartHttpServletRequest mtfRequest, HttpSession session) throws Exception {
//			System.out.println("/boardController GET honeyWritePost() ");
//
//			BoardBean bb = new BoardBean();
//
//			bb.setNotice_use(request.getParameter("notice"));
//			System.out.println(request.getParameter("notice"));
//			bb.setSubject(request.getParameter("subject"));
//			bb.setContent(request.getParameter("content"));
//			bb.setBoard_id("honey");
//			bb.setSecret_use("N");
//			bb.setEtc1(request.getParameter("board"));
//			bb.setRegi_id(request.getParameter("id"));
//			bb.setRegi_ip_addr(utilService.getIp(request));
//			String filename = "";
//			if (file == null) {
//				bb.setFile_use("N");
//			} else {
//				bb.setFile_use("Y");
//				filename = file.getOriginalFilename();
//			}
//
//			service.write(bb);
//
//			if (!filename.equals("")) {
//
//				Calendar cal = Calendar.getInstance();
//				int year = cal.get(Calendar.YEAR);
//				int month = cal.get(Calendar.MONTH) + 1;
//				int date = cal.get(Calendar.DATE);
//
//				String dateDir = year + "/" + month + "/" + date;
//				uploadPath += "\\" + dateDir + "\\";
//				System.out.println("uploadPath 이름!!  "+ uploadPath);
//				File targetDir = new File(uploadPath.replace('\\', '/'));
//				System.out.println("targetDir 디렉토리 이름!!  "+ targetDir);
//				if (!targetDir.exists()) {
//					targetDir.mkdirs();
//					System.out.println("targetDir 디렉토리 만들었움!!");
//				}
//
//				// 실제 파일은 file.getBytes()
//				// 파일을 upload 폴더에 넣기 => 파일 카피
//				File target = new File(uploadPath, filename);
//
//				FileCopyUtils.copy(file.getBytes(), target);
//
//				FileSettingBean fs = new FileSettingBean();
//				fs.setFile_name(file.getOriginalFilename());
//				fs.setPath(dateDir);
//				fs.setSize(file.getSize());
//				fs.setRegi_id(request.getParameter("id"));
//				fs.setExt(file.getContentType());
//				fs.setBoard_seq(bb.getSeq());
//				// regi_ip_addr, modifier_ip_addr 세팅
//				fs.setRegi_ip_addr(utilService.getIp(request));
//
//				service.fileUpload(fs);
//			}
//
//			return "redirect:honey";
//		}
	
	// http://localhost:8080/myapp/honeyContent
	@RequestMapping(value = "/honeycontent", method = RequestMethod.GET)
	public String honeyContent(HttpServletRequest request, Model model, HttpSession session) {
		System.out.println("/boardController GET honeyContent() ");
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		String regi_id = (String)session.getAttribute("id");
		
		service.updateReadcount(seq);
		BoardBean bb = service.getArticle(seq);
		
		List<BoardCommentBean> commentList = service.getCommentList(seq);
		// 댓글의 regi_id 를 통해 memberBean 을 가져오기 (프로필 사진)
		for(BoardCommentBean bean : commentList) {
			MemberBean mb = memberService.getMemberInfoById(bean.getRegi_id());
			bean.setProfile(mb.getProfile_photo());
			bean.setGender(mb.getGender());
		}
		
		List<FileSettingBean> file = service.getFileBySeq(seq);
		if(file!=null) {
			System.out.println("file 왔냐 "+ file);
			model.addAttribute("file",file);
		}
		
		model.addAttribute("bb", bb);
		model.addAttribute("regi_id",regi_id);
		model.addAttribute("commentList", commentList);
		
		System.out.println("나간다~~~");
		
		return "team_project/tour/board/honey/honeycontent";
	}
	
	// honeyContent에서 댓글 썻을때
	@RequestMapping(value = "/honeyComment", method = RequestMethod.POST)
	public String honeyComment(Model model, HttpSession session, HttpServletRequest request, BoardCommentBean commentBean) {
		System.out.println("/boardController POST honeyComment()");

		// 작성자 아이디
		String regi_id = (String) session.getAttribute("id");
		commentBean.setRegi_id(regi_id);
		commentBean.setRegi_ip_addr(utilService.getIp(request));
		
		commentBean.setRe_ref(0);
		commentBean.setRe_lev(0);
		commentBean.setRe_seq(0);
		
		commentBean.setLikeCount(0);
		commentBean.setHateCount(0);
		
		// 댓글 저장
		service.insertComment(commentBean);
		int seq = commentBean.getBoard_seq();
		// 게시글 번호
//		model.addAttribute("seq", commentBean.getBoard_seq());
//		System.out.println("여기까지 오냐?1????????????????????");
		
		return "redirect:/honeycontent?seq="+seq;
	}

	// http://localhost:8080/myapp/honeyUpdate
	@RequestMapping(value = "/honeyUpdate", method = RequestMethod.GET)
	public String honeyUpdate(BoardBean bb, Model model, HttpServletRequest request,HttpSession session) {
		System.out.println("/boardController GET honeyUpdate() ");
		
		// 세션값 없으면 뒤로 돌려보냄~
		String regi_id = (String) session.getAttribute("id");

		if (regi_id == null) {
			model.addAttribute("msg", "잘못된 접근입니다.");
			return "team_project/tour/member/msg";
		}

		System.out.println("BoardController /board/update get update()");

		int seq = Integer.parseInt(request.getParameter("seq"));

		BoardBean bbb = service.getArticle(seq);

		String pageNum = request.getParameter("pageNum");

		model.addAttribute("bb1", bbb);
		model.addAttribute("pageNum", pageNum);

		return "team_project/tour/board/honey/honeyUpdateForm";
	}
	
	
	
	// http://localhost:8080/myapp/honeyUpdate
	@RequestMapping(value = "/honeyUpdate", method = RequestMethod.POST)
	public String honeyUpdatePost(BoardBean bb, Model model, HttpServletRequest request) {
		System.out.println("/boardController GET honeyUpdatePost() ");
		
		int result = service.contentUpdate(bb);
		System.out.println("게시물 수정: " + result);
		if(result > 0) {
			model.addAttribute("msg","게시물이 수정되었습니다.");
			model.addAttribute("url","honeycontent?seq="+bb.getSeq());
			
		} else {
			System.out.println("게시물 수정 실패~~~~");
			model.addAttribute("msg","게시물 수정에 실패하였습니다.");
			return "team_project/tour/member/msg";
		}

		return "team_project/tour/common/msg";
	}

	
	// http://localhost:8080/myapp/honey/delete
	@RequestMapping(value = "/honey/delete", method = RequestMethod.GET)
	public String honeyDelete(Model model, HttpServletRequest request, HttpSession session) {
		System.out.println("/boardController POST delete() ");
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		
		
		System.out.println("delete 할때: " + seq );
		
		int result = service.delete(seq);
		
		return "redirect:/honey";
		
	}


	// http://localhost:8080/myapp/airport
	@RequestMapping(value = "/airport", method = RequestMethod.GET)
	public String countryInfo(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("/boardController GET countryInfo() ");
		PageBean pb = new PageBean();

		String searchSelect = (String) request.getParameter("searchSelect");
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pb.setSearchSelect(searchSelect);
		pb.setSearch(search);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		} 

		pb.setPageSize(10);
		pb.setPageNum(pageNum);
		pb.setCurrentPage(Integer.parseInt(pageNum));
		pb.setBoard_id("honey");
		pb.setEtc1("공항");

		List<BoardBean> list = service.getSubBoard(pb);
		int num = service.getSubBoardCount("honey", "공항");
		pb.setCount(num);

		model.addAttribute("list", list);
		model.addAttribute("pageBean", pb);
		model.addAttribute("searchSelect", searchSelect);

		return "team_project/tour/board/honey/airport";
	}

	// http://localhost:8080/myapp/traffic
	@RequestMapping(value = "/traffic", method = RequestMethod.GET)
	public String traffic(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET traffic() ");

		PageBean pb = new PageBean();

		String searchSelect = (String) request.getParameter("searchSelect");
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pb.setSearchSelect(searchSelect);
		pb.setSearch(search);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		pb.setPageSize(10);
		pb.setPageNum(pageNum);
		pb.setCurrentPage(Integer.parseInt(pageNum));
		pb.setBoard_id("honey");
		pb.setEtc1("교통");

		List<BoardBean> list = service.getSubBoard(pb);
		int num = service.getSubBoardCount("honey", "교통");
		pb.setCount(num);

		model.addAttribute("list", list);
		model.addAttribute("pageBean", pb);
		model.addAttribute("searchSelect", searchSelect);

		return "team_project/tour/board/honey/traffic";
	}

	// http://localhost:8080/myapp/honey/weather
	@RequestMapping(value = "/weather", method = RequestMethod.GET)
	public String weather(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET weather() ");
		PageBean pb = new PageBean();

		String searchSelect = (String) request.getParameter("searchSelect");
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pb.setSearchSelect(searchSelect);
		pb.setSearch(search);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		pb.setPageSize(10);
		pb.setPageNum(pageNum);
		pb.setCurrentPage(Integer.parseInt(pageNum));
		pb.setBoard_id("honey");
		pb.setEtc1("날씨");

		List<BoardBean> list = service.getSubBoard(pb);
		int num = service.getSubBoardCount("honey", "날씨");
		pb.setCount(num);

		model.addAttribute("list", list);
		model.addAttribute("pageBean", pb);
		model.addAttribute("searchSelect", searchSelect);

		return "team_project/tour/board/honey/weather";
	}

	// http://localhost:8080/myapp/honey/culture
	@RequestMapping(value = "/culture", method = RequestMethod.GET)
	public String culture(Model model, HttpServletRequest request) {
		System.out.println("/boardController GET culture() ");
		PageBean pb = new PageBean();

		String searchSelect = (String) request.getParameter("searchSelect");
		String search = (String) request.getParameter("search");

		if (searchSelect == null) {
			searchSelect = "subject";
		}
		if (search == null) {
			search = "";
		}

		pb.setSearchSelect(searchSelect);
		pb.setSearch(search);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		pb.setPageSize(10);
		pb.setPageNum(pageNum);
		pb.setCurrentPage(Integer.parseInt(pageNum));
		pb.setBoard_id("honey");
		pb.setEtc1("문화");

		List<BoardBean> list = service.getSubBoard(pb);
		int num = service.getSubBoardCount("honey", "문화");
		pb.setCount(num);

		model.addAttribute("list", list);
		model.addAttribute("pageBean", pb);
		model.addAttribute("searchSelect", searchSelect);

		return "team_project/tour/board/honey/culture";
	}
	
	// http://localhost:8080/myapp/subscribe
		@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
		public String subscribe(Model model, HttpServletRequest request) {
			
			String email = request.getParameter("email");
			
			if (email == null) {
				return "redirect:/tourindex";
			}
			
				int result = memberService.deleteSubscriber(email);
				if(result>0) {
					model.addAttribute("msg", "구독취소가 완료되었습니다.");
					model.addAttribute("url", "tourindex");
				} else {
					System.out.println("구독취소 실패~~");
				}
			
			return "/team_project/tour/common/msg";
		}
		
		

		
		
//	// http://localhost:8080/myapp/flight	
//	@RequestMapping(value = "/flight", method = RequestMethod.GET)
//	public String flight(HttpSession session) {
//		System.out.println("/boardController GET flight() ");
//
//		return "team_project/flight/flightsearch";
//	}
}
