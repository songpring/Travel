package com.itwill.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.itwill.dao.BoardSettingDAO;
import com.itwill.dao.ScdCodeDAO;
import com.itwill.dao.TableColumnMappingDAO;
import com.itwill.domain.BoardSettingBean;
import com.itwill.domain.TableColumnMappingBean;

@Service
public class BoardSettingServiceImpl implements BoardSettingService {

	@Inject
	private BoardSettingDAO dao;
	
	@Inject
	private TableColumnMappingDAO tableColumnMappingdao;
	
	// 전체 카운터 (map 형식)
	@Override
	public int totalCount(Map<String, String> map) {
		int count = 0;
		count = dao.getCount(map);
 		return count;
	}
	
	// 조회  (map 형식)
	@Override
	public List getDataTableList(Map<String, String> map) {
		List list = null;
		list = dao.getDataTableList(map);
		return list;
	}

	// 조회
	@Override
	public List getDataTableList(BoardSettingBean bsb) {
		List list = null;
		list = dao.getDataTableList(bsb);
		return list;
	}

	// 전체 카운터
	@Override
	public int totalCount(BoardSettingBean bsb) {
		//System.out.println("totalCount!!!!");
		int count = 0;
		count = dao.getCount(bsb);
 		return count;
	}

	@Override
	public List<BoardSettingBean> getBeanList(BoardSettingBean bsb) {
		System.out.println("getBeanList!!!!");
		// 데이터 가져오기
		List<BoardSettingBean> list = new ArrayList<BoardSettingBean>();
		list = dao.getBeanList(bsb);
		return list;
	}

	@Override
	public List<Object> getList(BoardSettingBean bsb) {
		System.out.println("getList!!!!");
		
		return null;
	}

	// Bean 객체로 받아서 Bean 객체로 단일건 리턴
	@Override
	public BoardSettingBean getInfoBeanData(BoardSettingBean bsb) {
		System.out.println("getInfoBeanData !!!!");
		bsb = dao.getInfoBeanData(bsb);
		return bsb;
	}

	//데이터를 Bean 객체로 받아서 업데이트 후 결과값을 BoardSettingBean로 리턴을 받아 정의
	@Override
	public BoardSettingBean setBeanUpdate(BoardSettingBean bsb) {
		System.out.println("setBeanUpdate !!!! : " + bsb.getSeq());
		int orgSeq = bsb.getSeq();
		
		bsb = dao.setBeanUpdate(bsb);
		// 리턴값이 성공이면 보드 상세 내역 저장
		if(bsb.getResult().equals("1")) {
			if(orgSeq==0) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("board_id", bsb.getBoard_id());
				paramMap.put("regi_id", bsb.getRegi_id());
				paramMap.put("regi_ip_addr", bsb.getRegi_ip_addr());
				paramMap.put("modifier_id", bsb.getRegi_ip_addr());
				paramMap.put("modifier_ip_addr", bsb.getModifier_ip_addr());
				tableColumnMappingdao.setBeanNewMapInsert(paramMap);
			}
		}
		return bsb;
	}

	//데이터를 Bean 객체로 받아서 삭제 후 결과값을 BoardSettingBean로 리턴을 받아 정의
	@Override
	public BoardSettingBean setBeanDelete(BoardSettingBean bsb) {
		System.out.println("setBeanDelete !!!! : " + bsb.getSeq());
		bsb = dao.setBeanDelete(bsb);
		return bsb;
	}
	
	
	
	
	

}
