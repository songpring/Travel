package com.itwill.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwill.domain.BoardSettingBean;
import com.itwill.domain.TableColumnMappingBean;

@Repository
public class TableColumnMappingDAOImpl implements TableColumnMappingDAO {
	@Inject
	private SqlSession sql;
	
	private static final String namespace="com.itwill.mappers.tableColumnMappingMapper";
	
	
	
	// Map<String, Object> 형식으로 된 데이터를 기준으로 업데이트 함
	@Override
	public int update(Map<String, Object> map) {
		int result = 0;
		result = sql.update(namespace+".update",map);
		return result;
	}

	@Override
	public int insert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	// Bean 객체로 받아서 List<TableColumnMappingBean> 객체로 리턴
	@Override
	public List<TableColumnMappingBean> getInfoBeanList(TableColumnMappingBean tcb) {
		System.out.println("DAO - getInfoBeanList");
		List<TableColumnMappingBean> list = new ArrayList<TableColumnMappingBean>();
		list = (List)sql.selectList(namespace+".getInfoBeanData",tcb);
		// TODO Auto-generated method stub
		return list;
	}

	// Bean 객체로 받아서 Bean 객체로 단일건 리턴
	@Override
	public TableColumnMappingBean getInfoBeanData(TableColumnMappingBean tcb) {
		// TODO Auto-generated method stub
		tcb = sql.selectOne(namespace+".getInfoBeanData",tcb);
		return tcb;
	}

	//데이터를 Bean 객체로 받아서 업데이트 후 결과값을 BoardSettingBean로 리턴을 받아 정의
	@Override
	public TableColumnMappingBean setBeanUpdate(TableColumnMappingBean tcb) {
		// TODO Auto-generated method stub
		return null;
	}

	//데이터를 Bean 객체로 받아서 업데이트 후 결과값을 BoardSettingBean로 리턴을 받아 정의
	@Override
	public TableColumnMappingBean setBeanDelete(TableColumnMappingBean tcb) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public int setBeanNewMapInsert(Map<String, Object> paramMap) {
		int result = 0;
		String[] arrData= { "subject" ,"content","country","file_use","notice_use","secret_use"
				,"readcount","like","hate","recommend","etc1","etc2","etc3","etc4"
				,"regi_id","regi_date","regi_ip_addr","modifier_id","modifier_date","modifier_ip_addr"
		};
		String[] arrBoardNameKr = {"제목","내용","나라","첨부파일 여부","공지여부","비밀글 여부"
				,"조회수","좋아요","싫어요","추천수","기타1","기타2","기타3","기타4"
				,"등록자 ID","등록일","등록자 IP","수정자 ID","수정일","수정자 IP"
		};
		
		Map<String, Object> dataMap;
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		
		for(int i=0;i<arrData.length;i++) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("column_code", arrData[i]);
			dataMap.put("board_name_kr", arrBoardNameKr[i]);
			
			if(arrData[i].equals("subject")){
				dataMap.put("column_type", "TEXT");
			}
			else if(arrData[i].equals("content")){
				dataMap.put("column_type", "TEXTAREA");
			}
			else if(arrData[i].equals("country")){
				dataMap.put("column_type", "SELECT");
			}
			else if(arrData[i].equals("notice_use")){
				dataMap.put("column_type", "CHECKBOX");
			}
			else if(arrData[i].equals("secret_use")){
				dataMap.put("column_type", "CHECKBOX");
			}
			else {
				dataMap.put("column_type", "SPAN");
			}
			
			dataMap.put("information", "");
			
			if(arrData[i].equals("country")){
				dataMap.put("board_column_use", "N");
			}
			else {
				dataMap.put("board_column_use", "Y");
			}
			dataList.add(dataMap);
		}
		paramMap.put("dataList", dataList);
		//System.out.println("paramMap : " + paramMap);
		
		result = sql.insert(namespace+".foreachInsert",paramMap);
		//System.out.println("setBeanNewMapInsert : " + result);
		return result;
	}

	@Override
	public int setBeanNewInsert(TableColumnMappingBean tcb) {
		int result = 0;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
//		tcb.setBoard_id(bsb.getBoard_id());
//		tcb.setRegi_id(bsb.getRegi_id());
//		tcb.setRegi_ip_addr(bsb.getRegi_ip_addr());
//		tcb.setModifier_id(bsb.getModifier_id());
//		tcb.setModifier_ip_addr(bsb.getModifier_ip_addr());
		
		paramMap.put("board_id", 123);
		paramMap.put("company_id", 123);
		
		result = sql.insert(namespace+".setInsert",tcb);
		return result;
	}
}
