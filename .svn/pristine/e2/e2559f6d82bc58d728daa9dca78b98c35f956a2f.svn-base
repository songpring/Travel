package com.itwill.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.CouponBean;

@Repository
public class CouponDAOImpl implements CouponDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace="com.itwill.mapper.couponMapper";
	@Override
	public void insertCoupon(CouponBean cb) {
		System.out.println("CouponDAOImpl insertcoupon()");
		sqlSession.insert(namespace+".insertCoupon",cb);
	}

}
