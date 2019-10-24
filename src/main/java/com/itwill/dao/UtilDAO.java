package com.itwill.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itwill.domain.ScdCodeBean;

public interface UtilDAO {

	List<Map<String, Object>> getCode(Object obj);

	
}
