package com.itwill.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.itwill.domain.TableColumnMappingBean;

public interface TableColumnMappingService {
	
	TableColumnMappingBean getInfoBeanData(TableColumnMappingBean tcb);

	TableColumnMappingBean setBeanUpdate(TableColumnMappingBean tcb);

	TableColumnMappingBean setBeanDelete(TableColumnMappingBean tcb);

	List<TableColumnMappingBean> getInfoBeanList(TableColumnMappingBean tcb);

	String getAjaxData(Map<String, Object> map);
	
}
