package com.itwill.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.ScdCodeBean;

@Repository
public class UtilDAOImpl implements UtilDAO {
	
	@Inject
	private SqlSession sql;
	
	private static final String namespace="com.itwill.mappers.scdCodeMapper";

	@Override
	public List<Map<String,Object>> getCode(Object obj) {
		System.out.println("UtilDAOImpl getCode" );

		List<Map<String,Object>> list = (List)sql.selectList(namespace+".listScdCode",obj);
		
		System.out.println("끝");
		return list;
	}

}
