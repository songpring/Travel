package com.itwill.dao;

import java.util.List;
import java.util.Map;

import com.itwill.domain.AirlineBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.MessageBean;

public interface MemberDAO {
	public int insertMember(MemberBean mb);

	public List<AirlineBean> getReservationList(String member_id);

	public void reservationCancel(int index);
	
	public MemberBean userCheck(String id, String password);
	public MemberBean userCheck2(Map<String, Object> map);
	
	public MemberBean userInfo(String id);
	
	public int updatePost(MemberBean mb);
	public MemberBean deletePost(MemberBean mb);

	public String getNickName(String regi_id);
	// 2019.09.26 박용훈 전체 회원 갯수 구하는거 추가
	public int getCount(Map<String, String> map);
	// 2019.09.26 박용훈 데이터 테이블즈 용 리스트 추가
	public List getDataTableList(Map<String, String> map);
	
	void insertMessage(MessageBean msgb);

	// Bean 객체로 데이터 가져 오기
	public MemberBean getInfoBeanData(MemberBean mb);

	// Bean 객체의 데이터를 입력하고 결과값을 받음
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
	public int idCheck(Map<String, Object> map);
	//닉네임 중복확인
	public int nickCheck(Map<String, Object> map);
	
	// mypage에서 프사변경
	public int updateProfile(String filename, String id);

	public MemberBean getMemberInfoById(String regi_id);
}
