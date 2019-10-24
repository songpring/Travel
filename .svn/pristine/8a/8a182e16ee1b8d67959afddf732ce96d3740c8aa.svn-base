package com.itwill.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itwill.domain.BoardSettingBean;
import com.itwill.domain.DataTableBean;
import com.itwill.domain.ScdCodeBean;
import com.itwill.domain.TableColumnMappingBean;
import com.itwill.service.BoardSettingService;
import com.itwill.service.TableColumnMappingService;
import com.itwill.service.UtilService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BoardSettingController {
	
	
	@Inject
	private UtilService util;
	
	@Inject
	private BoardSettingService service;
	
	
	
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardSettingController.class);
	
	@RequestMapping(value = "adminBoardSettingList", method = RequestMethod.POST)
	@ResponseBody
	public DataTableBean adminBoardSettingList(@RequestBody MultiValueMap<String, String> formData, BoardSettingBean bsb, DataTableBean data){
		System.out.println("formData : " + formData);
	   
		// util 서비스를 이용하여 DataTable에 사용할 데이터가공후 (map) 객체로 리턴
		Map<String, String> map = new HashMap<String, String>();
	    map = util.getDataTableMpa(formData);
	    
	    // 전체 갯수
	    int total = 0;
	    total = service.totalCount(map);
	    
	    // 실제 데이터 
	    List list = null;
	    list = service.getDataTableList(map);

	    
	    // 리턴 받을 DataTableBean 세팅
	    data.setDraw(Integer.parseInt(map.get("draw").toString()));
	    data.setRecordsFiltered(total);
	    data.setRecordsTotal(total);
	    data.setData(list);
	    return data;
	}
	//

	// 게시판 관리 상세정보
	@RequestMapping(value = "/adminBoardSettingInfo")
	public String adminBoardSettingInfo(HttpSession session, Model model, HttpServletRequest request
			, BoardSettingBean bsb) {
		System.out.println("/administra/adminBoardSettingInfo" +  bsb.getSeq());
		
		// 업로드 실행시 업로드 이후 다시 이 페이지로 와야 하는데 redirect 를 이용하므로 따로 넘기는 변수를 받아야 함
		// Map 형식으로 출력을 할수 있으니 아래와 같이 사용
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		if(flashMap !=null) {
			bsb.setSeq(Integer.parseInt(flashMap.get("seq").toString()));
		}
		
		// 데이터 가져 오기
		//System.out.println("bsb.getSeq() : " +bsb.getSeq());
		if(bsb.getSeq()>0) {
			//System.out.println("여기 오냐?");
			bsb = service.getInfoBeanData(bsb);
		}
		//System.out.println("bsb.getSeq() : " +bsb.getSeq());
		
		model.addAttribute("bsb",bsb);
	
		Map<String, Object> map = new HashMap<String, Object>();
		// 게시판 종류 코드 가져 오기
		//selectBox
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "05");
		map.put("control_id", "board_types");
		map.put("type", "code");
		map.put("class", "form-control");
		map.put("selectItem", bsb.getBoard_types());
		model.addAttribute("board_types",util.getCodeData(map));
		
		// 글쓰기 권한
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "07");
		map.put("control_id", "ssl_writing_authority");
		map.put("type", "code");
		map.put("class", "form-control");
		//map.put("selectItem", bsb.getWriting_authority());
		model.addAttribute("ssl_writing_authority",util.getCodeData(map));

		// 글수정 권한
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "07");
		map.put("control_id", "ssl_modification_authority");
		map.put("type", "code");
		map.put("class", "form-control");
		//map.put("selectItem", bsb.getModification_authority());
		model.addAttribute("ssl_modification_authority",util.getCodeData(map));
		
		// 글삭제 권한
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "07");
		map.put("control_id", "ssl_delete_authority");
		map.put("type", "code");
		map.put("class", "form-control");
		//map.put("selectItem", bsb.getDelete_authority());
		model.addAttribute("ssl_delete_authority",util.getCodeData(map));

		// 글 읽기 권한
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "07");
		map.put("control_id", "ssl_read_authority");
		map.put("type", "code");
		map.put("class", "form-control");
		//map.put("selectItem", bsb.getRead_authority());
		model.addAttribute("ssl_read_authority",util.getCodeData(map));
		
		// 공지 사용 여부
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "06");
		map.put("control_id", "notification_use");
		map.put("type", "code");
		map.put("class", "form-control");
		map.put("selectItem", bsb.getNotification_use());
		model.addAttribute("notification_use",util.getCodeData(map));
		
		// 첨부파일 사용 여부
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "06");
		map.put("control_id", "file_use");
		map.put("type", "code");
		map.put("class", "form-control");
		map.put("selectItem", bsb.getFile_use());
		model.addAttribute("file_use",util.getCodeData(map));
		
		
		// 답변 사용 여부
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "06");
		map.put("control_id", "re_write_use");
		map.put("type", "code");
		map.put("class", "form-control");
		map.put("selectItem", bsb.getRe_write_use());
		model.addAttribute("re_write_use",util.getCodeData(map));
		
		// 코멘트 사용 여부
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "06");
		map.put("control_id", "comments_use");
		map.put("type", "code");
		map.put("class", "form-control");
		map.put("selectItem", bsb.getComments_use());
		model.addAttribute("comments_use",util.getCodeData(map));
		return "team_project/tour/administra/board/boardSettingInfo";
	}
	
	// 게시판 관리 저장
	@RequestMapping(value = "/adminBoardSettingUpdate", method = RequestMethod.POST)
	public String adminBoardSettingUpdate(HttpSession session, Model model, BoardSettingBean bsb,HttpServletRequest request, RedirectAttributes redirectAttr) {
		System.out.println("/administra/adminBoardSettingUpdate" +  bsb.getSeq());
		
		int orgSeq = bsb.getSeq();
		
		// 수정자 정보 ip, id 입력
		bsb.setModifier_ip_addr(util.getIp(request));
		bsb.setModifier_id((String)session.getAttribute("id"));
		bsb.setRegi_ip_addr(util.getIp(request));
		bsb.setRegi_id((String)session.getAttribute("id"));

		bsb = service.setBeanUpdate(bsb);
		System.out.println("bsb.getBoard_id() : " + bsb.getBoard_id());

		if(bsb.getResult().equals("1")) {
			redirectAttr.addFlashAttribute("seq",bsb.getSeq());
			return "redirect:adminBoardSettingInfo";
		}
		else {
			model.addAttribute("msg", bsb.getMessage());
			return "/team_project/tour/common/msg";
		}
	}
	
	// 게시판 관리 삭제
	@RequestMapping(value = "/adminBoardSettingDelete", method = RequestMethod.POST)
	public String adminBoardSettingDelete(HttpSession session, Model model, BoardSettingBean bsb,HttpServletRequest request, RedirectAttributes redirectAttr) {
		System.out.println("/administra/adminBoardSettingDelete" +  bsb.getSeq());
		bsb = service.setBeanDelete(bsb);
		if(bsb.getResult().equals("1")) {
			return "redirect:adminBoardSetting";
		}
		else {
			model.addAttribute("msg", bsb.getMessage());
			return "/team_project/tour/common/msg";
		}
	}
}





