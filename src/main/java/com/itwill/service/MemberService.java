package com.itwill.service;

import java.util.List;
import java.util.Map;

import com.itwill.domain.AirlineBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.MessageBean;

public interface MemberService {
	public int insertMember(MemberBean mb);

	public List<AirlineBean> getReservationList(String member_id);

	public void reservationCancel(int index);
	public MemberBean userCheck(String id, String password); // 회원 로그인 체크
    
	public String getAjaxData(Map<String, Object> map);

	public int updatePost(MemberBean mb);
	public MemberBean deletePost(MemberBean mb);

	public MemberBean userInfo(String id);

	public String getNickName(String regi_id);
	
	// 회원 테이블 전체 내용
	public int totalCount(Map<String, String> map);

	// 데이터테이블즈용 리스트
	public List getDataTableList(Map<String, String> map);
	
	void insertMessage(MessageBean msgb);

	// Bean 객체로 데이터 가져 오기
	public MemberBean getInfoBeanData(MemberBean mb);

	// 업데이트 이후 Bean 객체로 받기
	public MemberBean setBeanUpdate(MemberBean mb);

	// 삭제 이후 Bean 객체로 받기
	public MemberBean setBeanDelete(MemberBean mb);

	public int subsrcibe(String email);

	public int subscriberCheck(String tomail);

	public int deleteSubscriber(String email);

	public void sendMessage(MessageBean msgBean);

	public String getName(String nickname);

	public List<MessageBean> getMsgList(String id);
	
	//아이디 중복확인
	public int idCheck(String id);
	//닉네임 중복확인
	public int nickCheck(String nickname);
	// mypage에서 프사변경
	public int updateProfile(String filename, String id);
	
	public MemberBean getMemberInfoById(String regi_id);
}
