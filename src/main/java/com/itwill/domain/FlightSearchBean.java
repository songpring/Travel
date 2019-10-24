package com.itwill.domain;

public class FlightSearchBean {
	
	private String trip; // 왕복RT/ 편도OW/ 다구간MD
	private String dep;// 출발지
	private String arr; // 도착지
	private String code_dep; // 출발지 코드
	private String code_arr; // 도착지 코드
	private String date_dep; // 가는날
	private String date_arr; // 오는날
	private String adultquantity; // 어른
	private String childquantity; // 아이
	private String babyquantity; // 유아
	private String comp; // 일반석Y/ 비즈니스석C/ 일등석F
	
	public String getTrip() {
		return trip;
	}
	public void setTrip(String trip) {
		this.trip = trip;
	}
	public String getDep() {
		return dep;
	}
	public void setDep(String dep) {
		this.dep = dep;
	}
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}
	public String getCode_dep() {
		return code_dep;
	}
	public void setCode_dep(String code_dep) {
		this.code_dep = code_dep;
	}
	public String getCode_arr() {
		return code_arr;
	}
	public void setCode_arr(String code_arr) {
		this.code_arr = code_arr;
	}
	public String getDate_dep() {
		return date_dep;
	}
	public void setDate_dep(String date_dep) {
		this.date_dep = date_dep;
	}
	public String getDate_arr() {
		return date_arr;
	}
	public void setDate_arr(String date_arr) {
		this.date_arr = date_arr;
	}
	public String getAdultquantity() {
		return adultquantity;
	}
	public void setAdultquantity(String adultquantity) {
		this.adultquantity = adultquantity;
	}
	public String getChildquantity() {
		return childquantity;
	}
	public void setChildquantity(String childquantity) {
		this.childquantity = childquantity;
	}
	public String getBabyquantity() {
		return babyquantity;
	}
	public void setBabyquantity(String babyquantity) {
		this.babyquantity = babyquantity;
	}
	public String getComp() {
		return comp;
	}
	public void setComp(String comp) {
		this.comp = comp;
	}
	
	
}
