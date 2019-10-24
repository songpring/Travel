package com.itwill.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.MemberBean;
import com.itwill.domain.ScdCodeBean;

@Repository
public class ScdCodeDAOImpl implements ScdCodeDAO {
	
	@Inject
	private SqlSession sql;
	
	private static final String namespace="com.itwill.mappers.scdCodeMapper";

	@Override
	public List<Map<String, Object>> getCode(Object obj) {
		//System.out.println("UtilDAOImpl getCode" );
		List<Map<String,Object>> list = (List)sql.selectList(namespace+".select",obj);
		//System.out.println("끝");;
		return list;
	}
	
	@Override
	public List<Object> getAllData(Map<String, Object> map) {
		List<Object> list = new ArrayList<Object>();
		list = (List)sql.selectList(namespace+".listScdCodeMap",map);
		
		System.out.println("list : " + list);

		return list;
	}

	@Override
	public List<ScdCodeBean> getCodeBean(Object obj) {
		//System.out.println("UtilDAOImpl getCode2" );
		List<ScdCodeBean> list = (List)sql.selectList(namespace+".select",obj);
		//System.out.println("끝");
		return list;
	}
	
	@Override
	public List<Object> getCodeObject(Object obj) {
		//System.out.println("UtilDAOImpl getCode3" );
		List<Object> list = (List)sql.selectList(namespace+".select",obj);
		//System.out.println("끝");
		return list;
	}

	// 공통코드 등록
	@Override
	public int insert(Map<String, Object> map) {
		int result = 0;
		result = sql.insert(namespace+".insert",map);
		return result;
	}
	
	// 공통코드 삭제
	@Override
	public int delete(Map<String, Object> map) {
		int result = 0;
		result = sql.delete(namespace+".delete",map);
		return result;
	}

	@Override
	public List<MemberBean> userList(MemberBean mb) {
		return sql.selectList(namespace+".userList",mb);
	}

	@Override
	public List<MemberBean> userList() {
		return sql.selectList(namespace+".allUserList");
	}
	
	
	

}
