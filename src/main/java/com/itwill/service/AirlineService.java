package com.itwill.service;

import java.util.List;
import java.util.Map;

import com.itwill.domain.AirlineBean;
import com.itwill.domain.FlightSearchBean;

public interface AirlineService {

	List<Object> flightSearch(FlightSearchBean bean);

	String getCTName(String code);

	void insertAirBooking(AirlineBean airBooking);

	String getAjaxData(Map<String, Object> map);

}
