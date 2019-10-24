package com.itwill.service;

import java.sql.Timestamp;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwill.dao.CouponDAO;
import com.itwill.domain.CouponBean;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Inject
	private CouponDAO dao;
	@Inject
	private UtilService utilService;

	@Override
	public void insertCoupon(CouponBean cb) {
		System.out.println("CouponServiceImpl insertCoupon()");
		
		
		// 등록자 아이디의 초기값을 관리자 아이디로 설정
		cb.setRegi_id("admin");
		
		
		// 현재시간으로 초기값 넣어 주기
		cb.setRegi_date(new Timestamp(System.currentTimeMillis()));
		cb.setModifier_date(new Timestamp(System.currentTimeMillis()));
		
		dao.insertCoupon(cb);
	}

}
