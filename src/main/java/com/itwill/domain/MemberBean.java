package com.itwill.domain;

import java.sql.Timestamp;

public class MemberBean {
	private String id; 		 // 아이디
	private String password; // 비밀번호
	private String name; 	 // 이름
	private String eng_name; // 영문이름
	private String nickname; // 닉네임
	private String birth; //생년월일
	private String email; 	 // 이메일
	private String phone; 	 // 전화번호
	private String gender; // 성별
	private String passport_number; // 여권
	private String zip_code; // 우편번호
	private String address1; // 주소
	private String address2; // 상세 주소
	private String profile_photo; // 프로필사진
	private String introduce; // 자기 소개
	private int point;  // 포인트
	private int mileage; // 마일리지
	private int level; // 등급
	private String regi_id;
	private Timestamp regi_date;
	private String regi_ip_addr;
	private String modifier_id;
	private Timestamp modifier_date;
	private String modifier_ip_addr;
	
	// 쿼리 실행 결과를 바로 저장
	private String result; // 결과값을 저장하기 위한 내용
	private String message; // 세부내용
	
	
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEng_name() {
		return eng_name;
	}
	public void setEng_name(String eng_name) {
		this.eng_name = eng_name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassport_number() {
		return passport_number;
	}
	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getRegi_id() {
		return regi_id;
	}
	public void setRegi_id(String regi_id) {
		this.regi_id = regi_id;
	}
	public Timestamp getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Timestamp regi_date) {
		this.regi_date = regi_date;
	}
	public String getRegi_ip_addr() {
		return regi_ip_addr;
	}
	public void setRegi_ip_addr(String regi_ip_addr) {
		this.regi_ip_addr = regi_ip_addr;
	}
	public String getModifier_id() {
		return modifier_id;
	}
	public void setModifier_id(String modifier_id) {
		this.modifier_id = modifier_id;
	}
	public Timestamp getModifier_date() {
		return modifier_date;
	}
	public void setModifier_date(Timestamp modifier_date) {
		this.modifier_date = modifier_date;
	}
	public String getModifier_ip_addr() {
		return modifier_ip_addr;
	}
	public void setModifier_ip_addr(String modifier_ip_addr) {
		this.modifier_ip_addr = modifier_ip_addr;
	}

}