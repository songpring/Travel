package com.itwill.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itwill.domain.AirlineBean;
import com.itwill.domain.FlightSearchBean;
import com.itwill.service.AirlineService;
import com.itwill.service.MemberService;
import com.itwill.service.UtilService;


@Controller
public class AirlineController {
	
	@Inject
	private AirlineService service;
	@Inject 
	private UtilService utilService;
	
	public String changeDate(String date) {
		String[] temp = date.split("/");
		
		if(temp[1].length() == 1) {
			temp[1] = "0" + temp[1];
		} 
		if(temp[2].length() == 1) {
			temp[2] = "0" + temp[2];
		}
		
		return temp[0] + temp[1] + temp[2];
	}
	
	// http://localhost:8080/myapp/flight	
	// 항공권 검색 시 이동
	@RequestMapping(value = "/flight", method = RequestMethod.GET)
	public String flight(HttpSession session, HttpServletRequest request, Model model, FlightSearchBean bean) {
		System.out.println("/MemberController GET flight() ");

		// 도시 코드로 가져온다
		bean.setCode_dep((String)request.getParameter("selCity_dep"));
		bean.setCode_arr((String)request.getParameter("selCity_arr"));
		
		// 도시코드를 문자열로 바꿔온다
		String dep = service.getCTName(bean.getCode_dep());
		String arr = service.getCTName(bean.getCode_arr());
		bean.setDep(dep);
		bean.setArr(arr);
		
		
		// 날짜 형식 변경 
		// 2019/9/9 -> 20190909
		// 2019/10/10 -> 20191010
		bean.setDate_dep(changeDate(bean.getDate_dep()));
		bean.setDate_arr(changeDate(bean.getDate_arr()));
		
		
		// 입력폼 수정 해서 받아오면 삭제할꺼임
		bean.setBabyquantity("0");
		bean.setTrip("RT");
		
		// 검색조건에 맞는 항공권들 crawling 
		List<Object> flightSearchList = service.flightSearch(bean);
		
		// 리스트 가져와서 model 에 add
		model.addAttribute("flightSearchList", flightSearchList);
		model.addAttribute("FlightSearchBean", flightSearchList.get(0));
		
		return "/team_project/tour/flight/flightsearch";
	}
	
	// http://localhost:8080/myapp/airBooking	
	// 항공권 예매시 이동
	// 해당 항공권을 예매(insert)후 memberController의 reservationDetails로 이동
	@RequestMapping(value = "/airBooking", method = RequestMethod.GET)
	public String airBooking(HttpSession session, HttpServletRequest request, AirlineBean bean, Model model) {
		
		
		// 세션에서 사용자 이름 가져오기 
		String member_id = (String)session.getAttribute("id");
		
		if(member_id == null) {
			model.addAttribute("msg","로그인이 필요한 서비스입니다");
			return "/team_project/tour/common/msg";
		}
		
		bean.setId(member_id);
		
		// 작성자 ip 작성
		bean.setRegi_ip_addr(utilService.getIp(request));
		
		// 예매내역에 insert 하기 
		service.insertAirBooking(bean);
		
		// memberController의 reservationDetails로 이동
		return "redirect:/reservationDetails";
	}
	
	

	
	
	
}
