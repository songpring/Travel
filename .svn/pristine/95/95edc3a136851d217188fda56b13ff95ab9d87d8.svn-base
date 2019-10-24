package com.itwill.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.dao.TableColumnMappingDAO;
import com.itwill.domain.JsonDataBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.TableColumnMappingBean;

@Service
public class TableColumnMappingServiceImpl implements TableColumnMappingService {

	@Inject
	private TableColumnMappingDAO dao;
	
	@Inject
	private UtilService util;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	// Ajax 통신
	@Override
	public String getAjaxData(Map<String, Object> map) {
		System.out.println("getAjaxData");
		JsonDataBean json = new JsonDataBean();
		String jsonStr="";
		
		// paramData 데이터를 Map 형식으로 리턴
		//Map<String, Object> map = util.getMap(map);
		
		// 데이터 가져오기
		List<Object> list = new ArrayList<Object>();
		if(map.get("call_type")!=null) {
			if(map.get("call_type").equals("save")){
				int result = 0;
				result = dao.update(map); 
				// 가져온 데이터를 JsonDataBean 객체에 할당
				json = util.setJsonIntData(result);
			}
		}
		
		try {
			jsonStr = mapper.writeValueAsString(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return jsonStr;
	}

	// Bean 객체로 받아서 List<TableColumnMappingBean> 객체로 리턴
	@Override
	public List<TableColumnMappingBean> getInfoBeanList(TableColumnMappingBean tcb) {
		System.out.println("getInfoBeanList !!!!");
		List<TableColumnMappingBean> list;
		list = dao.getInfoBeanList(tcb);
		
		return list;
	}

	// Bean 객체로 받아서 Bean 객체로 단일건 리턴
	@Override
	public TableColumnMappingBean getInfoBeanData(TableColumnMappingBean tcb) {
		System.out.println("getInfoBeanData !!!!");
		tcb = dao.getInfoBeanData(tcb);
		return tcb;
	}

	//데이터를 Bean 객체로 받아서 업데이트 후 결과값을 BoardSettingBean로 리턴을 받아 정의
	@Override
	public TableColumnMappingBean setBeanUpdate(TableColumnMappingBean tcb) {
		System.out.println("setBeanUpdate !!!! : " + tcb.getSeq());
		tcb = dao.setBeanUpdate(tcb);
		return tcb;
	}

	//데이터를 Bean 객체로 받아서 업데이트 후 결과값을 BoardSettingBean로 리턴을 받아 정의
	@Override
	public TableColumnMappingBean setBeanDelete(TableColumnMappingBean tcb) {
		System.out.println("setBeanDelete !!!! : " + tcb.getSeq());
		tcb = dao.setBeanDelete(tcb);
		return tcb;
	}
	
	
	
	
	

}
