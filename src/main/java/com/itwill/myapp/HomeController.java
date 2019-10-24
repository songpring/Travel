package com.itwill.myapp;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.itwill.domain.ScdCodeBean;
import com.itwill.service.UtilService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@Inject
	private UtilService util;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
//	
//
//	
//	@RequestMapping(value = "basicTest", method = RequestMethod.GET)
//	public String basicTest(Locale locale, Model model) {
//		
//		// 코드를 가지고 자동으로 select, radio, check 만들기
//
//		// MAP 으로 파라미터를 만들어서 json 으로 변환해서 넘기기
//		// 국가 가져오기
//		//selectBox
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("control_type", "select");
//		map.put("first_item_type", "select");
//		map.put("code_div", "CO");
//		map.put("control_id", "selCountry");
//		map.put("type", "code");
//		model.addAttribute("selCountry",getData(map));
//		
//		// 도시 가져오기
//		map.clear();
//		map.put("control_type", "select");
//		map.put("first_item_type", "select");
//		map.put("code_div", "CT");
//		map.put("control_id", "selCity");
//		map.put("type", "code");
//		model.addAttribute("selCity",getData(map));
//		
//		//성별구분
//		map.clear();
//		map.put("control_type", "radio"); //radio ,select ,select2, check
//		map.put("first_item_type", "select"); // select:선택 , all:전체
//		map.put("code_div", "01");
//		map.put("control_id", "radGender1");
//		map.put("type", "code");
//		model.addAttribute("radGender1",getData(map));
//		
//		return "basicTest";
//	}
//	
//	
//	@RequestMapping(value = "ajaxCodeProc", method = RequestMethod.POST)
//	@ResponseBody
//	public String ajaxCodeProc(@RequestBody String paramData) {
//		String returnValue ="";
//		System.out.println("paramData : " + paramData);
//		try {
//			returnValue = util.getCode(paramData);
//		} catch (JsonProcessingException e) {
//			System.out.println(e.getMessage());
//		}
//		//System.out.println("paramData : " + paramData);
//		System.out.println("returnValue : " + returnValue);
//		return returnValue;
//	}
//	
//	@RequestMapping(value = "ajaxProc", method = RequestMethod.POST)
//	@ResponseBody
//	public String ajaxProc(@RequestBody String paramData, HttpServletRequest request,HttpSession session){
//		System.out.println("ajaxProc : "+ paramData);
//		String returnValue ="";
//	
//		// 아이피 주소 작성자 추가 작업
//		// paramData 데이터를 Map 형식으로 리턴
//		Map<String, Object> map = util.getMap(paramData);
//		map.put("regi_ip_addr", util.getIp(request));
//		map.put("modifier_ip_addr", util.getIp(request));
//		map.put("regi_id", (String)session.getAttribute("id"));
//		map.put("modifier_id", (String)session.getAttribute("id"));
//		paramData = util.getJson(map);
//		
//		try {
//			returnValue = util.getData(paramData);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("ajaxProc Error" + e.getMessage());
//		}
//        
//		return returnValue;
//	}
//	
//	// 아작스통신시 단순 리스트로 리턴
//	@RequestMapping(value = "ajaxList", method = RequestMethod.POST)
//	@ResponseBody
//	public List<Object> ajaxList(@RequestBody String paramData){
//		System.out.println("ajaxList : "+ paramData);
//		List<Object> list = null;
//		
//		list = util.getListData(paramData);
//        
//		return list;
//	}
//	
//	
//	@RequestMapping(value = "test", method = RequestMethod.POST)
//	@ResponseBody
//	public String test(@RequestBody String paramData){
//		System.out.println("ajaxProctest : "+ paramData);
//		String returnValue ="";
//		return returnValue;
//	}
//	
//	
//	
//	
//	// 실제 데이터 가져 오기
//	public String getData(Map<String, Object> map) {
//		String returnValue="";
//		// map 형식을 String 형식으로 변환
//		String json = util.getJson(map);
//		
//		try {
//			// json 형식으로 코드를 가져옴 
//			returnValue = util.getCode(json);
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// 실제 data 만 추출 하기 위해 map로 변경후 데이터 뽑아서 리턴
//		map.clear();
//		// 가져온 코드를 map 형으로 변경
//		map = util.getMap(returnValue);
//		// 실제 데이터는 data 에 담겨 있어서 data 값만 추출
//		returnValue = map.get("data").toString();
//		return returnValue;
//	}
	
	 
}




