package com.itwill.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itwill.domain.BoardSettingBean;

public interface BoardSettingService {

	List<BoardSettingBean> getBeanList(BoardSettingBean bsb);
	
	List<Object> getList(BoardSettingBean bsb);

	int totalCount(BoardSettingBean bsb);

	List getDataTableList(BoardSettingBean bsb);

	int totalCount(Map<String, String> map);

	List getDataTableList(Map<String, String> map);

	BoardSettingBean getInfoBeanData(BoardSettingBean bsb);

	BoardSettingBean setBeanUpdate(BoardSettingBean bsb);

	BoardSettingBean setBeanDelete(BoardSettingBean bsb);
	
}
