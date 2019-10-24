package com.itwill.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.AirlineBean;
import com.itwill.domain.FileSettingBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.MessageBean;

@Repository
public class MemberDAOImpl implements MemberDAO{
	//mybatis 객체생성 <= 자동주입
	@Inject
	private SqlSession sqlSession;
	// memberMapper 파일안에  namespace 이름 정의
	private static final String namespace="com.itwill.mapper.MemberMapper";
	
	@Override
	public int insertMember(MemberBean mb) {
		System.out.println("MemberDAOImpl insertmember()");
		// mybatise  sql 구문 호출 실행
		return sqlSession.insert(namespace+".insert",mb);
	}

	@Override
	public List<AirlineBean> getReservationList(String id) {
		
		List<AirlineBean> reservationList = null;
		
		try {
			reservationList = sqlSession.selectList(namespace+".getReservationList",id);
		} catch (Exception e) {
			System.out.println("에러 : "  + e.getMessage());
		}
		
		return reservationList;
	}
	
	//아이디 중복확인
	@Override
	public int idCheck(Map<String, Object> map) {
		int idCheck = sqlSession.selectOne(namespace+".idCheck", map);
//		System.out.println("idCheck : " + idCheck);
		
		// 중복아이디면 1 -> 2
		// 중복아이디 아니면 0 -> 1
		idCheck++;
		
		System.out.println("id : " + map.get("id"));
		System.out.println("idCheck : " + idCheck);
		
		return idCheck;
	}
// 닉네임 중복확인
	@Override
	public int nickCheck(Map<String, Object> map) {
		int nickCheck = sqlSession.selectOne(namespace+".nickCheck", map);
//		System.out.println("idCheck : " + idCheck);
		
		
		// 중복아이디면 1 -> 2
		// 중복아이디 아니면 0 -> 1
		nickCheck++;
		
		System.out.println("nickname : " + map.get("nickname"));
		System.out.println("nickCheck : " + nickCheck);
		
		return nickCheck;
	}
	

	

	@Override
	public MemberBean userCheck2(Map<String, Object> map) {
		System.out.println("userCheck!!!" );
//		MemberBean mb = new MemberBean();
//		mb.setId(id);
//		mb.setPassword(password);
		return sqlSession.selectOne(namespace+".userCheck", map);
		//return null;
	}


	@Override
	public void reservationCancel(int seq) {
		sqlSession.insert(namespace+".reservationCancel",seq);
	}


	@Override
	public MemberBean userCheck(String id, String password) {
		System.out.println("userCheck!!!" );
		MemberBean mb = new MemberBean();
		mb.setId(id);
		//mb.setPassword(password);
		return sqlSession.selectOne(namespace+".userCheck", mb);
	}


	@Override
	public MemberBean userInfo(String id) {
		return sqlSession.selectOne(namespace+".userInfo", id);
	}
	
	@Override
	public int updatePost(MemberBean mb) {
		return sqlSession.update(namespace+".updatePost",mb);
		
	}
	
	@Override
	public MemberBean deletePost(MemberBean mb) {
		int result = 0;
		try {
			result = sqlSession.delete(namespace+".deletePost",mb);
			mb.setResult(result+"");
			if(result>=1) {
				mb.setResult("1");
				mb.setMessage("삭제 완료되었습니다.");
			}
			else {
				mb.setMessage("처리가 안됐습니다.");
			}
		} catch (Exception e) {
			mb.setResult("-2");
			mb.setMessage("오류가 발생했습니다.");
		}
		return mb;
	}

	@Override
	public String getNickName(String regi_id) {
		return sqlSession.selectOne(namespace+".getNickName", regi_id);
	}
	
	// 2019.09.26 박용훈 전체 회원 갯수 구하는거 추가
	@Override
	public int getCount(Map<String, String> map) {
		int result=0;
		result = sqlSession.selectOne(namespace+".totCount",map);
		return result;
	}

	// 2019.09.26 박용훈 데이터 테이블즈 용 리스트 추가
	@Override
	public List getDataTableList(Map<String, String> map) {
		List list = null;
		list = (List)sqlSession.selectList(namespace+".selectListMap",map);
		return list;
	}
	
	@Override
	public void insertMessage(MessageBean msgb) {
		System.out.println("MemberDAOImpl insertmessage()");
		sqlSession.insert(namespace+".insertMessage",msgb);
	}

	// Bean 객체로 데이터 가져 오기
	@Override
	public MemberBean getInfoBeanData(MemberBean mb) {
		// TODO Auto-generated method stub
		mb = sqlSession.selectOne(namespace+".userInfoBean",mb);
		return mb;
	}


	// Bean 객체의 데이터를 입력하고 결과값을 받음
	@Override
	public MemberBean setBeanUpdate(MemberBean mb){
		int result = 0;
		try {
			result = sqlSession.insert(namespace+".setInsert",mb);
			mb.setResult(result+"");
			if(result>=1) {
				mb.setResult("1");
				mb.setMessage("처리되었습니다.");
			}
			else {
				mb.setMessage("처리된 내역이 없습니다.");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			mb.setResult("-2");
			mb.setMessage("오류가 발생 하였습니다");
		}
		
		return mb;
	}
	
	
	
	@Override
	public int subsrcibe(String email) {
		return sqlSession.insert(namespace+".subscribe", email);
	}

	@Override
	public int subscriberCheck(String tomail) {
		return sqlSession.selectOne(namespace+".subscriberCheck", tomail);
	}

	@Override
	public MemberBean setBeanDelete(MemberBean mb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteSubscriber(String email) {
		return sqlSession.delete(namespace+".deleteSubscriber", email);
	}

	@Override
	public void sendMessage(MessageBean msgBean) {
		sqlSession.insert(namespace+".sendMessage", msgBean);
		
	}

	@Override
	public String getName(String nickname) {
		return sqlSession.selectOne(namespace+".getName", nickname);
	}

	@Override
	public List<MessageBean> getMsgList(String id) {
		return sqlSession.selectList(namespace+".getMsgList",id);
	}

	@Override
	public int updateProfile(String profile_photo, String id) {
		MemberBean mb = new MemberBean();
		mb.setProfile_photo(profile_photo);
		mb.setId(id);
		return sqlSession.update(namespace+".updateProfile", mb);
	}
	

	@Override
	public MemberBean getMemberInfoById(String regi_id) {
		return sqlSession.selectOne(namespace+".getMemberInfoById", regi_id);
	}
	
}
