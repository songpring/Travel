package com.itwill.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.service.ScdCodeService;
import com.itwill.service.TableColumnMappingService;
import com.itwill.service.UtilService;
import com.itwill.domain.BoardSettingBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.TableColumnMappingBean;
import com.itwill.service.BoardSettingService;

@Controller
public class AdministraController {
	
	@Inject
	private ScdCodeService service;
	
	@Inject
	private UtilService utilService;
	
	@Inject
	private BoardSettingService BoardSettingService;
	
	@Inject
	private TableColumnMappingService TableColumnMappingService;
	
	//공통코드 관리
	@RequestMapping(value = "/scdCode", method = RequestMethod.GET)
	public String scdCode(HttpSession session) {
		System.out.println("/administra/scdCode GET scdCode() ");

		//return "team_project/tour/administra/system/scdCodeFrom";
		return "team_project/tour/administra/system/scdCodeForm";
	}
	
	// 회원리스트관리
	@RequestMapping(value = "/adminMemberList", method = RequestMethod.GET)
	public String adminMemberList(HttpSession session, Model model) {
		System.out.println("/administra/adminMemberList GET adminMemberList() ");
		// 회원검색목록 코드 가져 오기
		//selectBox
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("control_type", "select");
		map.put("first_item_type", "all");
		map.put("code_div", "09");
		map.put("control_id", "ddlSearchType");
		map.put("type", "code");
		map.put("class", "form-control");
		model.addAttribute("ddlSearchType",utilService.getCodeData(map));
		//return "team_project/tour/administra/system/scdCodeFrom";
		return "team_project/tour/administra/member/memberList";
	}
	
	
//	@RequestMapping(value = "/adminMemberList", method = RequestMethod.POST)
//	public String userList(HttpSession session, MemberBean mb, Model model) {
//		System.out.println("/administra/adminMemberList GET userList() ");
//		System.out.println("name: " + mb.getName()+", id: " + mb.getId()+", nickname: "+mb.getNickname());
//		
//		if(mb.getId()=="" &&mb.getName()==""&&mb.getNickname()=="") {
//			List<MemberBean> list = null;//service.userList();
//			model.addAttribute("list",list);
//			session.setAttribute("list2", list);
//		} 
//
//		return "redirect:adminMemberList";
//	}
	
	
	// 게시판 관리 리스트  
	@RequestMapping(value = "/adminBoardSetting", method = RequestMethod.GET)
	public String adminBoardSetting(HttpSession session, Model model) {
		System.out.println("/administra/adminBoardSetting GET join() ");
		// 게시판 종류 코드 가져 오기
		//selectBox
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "05");
		map.put("control_id", "selboardType");
		map.put("type", "code");
		map.put("class", "form-control");
		model.addAttribute("selboardType",utilService.getCodeData(map));
		//return "team_project/tour/administra/system/scdCodeFrom";
		return "team_project/tour/administra/board/boardSetting";
	}
	
	// 게시판 컬럼 관리
	@RequestMapping(value = "/adminTableColumnMappingInfo", method = RequestMethod.POST)
	public String adminTableColumnMappingInfo(HttpSession session, Model model, TableColumnMappingBean tcb) {
		String boardId=tcb.getBoard_id();
		model.addAttribute("seq",tcb.getSeq());
		List<TableColumnMappingBean> list;
		System.out.println("/administra/adminTableColumnMappingInfo" +  tcb.getSeq());
		tcb = new TableColumnMappingBean();
		tcb.setBoard_id(boardId);
		
		list = TableColumnMappingService.getInfoBeanList(tcb);
		
		// 게시판 종류 코드 가져 오기
		//selectBox
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("control_type", "select");
		map.put("first_item_type", "select");
		map.put("code_div", "08");
		map.put("control_id", "column_type");
		map.put("type", "code");
		map.put("class", "form-control");
		
		String test = utilService.getCodeData(map);
		
		System.out.println("column_type : "+ test);
		model.addAttribute("column_type",test);
		//model.addAttribute("column_type",utilService.getCodeData(map));
		
		// 컬럼타입 데이터만 가져 오기
		map.clear();
		map.put("code_div", "08");
		map.put("code_step", "2");
		map.put("use_yn", "Y");
		map.put("call_type", "AllDate2");
		List<Object> columnTypeList = service.getCodeObject(map);
		
		
		// 컬럼타입 데이터만 가져 오기
		map.clear();
		map.put("code_div", "06");
		map.put("code_step", "2");
		map.put("use_yn", "Y");
		map.put("call_type", "AllDate2");
		List<Object> BoardColumnList = service.getCodeObject(map);
		
		for(int i=0;i<list.size();i++) {
			tcb = list.get(i);

			// 컬럼타입
			map.clear();
			map.put("control_type", "select");
			map.put("first_item_type", "select");
			map.put("class", "form-control");
			map.put("control_id", "column_type");
			map.put("selectItem", tcb.getColumn_type());
			map.put("type", "code");
			map.put("id_use", "n");
			tcb.setColumn_type(utilService.getCodeDataSetting(columnTypeList,map));
			
			// 사용여부
			map.clear();
			map.put("control_type", "select");
			map.put("first_item_type", "select");
			map.put("class", "form-control");
			map.put("control_id", "board_column_use");
			map.put("selectItem", tcb.getBoard_column_use());
			map.put("type", "code");
			map.put("id_use", "n");
			tcb.setBoard_column_use(utilService.getCodeDataSetting(BoardColumnList,map));

			list.set(i, tcb);
		}
		model.addAttribute("list",list);
		
		//System.out.println("column_type : "+ utilService.getCodeData(map));
		
		return "team_project/tour/administra/board/tableColumnMappingInfo";
	}
	
	
	
}
