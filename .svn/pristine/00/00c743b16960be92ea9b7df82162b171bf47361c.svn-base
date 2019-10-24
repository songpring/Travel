package com.itwill.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.BoardSettingBean;

@Repository
public class BoardSettingDAOImpl implements BoardSettingDAO {
	@Inject
	private SqlSession sql;
	
	private static final String namespace="com.itwill.mappers.boardSettingMapper";
	
	
	

	@Override
	public int getCount(Map<String, String> map) {
		int result=0;
		result = sql.selectOne(namespace+".totCount",map);
		return result;
	}

	// DataTableList 용 리스트 map 형식
	@Override
	public List getDataTableList(Map<String, String> map) {
		List list = null;
		list = (List)sql.selectList(namespace+".selectListMap",map);
		return list;
	}


	// DataTableList 용 리스트
	@Override
	public List getDataTableList(BoardSettingBean bsb) {
		List list = null;
		list = (List)sql.selectList(namespace+".selectListMap",bsb);
		return list;
	}


	// 전체 카운터
	@Override
	public int getCount(BoardSettingBean bsb) {
		int result=0;
		result = sql.selectOne(namespace+".totCount",bsb);
		return result;
	}



	@Override
	public List<BoardSettingBean> getBeanList(BoardSettingBean bsb) {
		// TODO Auto-generated method stub
		return null;
	}

	// Bean 객체로 받아서 Bean 객체로 단일건 리턴
	@Override
	public BoardSettingBean getInfoBeanData(BoardSettingBean bsb) {
		bsb = sql.selectOne(namespace+".getInfoBeanData",bsb);
		return bsb;
	}

	//데이터를 Bean 객체로 받아서 업데이트 후 결과값을 int로 리턴
	@Override
	public BoardSettingBean setBeanUpdate(BoardSettingBean bsb) {
		//BoardSettingBean bsb2 = null;
		int result = 0;
		try {
			int orgSeq = bsb.getSeq();
			result = sql.insert(namespace+".setInsert",bsb);
			System.out.println("seq : " +  bsb.getSeq());
			if(bsb.getSeq()==0) {
				bsb.setSeq(orgSeq);
			}
			System.out.println("seq : " +  bsb.getSeq());
			
			bsb.setResult(result+"");
			if(result>=1) {
				bsb.setResult("1");
				//System.out.println("업데이트");
				bsb.setMessage("처리되었습니다.");
			}
			else {
				//System.out.println("업데이트 실패");
				bsb.setMessage("처리된 내역이 없습니다.");
			}
		} catch (Exception e) {
			bsb.setResult("-2");
			bsb.setMessage("오류 발생가 발생 하였습니다");
		}
		return bsb;
	}

	@Override
	public BoardSettingBean setBeanDelete(BoardSettingBean bsb) {
		int result = 0;
		try {
			result = sql.insert(namespace+".setDelete",bsb);
			bsb.setResult(result+"");
			if(result>=1) {
				bsb.setResult("1");
				bsb.setMessage("처리되었습니다.");
			}
			else {
				bsb.setMessage("처리된 내역이 없습니다.");
			}
		} catch (Exception e) {
			bsb.setResult("-2");
			bsb.setMessage("오류 발생가 발생 하였습니다");
		}
		return bsb;
	}
	
	
	

}
