package com.itwill.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.dao.ScdCodeDAO;
import com.itwill.dao.UtilDAO;
import com.itwill.domain.JsonDataBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.ScdCodeBean;


@Service
public class ScdCodeServiceImpl implements ScdCodeService {
	
	@Inject
	private UtilService util;
	
	@Inject
	private ScdCodeDAO scdDao;
	
//	@Inject
	private ObjectMapper mapper = new ObjectMapper();
	
	private String control_type;  // 컨트롤 타입
	private String first_item_type;// 셀렉트 박스 일때 전체 혹은 선택 표시 뜨도록
	private String control_id; // 만들어지는 아이디값
	private String bean_type; // 사용할 빈 타입
	
	
	@Override
	public List<Object> getCodeObject(Map<String, Object> map) {
		System.out.println("ScdCodeServiceImpl getCodeObject!!!");
		// 데이터 가져오기
		System.out.println("map : "+map);
		List<Object> list = new ArrayList<Object>();
		if(map.get("call_type")!=null) {
			
			if(map.get("call_type").equals("AllDate")){
				list = scdDao.getAllData(map);
			}
			else if(map.get("call_type").equals("AllDate2")){
				list = scdDao.getCodeObject(map);
			}
		}
		
		return list;
	}


	@Override
	public String getAjaxData(Map<String, Object> map) throws JsonProcessingException {
		System.out.println("ScdCodeServiceImpl getAjaxData!!!");
		JsonDataBean json = new JsonDataBean();
		String jsonStr="";
		
		// paramData 데이터를 Map 형식으로 리턴
		//Map<String, Object> map = util.getMap(map);
		
		// 데이터 가져오기
		List<Object> list = new ArrayList<Object>();
		if(map.get("call_type")!=null) {
			
			if(map.get("call_type").equals("AllDate")){
				list = scdDao.getAllData(map);

				// 가져온 데이터를 JsonDataBean 객체에 할당
				json = util.setJsonListData(list);
			}
			else if(map.get("call_type").equals("save")){
				int result = 0;
				result = scdDao.insert(map); 

				// 가져온 데이터를 JsonDataBean 객체에 할당
				json = util.setJsonIntData(result);
			}
			else if(map.get("call_type").equals("delete")){
				int result = 0;
				result = scdDao.delete(map); 

				// 가져온 데이터를 JsonDataBean 객체에 할당
				json = util.setJsonIntData(result);
			}
		}
		
		
		
		// JsonBean 객체의 내용을 json 형식으로 변경
		jsonStr = mapper.writeValueAsString(json);
				
		return jsonStr;
	}


	@Override
	public List<MemberBean> userList(MemberBean mb) {
		return scdDao.userList(mb);
	}


	@Override
	public List<MemberBean> userList() {
		
		return scdDao.userList();
	}

	
	
	
	
}





















