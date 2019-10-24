package com.itwill.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itwill.dao.MemberDAO;
import com.itwill.dao.MemberDAOImpl;
import com.itwill.domain.AirlineBean;
import com.itwill.domain.JsonDataBean;
import com.itwill.domain.MemberBean;
import com.itwill.domain.MessageBean;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	@Inject
	private UtilService utilService;

	private ObjectMapper mapper = new ObjectMapper();

	// Ajax 통신
	@Override
	public String getAjaxData(Map<String, Object> map) {
		System.out.println("getAjaxData");
		JsonDataBean json = new JsonDataBean();
		String jsonStr = "";

		// paramData 데이터를 Map 형식으로 리턴
		// Map<String, Object> map = util.getMap(map);

		// 데이터 가져오기
		List<Object> list = new ArrayList<Object>();
		if (map.get("call_type") != null) {
			if (map.get("call_type").equals("userCheck")) {

				MemberBean mb = new MemberBean();

				String orgPass = map.get("password").toString();

				mb = dao.userCheck(map.get("id").toString(), map.get("password").toString());

				System.out.println("asdf");
				String result = "";
				if (mb != null) {

					if (orgPass.equals(mb.getPassword())) {
						// 로그인
						result = "1";
					} else {
						result = "2";
					}
					// list.add("1");
				} else {
					result = "3";
				}
				System.out.println("list : " + list);
				json = utilService.setJsonStringData(result);
			} else if (map.get("call_type").equals("userCheck2")) {

				MemberBean mb = new MemberBean();
				mb = dao.userCheck2(map);

				System.out.println("asdf");
				String result = "";
				if (mb != null) {
					result = "1";
				} else {
					result = "2";
					// list.add("2");
				}
				System.out.println("list : " + list);
				json = utilService.setJsonStringData(result);
			}else if (map.get("call_type").equals("idCheck")) {

				int idCheck = dao.idCheck(map);
				
				return idCheck+"";
//				System.out.println("list : " + list);
//				json = utilService.setJsonStringData(result);
			} 
			 else if (map.get("call_type").equals("nickCheck")) {

					int nickCheck = dao.nickCheck(map);
					
					return nickCheck+"";
//					System.out.println("list : " + list);
//					json = utilService.setJsonStringData(result);
				}
		
		}

		try {
			jsonStr = mapper.writeValueAsString(json);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonStr;

	}

	@Override
	public int insertMember(MemberBean mb) {
		System.out.println("MemberServiceImpl insertMember()");
		// 디비작업파일 부모=자식
		// 패키지 com.itwillbs.dao
//		MemberDAO memberDAO=new MemberDAOImpl();
//		memberDAO.insertMember(mb);

//		mb.setEmail("0"); -> 셀렉트 값만 불러와짐 
//		mb.setPhone("0"); -> // (추후 수정)
//		mb.setGender("1");
		// 코드 이용 x 초기값으로 일단 실험 -> 된당

		mb.setPoint(0);
		mb.setMileage(0);
		mb.setLevel(0);
		if(mb.getGender().equals("01")) {
			mb.setProfile_photo("male.jpg");
		} else if (mb.getGender().equals("02")) {
			mb.setProfile_photo("female.jpg");
		}
		// nn값들 초기값 0으로 주기

		mb.setRegi_date(new Timestamp(System.currentTimeMillis()));
		mb.setModifier_date(new Timestamp(System.currentTimeMillis()));
		// 현재시간으로 초기값 넣어 주기

		return dao.insertMember(mb);

	}

	@Override
	public List<AirlineBean> getReservationList(String id) {

		List<AirlineBean> reservationList = dao.getReservationList(id);

		return reservationList;
	}

	@Override
	public void reservationCancel(int seq) {
		dao.reservationCancel(seq);

	}

	@Override
	public MemberBean userCheck(String id, String password) {
		String orgPass = password;
		
		MemberBean userInfo = dao.userCheck(id, password);
		
		//MemberBean mb = new MemberBean();
		//String orgPass = map.get("password").toString();
		//mb = dao.userCheck(map.get("id").toString(),map.get("password").toString());
		
		System.out.println("asdf");
		String result = "";
		if(userInfo!=null) {
			if(orgPass.equals(userInfo.getPassword())){
				// 로그인
				//aaaa = "1";
				userInfo.setResult("1");
				
			}
			else {
				// 비밀번호가 틀림
				userInfo.setResult("2");
				userInfo.setMessage("아이디 또는 비밀번호가 틀렸습니다.");
			}

		}
		return userInfo;
	}

	@Override
	public MemberBean userInfo(String id) {
		return dao.userInfo(id);

	}

	@Override
	public int updatePost(MemberBean mb) {
		return dao.updatePost(mb);
		
	}

	@Override
	public MemberBean deletePost(MemberBean mb) {
		System.out.println("deletePost 왓따");
		mb = dao.deletePost(mb);
		
		return mb;
	}
	
	@Override
	public String getNickName(String regi_id) {
		return dao.getNickName(regi_id);
	}
	
	// 2019.09.26 박용훈 전체 회원 갯수 구하는거 추가
	@Override
	public int totalCount(Map<String, String> map) {
		int count = 0;
		count = dao.getCount(map);
 		return count;
	}

	// 2019.09.26 박용훈 데이터 테이블즈 용 리스트 추가
	@Override
	public List getDataTableList(Map<String, String> map) {
		List list = null;
		list = dao.getDataTableList(map);
		return list;
	}
	
	@Override
	public void insertMessage(MessageBean msgb) {
		System.out.println("MemberServiceImpl insertMessage()");

		// 현재시간으로 초기값 넣어 주기
		msgb.setRegi_date(new Timestamp(System.currentTimeMillis()));
		
		msgb.setRead_use("X");
		
		dao.insertMessage(msgb);
	}
	
	// Bean 객체로 데이터 가져 오기
	@Override
	public MemberBean getInfoBeanData(MemberBean mb) {
		System.out.println("getInfoBeanData !!!!");
		mb = dao.getInfoBeanData(mb);
		return mb;
	}
	
	@Override
	public MemberBean setBeanUpdate(MemberBean mb) {
		System.out.println("setBeanUpdate !!!! : " + mb.getId());
		mb = dao.setBeanUpdate(mb);
		return mb;
	}
	// 삭제 이후 Bean 객체로 받기
	@Override
	public MemberBean setBeanDelete(MemberBean mb) {
		System.out.println("setBeanDelete !!!! : " + mb.getId());
		mb = dao.setBeanDelete(mb);
		return mb;
	}
	
	
	

	@Override
	public int subsrcibe(String email) {
		return dao.subsrcibe(email);
	}

	@Override
	public int subscriberCheck(String tomail) {
		return dao.subscriberCheck(tomail);
	}

	@Override
	public int deleteSubscriber(String email) {
		return dao.deleteSubscriber(email);
	}

	@Override
	public void sendMessage(MessageBean msgBean) {
		dao.sendMessage(msgBean);
	}

	@Override
	public String getName(String nickname) {
		return dao.getName(nickname);
	}

	@Override
	public List<MessageBean> getMsgList(String id) {
		return dao.getMsgList(id);
	}

	@Override
	public int idCheck(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nickCheck(String nickname) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberBean getMemberInfoById(String regi_id) {
		return dao.getMemberInfoById(regi_id);
	}
	
	// 프사변경
	@Override
	public int updateProfile(String filename, String id) {
		return dao.updateProfile(filename, id);
	}

	


	
}
