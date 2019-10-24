package com.itwill.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itwill.domain.JsonDataBean;
import com.itwill.domain.ScdCodeBean;

public interface UtilService {

	public String getCode(String paramData) throws JsonProcessingException;

	public String getJson(Map<String, Object> map);

	public Map<String, Object> getMap(String paramData);

	public String getData(String paramData) throws JsonProcessingException;

	public String getListJson(List<Object> list);

//	public JsonDataBean setJsonData(List<Object> list);

	public String getIp(HttpServletRequest request);

	public List<Object> getListData(String paramData);

	public JsonDataBean setJsonListData(List<Object> list);

	public JsonDataBean setJsonIntData(int result);

	public String getCodeData(Map<String, Object> map);

	public JsonDataBean setJsonStringData(String data);

	public Map<String, String> getDataTableMpa(MultiValueMap<String, String> formData);

	public String getCodeDataSetting(List<Object> columnTypeList, Map<String, Object> map);

	public String getCodeDataSetting2(List<Object> columnTypeList);

	// 현재 날짜를 기준으로 하여 문자열 만들기
	public String getDateData(String Type);
	
	// 현재 시간을 기준으로 하여 문자열 만들기
	public String getTimeData(String Type);
	

	// 파일 업로드를 하고 결과를 리턴
	public String setFileUpload(String dateDir, MultipartFile mpf);

}
