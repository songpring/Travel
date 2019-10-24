package com.itwill.dao;
import java.util.List;
import java.util.Map;

import com.itwill.domain.ScdCodeBean;
import com.itwill.domain.MemberBean;

public interface ScdCodeDAO {

	List<Map<String, Object>> getCode(Object obj);
	List<ScdCodeBean> getCodeBean(Object obj);
	List<Object> getCodeObject(Object obj);
	List<Object> getAllData(Map<String, Object> map);
	int insert(Map<String, Object> map);
	int delete(Map<String, Object> map);
	public List<MemberBean> userList(MemberBean mb);
	public List<MemberBean> userList();

	
}
