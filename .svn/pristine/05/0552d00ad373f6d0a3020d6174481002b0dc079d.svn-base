package com.itwill.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.AirlineBean;

@Repository
public class AirlineDAOImpl implements AirlineDAO {
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.itwill.mapper.AirlineMapper"; //com.itwill.mapper.AirlineMapper
	
	@Override
	public String getCTName(String code) {
//		System.out.println("AirlineDAOImpl getCode()");
		System.out.println("==========================================");
		System.out.println("code : " + code);
		String a = "";
		try{
			a = sqlSession.selectOne(namespace+".getCTName",code);
			System.out.println(a);
			System.out.println("==========================================");
		} catch (Exception e) {
			System.out.println("애러 : "  + e.getMessage());
		}
		return a;
	}

	
	@Override
	public void insertAirBooking(AirlineBean airBooking) {
		sqlSession.insert(namespace+".insertAirBooking",airBooking);
	}


	
	
}
