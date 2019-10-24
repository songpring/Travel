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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.domain.CouponBean;
import com.itwill.service.CouponService;
import com.itwill.service.UtilService;

@Controller
public class CouponController {
	
	@Inject
	private CouponService service;
	@Inject
	private UtilService util;
	
	private ObjectMapper mapper = new ObjectMapper();
	
//	쿠폰 관리
	@RequestMapping(value = "/couponManagement", method = RequestMethod.GET)
	public String couponManagement(HttpSession session) {
		System.out.println("/HomeController2 GET couponManagement() ");
		// /WEB-INF/views/insertForm.jsp
		
//		회원등급(레벨)에 따라 쿠폰 관리를 할 수 있는지 여부를 따지기
		
		return "/team_project/tour/coupon/couponManagement";
	}
	
	
	
	
// 쿠폰 정보만들기
	@RequestMapping(value = "/couponInformation", method = RequestMethod.GET)
	public String couponInformation(HttpSession session) {
		System.out.println("/HomeController2 GET couponInformation() ");
		// /WEB-INF/views/insertForm.jsp
		return "/team_project/tour/coupon/couponInformation";
	}
// 쿠폰 정보만들기 POST
	@RequestMapping(value = "/couponInformation", method = RequestMethod.POST)
	public String couponInformationPost(HttpSession session, Model model, CouponBean cb,HttpServletRequest request) {
		
		System.out.println("/HomeController2 POST couponInformation() ");

		service.insertCoupon(cb);
		
//		회원등급(레벨)에 따라 coupon관리를 할 수 있는지 여부를 따지고
//		쿠폰추가가 완료되면 로그인은 그대로 유지하고 
		
		// 쿠폰관리 페이지로 넘어간다(완)
		return "redirect:/couponManagement";
 	}
	
	
	
	
//	쿠폰 발송
	@RequestMapping(value = "/receiveCoupon", method = RequestMethod.GET)
	public String receiveCoupon(HttpSession session) {
		System.out.println("/HomeController2 GET receiveCoupon() ");
		// /WEB-INF/views/insertForm.jsp
		return "/team_project/tour/coupon/receiveCoupon";
	}
//	쿠폰 발송 POST
	@RequestMapping(value = "/receiveCoupon", method = RequestMethod.POST)
	public String receiveCouponPost(HttpSession session) {
		System.out.println("/HomeController2 POST receiveCoupon() ");
		// /WEB-INF/views/insertForm.jsp
		return "redirect:/couponManagement";
	}


	
	
//	쿠폰 수정
	@RequestMapping(value = "/couponUpdate", method = RequestMethod.GET)
	public String couponUpdate(HttpSession session) {
		System.out.println("/HomeController2 GET couponUpdate() ");
		// /WEB-INF/views/insertForm.jsp
		return "/team_project/tour/coupon/couponUpdate";
	}
//	쿠폰 수정POST
	@RequestMapping(value = "/couponUpdate", method = RequestMethod.POST)
	public String couponUpdatePost(HttpSession session) {
		System.out.println("/HomeController2 POST couponUpdate() ");
		// /WEB-INF/views/insertForm.jsp
		return "redirect:/couponManagement";
	}	
	
	
	
	
//	쿠폰 삭제
	@RequestMapping(value = "/couponDelete", method = RequestMethod.GET)
	public String couponDelete(HttpSession session) {
		System.out.println("/HomeController2 GET couponDelete() ");
		// /WEB-INF/views/insertForm.jsp
		return "/team_project/tour/coupon/couponDelete";
	}
//	쿠폰 삭제 POST
	@RequestMapping(value = "/couponDelete", method = RequestMethod.POST)
	public String couponDeletePost(HttpSession session, Model model, CouponBean cb, HttpServletRequest request, RedirectAttributes redirectAttr) {
		System.out.println("/HomeController2 POST couponDelete() " + cb.getSeq());

//		cb = service.setDelete(cb);
		
//		조건식을 어떻게 할까여?!,,,히히
		// 바로 버튼만 누르면 삭제가 되길 바람...
		return "redirect:/couponManagement";
	}
	
	
//	쿠폰 발급 내역
	@RequestMapping(value = "/couponissueHistory", method = RequestMethod.GET)
	public String couponissueHistory(HttpSession session) {
		System.out.println("/HomeController2 GET couponissueHistory() ");
	
//	회원등급(레벨)에 따라 쿠폰 관리를 할 수 있는지 여부를 따지기
	// 쿠폰 종류를 뿌려준 후에 쿠폰을 누르면 
	   //그 쿠폰을 발급 받은 사람을 뿌려주기
	return "/team_project/tour/coupon/couponissueHistory";
}
}

